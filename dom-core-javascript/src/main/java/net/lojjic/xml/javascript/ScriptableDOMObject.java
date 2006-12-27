package net.lojjic.xml.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public abstract class ScriptableDOMObject extends ScriptableObject {

	public static String JS_CLASS_NAME;
	
	public ScriptableDOMObject(Scriptable scope) {
		super(scope, getClassPrototype(scope, JS_CLASS_NAME));
	}

	@Override
	public String getClassName() {
		return JS_CLASS_NAME;
	}


	protected Object wrap(Object javaObject) {
		return Context.javaToJS(javaObject, getTopLevelScope(this));
	}


	public void jsConstructor() {
		throw new IllegalStateException(getClassName() + " cannot be instantiated directly; " +
				"use document.createElement(tagName) or document.createElementNS(namespace, tagName) instead.");
	}
}
