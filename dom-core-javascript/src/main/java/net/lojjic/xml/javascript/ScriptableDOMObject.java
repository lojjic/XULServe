package net.lojjic.xml.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Node;

public abstract class ScriptableDOMObject extends ScriptableObject {

	public static final String USER_DATA_KEY = "ScriptableDOMObject:UserDataKey";

	public static String JS_CLASS_NAME;
	
	public ScriptableDOMObject(Scriptable scope) {
		super(scope, getClassPrototype(scope, JS_CLASS_NAME));
	}

	@Override
	public String getClassName() {
		return JS_CLASS_NAME;
	}


	protected Object wrap(Object javaObject) {
		Object wrapper = null;
		if(javaObject instanceof Node) {
			wrapper = ((Node)javaObject).getUserData(USER_DATA_KEY);
		}
		if(wrapper == null) {
			wrapper = Context.javaToJS(javaObject, getTopLevelScope(this));
			if(javaObject instanceof Node) {
				((Node)javaObject).setUserData(USER_DATA_KEY, wrapper, null);
			}
		}
		return wrapper;
	}


	public void jsConstructor() {
		throw new IllegalStateException(getClassName() + " cannot be instantiated directly; " +
				"use document.createElement(tagName) or document.createElementNS(namespace, tagName) instead.");
	}
}
