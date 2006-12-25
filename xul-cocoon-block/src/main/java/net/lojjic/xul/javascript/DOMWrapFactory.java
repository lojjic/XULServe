package net.lojjic.xul.javascript;


import net.lojjic.xul.XULElement;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMWrapFactory extends WrapFactory {
	

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
		
		if(javaObject instanceof XULElement) {
			return new ScriptableXULElement(scope, (XULElement)javaObject);
		}
		if(javaObject instanceof Element) {
			return new ScriptableElement(scope, (Element)javaObject);
		}
		if(javaObject instanceof Document) {
			return new ScriptableDocument(scope, (Document)javaObject);
		}
		if(javaObject instanceof Node) {
			return new ScriptableNode(scope, (Node)javaObject);
		}
		if(javaObject instanceof NodeList) {
			return new ScriptableNodeList(scope, (NodeList)javaObject);
		}
		
		return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
	}

}
