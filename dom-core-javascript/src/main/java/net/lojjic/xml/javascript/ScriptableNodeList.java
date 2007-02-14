package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NodeList}
 */
@JSClassName("NodeList")
public class ScriptableNodeList extends ScriptableDOMObject implements NodeList {

	NodeList delegateNodeList;
	
	public ScriptableNodeList(Scriptable scope, NodeList nodeList) {
		super(scope, nodeList);
		this.delegateNodeList = nodeList;
	}
	
	/**
	 * {@link NodeList#getLength()}
	 */
	@JSGetter("length")
	public int getLength() {
		return delegateNodeList.getLength();
	}

	/**
	 * {@link NodeList#item(int)}
	 */
	@JSFunction("item")
	public Node item(int index) {
		return delegateNodeList.item(index);
	}
	
	/**
	 * Get an indexed member; returns the node at the given index
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
