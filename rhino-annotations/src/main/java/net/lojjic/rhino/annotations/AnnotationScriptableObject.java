package net.lojjic.rhino.annotations;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.ScriptRuntime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AnnotationScriptableObject extends ScriptableObject {

	private String className;

	/**
	 * Data container for categorized annotated methods
	 */
	protected static class AnnotatedMethods {
		Method constructor = null;
		Map<String,Method> propertyGetters = new HashMap<String,Method>();
		Map<String,Method> propertySetters = new HashMap<String,Method>();
		Map<String,Method> functions = new HashMap<String,Method>();
	}


	protected AnnotationScriptableObject() {
		super();
	}

	protected AnnotationScriptableObject(Scriptable scope) {
		super(scope, null);
		setPrototype(getClassPrototype(scope, getClassName()));
	}


	@Override
	public String getClassName() {
		if(className == null) {
			className = getJSClassName(getClass());
		}
		return className;
	}


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

		// Set the prototype's prototype:
		prototype.setPrototype(getSuperPrototype(scope, clazz, sealed, mapInheritance));

		// Get the JS class name:
		String className = getJSClassName(clazz);

		// Collect the class's annotated methods:
		AnnotatedMethods methods = getAnnotatedMethods(clazz);

		// Add the constructor and prototype to the parent scope:
		FunctionObject ctor = new FunctionObject(className, methods.constructor, scope);
		ctor.addAsConstructor(scope, prototype);

		// Loop over the getters and add them and any corresponding setters
		// to the prototype:
		for(Map.Entry<String,Method> entry : methods.propertyGetters.entrySet()) {
			Method setter = methods.propertySetters.get(entry.getKey());
			int attr = PERMANENT | DONTENUM | (setter != null ? 0 : READONLY);
			ScriptableObject parent = (entry.getValue().isAnnotationPresent(JSStatic.class) ? ctor : (ScriptableObject)prototype);
			parent.defineProperty(entry.getKey(), null, entry.getValue(), setter, attr);
		}

		// Add the functions to the prototype:
		for(Map.Entry<String,Method> entry : methods.functions.entrySet()) {
			FunctionObject func = new FunctionObject(entry.getKey(), entry.getValue(), prototype);
			ScriptableObject parent = (entry.getValue().isAnnotationPresent(JSStatic.class) ? ctor : (ScriptableObject)prototype);
			parent.defineProperty(entry.getKey(), func, DONTENUM);
		}

		return className;
	}


	/**
	 * Get the target JavaScript class name for the given Class.
	 * Uses the {@link JSClassName}
	 * annotation if available, otherwise uses the Class's unqualified name.
	 */
	protected static String getJSClassName(Class clazz) {
		return (clazz.isAnnotationPresent(JSClassName.class))
				? ((JSClassName)clazz.getAnnotation(JSClassName.class)).value()
				: clazz.getSimpleName();
	}


	/**
	 * Collect the annotated methods for the given Class.
	 */
	protected static AnnotatedMethods getAnnotatedMethods(Class clazz) {
		AnnotatedMethods result = new AnnotatedMethods();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			// Constructor - only look for one:
			if(result.constructor == null && method.isAnnotationPresent(JSConstructor.class)) {
				result.constructor = method;
			}
			// Property getters:
			if(method.isAnnotationPresent(JSGetter.class)) {
				result.propertyGetters.put(method.getAnnotation(JSGetter.class).value(), method);
			}
			// Property setters:
			if(method.isAnnotationPresent(JSSetter.class)) {
				result.propertySetters.put(method.getAnnotation(JSSetter.class).value(), method);
			}
			// Functions:
			if(method.isAnnotationPresent(JSFunction.class)) {
				result.functions.put(method.getAnnotation(JSFunction.class).value(), method);
			}
		}
		return result;
	}


	/**
	 * Build the prototype's prototype, trying to map Java inheritance to JS
	 * prototype-based inheritance if requested to do so.
	 */
	protected static Scriptable getSuperPrototype(Scriptable scope, Class clazz, boolean mapInheritance, boolean sealed)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
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
		return superProto;
	}



}
