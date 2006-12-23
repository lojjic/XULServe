package net.lojjic.xul.javascript;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
;

public class ScriptableNodeList extends ScriptableObject {

	NodeList delegateNodeList;
	
	
	public ScriptableNodeList(NodeList nodeList) {
		this.delegateNodeList = nodeList;
	}
	
	
	@Override
	public String getClassName() {
		return "NodeList";
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
		return new ScriptableNode(node);
	}
	
	/**
	 * Get an indexed member; returns the node at the given indexed
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return jsFunction_item(index);
	}
}
