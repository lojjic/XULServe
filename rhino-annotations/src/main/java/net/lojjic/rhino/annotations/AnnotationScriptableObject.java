package net.lojjic.rhino.annotations;

import org.mozilla.javascript.*;

import java.lang.reflect.*;
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
	protected static class AnnotatedMembers {
		Member constructor = null;
		Map<String,Method> propertyGetters = new HashMap<String,Method>();
		Map<String,Method> propertySetters = new HashMap<String,Method>();
		Map<String,Method> functions = new HashMap<String,Method>();

		/**
		 * Add the members from the given AnnotatedMembers object, if not already defined
		 */
		void add(AnnotatedMembers other) {
			if(constructor == null) {
				constructor = other.constructor;
			}
			for(String key : other.propertyGetters.keySet()) {
				if(!propertyGetters.containsKey(key)) {
					propertyGetters.put(key, other.propertyGetters.get(key));
				}
			}
			for(String key : other.propertySetters.keySet()) {
				if(!propertySetters.containsKey(key)) {
					propertySetters.put(key, other.propertySetters.get(key));
				}
			}
			for(String key : other.functions.keySet()) {
				if(!functions.containsKey(key)) {
					functions.put(key, other.functions.get(key));
				}
			}
		}
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


	public static void defineClass(Scriptable scope, Class<? extends AnnotationScriptableObject> clazz)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		defineClass(scope, clazz, false, false);
	}

	public static void defineClass(Scriptable scope, Class<? extends AnnotationScriptableObject> clazz, boolean sealed)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		defineClass(scope, clazz, sealed, false);
	}

	public static String defineClass(Scriptable scope, Class<? extends AnnotationScriptableObject> clazz,
	                                 boolean sealed, boolean mapInheritance)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {

		// Create the prototype instance:
		Scriptable prototype = clazz.newInstance();

		// Set the prototype's prototype:
		prototype.setPrototype(getSuperPrototype(scope, clazz, sealed, mapInheritance));

		// Get the JS class name:
		String className = getJSClassName(clazz);

		// Collect the class's annotated methods:
		AnnotatedMembers methods = getAnnotatedMethods(clazz, !mapInheritance);

		// Add the constructor and prototype to the parent scope:
		FunctionObject ctor = new FunctionObject(className, methods.constructor, scope);
		ctor.addAsConstructor(scope, prototype);

		// Loop over the getters and add them and any corresponding setters
		// to the prototype:
		for(Map.Entry<String,Method> entry : methods.propertyGetters.entrySet()) {
			Method setter = methods.propertySetters.get(entry.getKey());
			int attr = PERMANENT | (setter != null ? 0 : READONLY);
			ScriptableObject parent = (entry.getValue().isAnnotationPresent(JSStatic.class) ? ctor : (ScriptableObject)prototype);
			parent.defineProperty(entry.getKey(), null, entry.getValue(), setter, attr);
		}

		// Add the functions to the prototype:
		for(Map.Entry<String,Method> entry : methods.functions.entrySet()) {
			FunctionObject func = new FunctionObject(entry.getKey(), entry.getValue(), prototype);
			ScriptableObject parent = (entry.getValue().isAnnotationPresent(JSStatic.class) ? ctor : (ScriptableObject)prototype);
			parent.defineProperty(entry.getKey(), func, 0);
		}

		// Seal objects if requested:
		if(sealed) {
			ctor.sealObject();
			if(prototype instanceof ScriptableObject) {
				((ScriptableObject)prototype).sealObject();
			}
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
	protected static AnnotatedMembers getAnnotatedMethods(Class<? extends AnnotationScriptableObject> clazz, boolean includeInherited) {
		AnnotatedMembers result = new AnnotatedMembers();

		for(Method method : clazz.getDeclaredMethods()) {
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
			// JSStatic only applies to static methods:
			if(method.isAnnotationPresent(JSStatic.class) && !Modifier.isStatic(method.getModifiers())) {
				throw Context.reportRuntimeError("@JSStatic annotation applied to non-static method " + method.getName());
			}
		}

		// Constructor:
		Member ctor = getJSConstructor(clazz);
		if(ctor == null) {
			throw Context.reportRuntimeError("No constructor found for class " + clazz.getName());
		}
		result.constructor = ctor;

		if(includeInherited) {
			Class superClass = clazz.getSuperclass();
			if (superClass != AnnotationScriptableObject.class &&
					AnnotationScriptableObject.class.isAssignableFrom(superClass)) {
				result.add(getAnnotatedMethods(superClass, includeInherited));
			}
		}

		return result;
	}

	/**
	 * Find an appropriate {@link Constructor} to use as the JavaScript constructor
	 * function for the given AnnotationScriptableObject class. First, look for a
	 * method annotated with {@link JSConstructor}. Then look for a constructor
	 * annotated with {@link JSConstructor}. If none is found, use the constructor
	 * with the fewest arguments. If no public constructors are found, return null.
	 */
	protected static Member getJSConstructor(Class<? extends AnnotationScriptableObject> clazz) {
		for(Method method : clazz.getDeclaredMethods()) {
			if(method.isAnnotationPresent(JSConstructor.class)) {
				return method;
			}
		}
		Constructor fewestArgsCtor = null;
		for(Constructor ctor : clazz.getConstructors()) {
			if(ctor.isAnnotationPresent(JSConstructor.class)) {
				return ctor;
			}
			if(fewestArgsCtor == null || ctor.getParameterTypes().length < fewestArgsCtor.getParameterTypes().length) {
				fewestArgsCtor = ctor;
			}
		}
		return fewestArgsCtor;
	}


	/**
	 * Build the prototype's prototype, trying to map Java inheritance to JS
	 * prototype-based inheritance if requested to do so.
	 */
	protected static Scriptable getSuperPrototype(Scriptable scope, Class clazz, boolean sealed, boolean mapInheritance)
			throws IllegalAccessException, InstantiationException, InvocationTargetException {
		Scriptable superProto = null;
		if (mapInheritance) {
			Class superClass = clazz.getSuperclass();
			if (superClass != AnnotationScriptableObject.class &&
					AnnotationScriptableObject.class.isAssignableFrom(superClass)) {
				String name = getJSClassName(superClass);
				if (name != null) {
					superProto = getClassPrototype(scope, name);
				}
				if(superProto == null) {
					name = defineClass(scope, superClass, sealed, mapInheritance);
				}
				if (name != null) {
					superProto = getClassPrototype(scope, name);
				}
			}
		}
		if (superProto == null) {
			superProto = getObjectPrototype(scope);
		}
		return superProto;
	}

}
