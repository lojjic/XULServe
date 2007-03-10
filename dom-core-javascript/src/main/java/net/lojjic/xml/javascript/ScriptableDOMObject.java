package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.AnnotationScriptableObject;
import net.lojjic.rhino.annotations.JSConstructor;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;

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

	@JSConstructor
	public void jsConstructor() {
		throw new RuntimeException(getClassName() + " cannot be instantiated directly; " +
				"use document.createElement(tagName) or document.createElementNS(namespace, tagName) instead.");
	}
}
