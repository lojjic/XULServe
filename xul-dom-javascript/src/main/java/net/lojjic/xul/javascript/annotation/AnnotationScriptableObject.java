package net.lojjic.xul.javascript.annotation;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.ScriptRuntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AnnotationScriptableObject extends ScriptableObject {

	public static void defineClass(Scriptable scope, Class clazz)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		defineClass(scope, clazz, false, false);
	}

	public static void defineClass(Scriptable scope, Class clazz, boolean sealed)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		defineClass(scope, clazz, sealed, false);
	}

	public static String defineClass(Scriptable scope, Class clazz, boolean sealed, boolean mapInheritance)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {

		// Create the prototype instance:
		Scriptable prototype = (Scriptable)clazz.newInstance();


		// Set the prototype's prototype, trying to map Java inheritance to JS
		// prototype-based inheritance if requested to do so.
		Scriptable superProto = null;
		if (mapInheritance) {
			Class superClass = clazz.getSuperclass();
			if (ScriptRuntime.ScriptableClass.isAssignableFrom(superClass)) {
				String name = ScriptableObject.defineClass(scope, superClass, sealed, mapInheritance);
				if (name != null) {
					superProto = ScriptableObject.getClassPrototype(scope, name);
				}
			}
		}
		if (superProto == null) {
			superProto = ScriptableObject.getObjectPrototype(scope);
		}
		prototype.setPrototype(superProto);


		// Get the JS class name:
		String className = (clazz.isAnnotationPresent(JSClassName.class))
				? ((JSClassName)clazz.getAnnotation(JSClassName.class)).value()
				: clazz.getSimpleName();


		// Go through the class's methods and collect the annotated ones:
		Method constructor = null;
		HashMap<String,Method> getters = new HashMap<String,Method>();
		HashMap<String,Method> setters = new HashMap<String,Method>();
		HashMap<String,Method> functions = new HashMap<String,Method>();

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			// Constructor - only look for one:
			if(constructor == null && method.isAnnotationPresent(JSConstructor.class)) {
				constructor = method;
			}

			// Property getters:
			if(method.isAnnotationPresent(JSGetter.class)) {
				getters.put(method.getAnnotation(JSGetter.class).value(), method);
			}

			// Property setters:
			if(method.isAnnotationPresent(JSSetter.class)) {
				setters.put(method.getAnnotation(JSSetter.class).value(), method);
			}

			// Functions:
			if(method.isAnnotationPresent(JSFunction.class)) {
				functions.put(method.getAnnotation(JSFunction.class).value(), method);
			}
		}

		// Add the constructor and prototype to the parent scope:
		if(constructor != null) {
			FunctionObject ctor = new FunctionObject(className, constructor, scope);
			ctor.addAsConstructor(scope, prototype);
		}

		// Loop over the getters and add them and any corresponding setters
		// to the prototype:
		for(Map.Entry<String,Method> entry : getters.entrySet()) {
			Method setter = setters.get(entry.getKey());
			int attr = PERMANENT | DONTENUM | (setter != null ? 0 : READONLY);
			((ScriptableObject)prototype).defineProperty(entry.getKey(), null, entry.getValue(), setter, attr);
		}

		// Add the functions to the prototype:
		for(Map.Entry<String,Method> entry : functions.entrySet()) {
			FunctionObject func = new FunctionObject(entry.getKey(), entry.getValue(), prototype);
			((ScriptableObject)prototype).defineProperty(entry.getKey(), func, DONTENUM);
		}

		return className;
	}

}
