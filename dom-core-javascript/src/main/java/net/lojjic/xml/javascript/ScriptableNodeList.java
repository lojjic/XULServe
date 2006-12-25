package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScriptableNodeList extends ScriptableDOMObject {
	
	public static String JS_CLASS = "NodeList";

	NodeList delegateNodeList;
	
	
	public ScriptableNodeList(Scriptable scope, NodeList nodeList) {
		super(scope);
		this.delegateNodeList = nodeList;
	}
	
	/**
	 * {@link NodeList#getLength()}
	 */
	public int jsGet_length() {
		return delegateNodeList.getLength();
	}

	/**
	 * {@link NodeList#item(int)}
	 */
	public Object jsFunction_item(int index) {
		Node node = delegateNodeList.item(index);
		if(null == node) {
			return NOT_FOUND;
		}
		return new ScriptableNode(getParentScope(), node);
	}
	
	/**
	 * Get an indexed member; returns the node at the given indexed
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return jsFunction_item(index);
	}
}
