package net.lojjic.xml.javascript;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NamedNodeMap}
 */
@JSClassName("NamedNodeMap")
public class ScriptableNamedNodeMap extends ScriptableDOMObject implements NamedNodeMap {

	private NamedNodeMap delegateNamedNodeMap;

	public ScriptableNamedNodeMap(Scriptable scope, NamedNodeMap delegateNamedNodeMap) {
		super(scope, delegateNamedNodeMap);
		this.delegateNamedNodeMap = delegateNamedNodeMap;
	}

	@JSGetter("length")
	public int getLength() {
		return delegateNamedNodeMap.getLength();
	}

	@JSFunction("getNamedItem")
	public Node getNamedItem(String name) {
		return delegateNamedNodeMap.getNamedItem(name);
	}

	@JSFunction("getNamedItemNS")
	public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
		return delegateNamedNodeMap.getNamedItemNS(namespaceURI, localName);
	}

	@JSFunction("item")
	public Node item(int index) {
		return delegateNamedNodeMap.item(index);
	}

	@JSFunction("removeNamedItem")
	public Node removeNamedItem(String name) throws DOMException {
		return delegateNamedNodeMap.removeNamedItem(name);
	}

	@JSFunction("removeNamedItemNS")
	public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
		return delegateNamedNodeMap.removeNamedItemNS(namespaceURI, localName);
	}

	@JSFunction("setNamedItem")
	public Node setNamedItem(Node arg) throws DOMException {
		return delegateNamedNodeMap.setNamedItem(arg);
	}

	@JSFunction("setNamedItemNS")
	public Node setNamedItemNS(Node arg) throws DOMException {
		return delegateNamedNodeMap.setNamedItemNS(arg);
	}

	/**
	 * Contained items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
