package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.AnnotationScriptableObject;
import net.lojjic.rhino.annotations.JSConstructor;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.Context;

/**
 * Common abstract superclass for all the Scriptable DOM object wrappers
 */
public abstract class ScriptableDOMObject extends AnnotationScriptableObject implements Wrapper {

	private Object wrappedObject;

	protected ScriptableDOMObject() {
		super();
	}

	protected ScriptableDOMObject(Scriptable scope, Object wrappedObject) {
		super(scope);
		this.wrappedObject = wrappedObject;
	}

	/**
	 * @see org.mozilla.javascript.Wrapper#unwrap()
	 * @return the wrapped Java object
	 */
	public Object unwrap() {
		return wrappedObject;
	}

	/**
	 * Cast/convert the given argument Object to the given Class.
	 */
	@SuppressWarnings({"unchecked"})
	protected <T> T convertArg(Object arg, Class<T> type) {
		while(arg instanceof Wrapper) {
			arg = ((Wrapper)arg).unwrap();
		}
		if(!type.isInstance(arg)) {
			throw new IllegalArgumentException("Expected argument of type " + type.getName()
					+ ", got " + arg.getClass().getName());
		}
		return (T)arg;
	}

	/**
	 * Convert the given Java object into a JS object suitable as a return value
	 */
	protected Object convertReturnValue(Object javaObject) {
		return Context.javaToJS(javaObject, getParentScope());
	}

	@JSConstructor
	public void jsConstructor() {
		throw new RuntimeException(getClassName() + " cannot be instantiated directly; " +
				"use document.createElement(tagName) or document.createElementNS(namespace, tagName) instead.");
	}
}
