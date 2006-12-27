package net.lojjic.xml.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;

import java.util.HashMap;
import java.lang.reflect.Constructor;

public class DOMWrapFactory extends WrapFactory {
	
	private HashMap<Class, Class<? extends ScriptableDOMObject>> mappings = new HashMap<Class, Class<? extends ScriptableDOMObject>>();


	/**
	 * Add a mapping from a DOM class/interface to its Scriptable wrapper class.
	 * @param domClass The DOM class or interface to be wrapped
	 * @param scriptableClass The Scriptable class in which instances of the DOM class
	 *        should be wrapped. Must be a subclass of {@link ScriptableDOMObject}.
	 */
	public void addWrapMapping(Class domClass, Class<? extends ScriptableDOMObject> scriptableClass) {
		mappings.put(domClass, scriptableClass);
	}


	/**
	 * Override the base functionality to handle our special DOM object
	 * wrappers.
	 * 
	 * @param cx the current Context for this thread
	 * @param scope the scope of the executing script
	 * @param javaObject the object to be wrapped
	 * @param staticType type hint. If security restrictions prevent to wrap
     *        object based on its class, staticType will be used instead.
	 * @return the wrapped value which shall not be null
	 */
	@Override
	public Scriptable wrapAsJavaObject(Context cx, Scriptable scope, Object javaObject, Class staticType) {

		// If a matching wrapper class is defined in the mappings, create an instance:
		Class javaClass = javaObject.getClass();
		Class wrapper = getWrapperForClass(javaClass);
		if(wrapper != null) {
			try {
				Constructor constructor = wrapper.getConstructor(Scriptable.class, javaClass);
				return (Scriptable)constructor.newInstance(scope, javaObject);
			}
			catch(Exception e) {
				// TODO handle more elegantly
				System.out.println("Problem instantiating JavaScript wrapper class; falling back to default wrapper.");
			}
		}

		return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
	}


	/**
	 * Find the most specific mapped wrapper for the given Class.
	 * Walks the inheritance line, checking each class and its explicitly
	 * declared interfaces for a match in the mappings. If no mapping
	 * is found, null is returned.
	 */
	private Class getWrapperForClass(Class clazz) {
		while(clazz != null) {
			// Try the class directly:
			Class wrapper = mappings.get(clazz);
			if(wrapper != null) {
				return wrapper;
			}
			// Try the class's interfaces:
			for(Class iface : clazz.getInterfaces()) {
				wrapper = mappings.get(iface);
				if(wrapper != null) {
					return wrapper;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

}
