package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.NodeList;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NodeList}
 */
@JSClassName("NodeList")
public class ScriptableNodeList<T extends NodeList> extends ScriptableDOMObject<T> {

	public ScriptableNodeList() {
		super();
	}

	public ScriptableNodeList(Scriptable scope, T nodeList) {
		super(scope, nodeList);
	}
	
	/**
	 * {@link NodeList#getLength()}
	 */
	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	/**
	 * {@link NodeList#item(int)}
	 */
	@JSFunction("item")
	public Object item(int index) {
		return Context.javaToJS(unwrap().item(index), getParentScope());
	}
	
	/**
	 * Get an indexed member; returns the node at the given index
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
