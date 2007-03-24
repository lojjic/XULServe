package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NamedNodeMap}
 */
@JSClassName("NamedNodeMap")
public class ScriptableNamedNodeMap extends ScriptableDOMObject {

	private NamedNodeMap delegateNamedNodeMap;

	public ScriptableNamedNodeMap() {
		super();
	}

	public ScriptableNamedNodeMap(Scriptable scope, NamedNodeMap delegateNamedNodeMap) {
		super(scope, delegateNamedNodeMap);
		this.delegateNamedNodeMap = delegateNamedNodeMap;
	}

	@JSGetter("length")
	public int getLength() {
		return delegateNamedNodeMap.getLength();
	}

	@JSFunction("getNamedItem")
	public Object getNamedItem(String name) {
		return Context.javaToJS(delegateNamedNodeMap.getNamedItem(name), getParentScope());
	}

	@JSFunction("getNamedItemNS")
	public Object getNamedItemNS(String namespaceURI, String localName) {
		return Context.javaToJS(delegateNamedNodeMap.getNamedItemNS(namespaceURI, localName), getParentScope());
	}

	@JSFunction("item")
	public Object item(int index) {
		return Context.javaToJS(delegateNamedNodeMap.item(index), getParentScope());
	}

	@JSFunction("removeNamedItem")
	public Object removeNamedItem(String name) {
		return Context.javaToJS(delegateNamedNodeMap.removeNamedItem(name), getParentScope());
	}

	@JSFunction("removeNamedItemNS")
	public Object removeNamedItemNS(String namespaceURI, String localName) {
		return Context.javaToJS(delegateNamedNodeMap.removeNamedItemNS(namespaceURI, localName), getParentScope());
	}

	@JSFunction("setNamedItem")
	public Object setNamedItem(Object arg) {
		return Context.javaToJS(delegateNamedNodeMap.setNamedItem(convertArg(arg, Node.class)), getParentScope());
	}

	@JSFunction("setNamedItemNS")
	public Object setNamedItemNS(Object arg) {
		return Context.javaToJS(delegateNamedNodeMap.setNamedItemNS(convertArg(arg, Node.class)), getParentScope());
	}

	/**
	 * Contained items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
