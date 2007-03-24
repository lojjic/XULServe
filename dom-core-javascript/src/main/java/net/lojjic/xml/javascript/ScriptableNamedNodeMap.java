package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NamedNodeMap}
 */
@JSClassName("NamedNodeMap")
public class ScriptableNamedNodeMap extends ScriptableDOMObject {

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
	public Object getNamedItem(String name) {
		return delegateNamedNodeMap.getNamedItem(name);
	}

	@JSFunction("getNamedItemNS")
	public Object getNamedItemNS(String namespaceURI, String localName) {
		return delegateNamedNodeMap.getNamedItemNS(namespaceURI, localName);
	}

	@JSFunction("item")
	public Object item(int index) {
		return delegateNamedNodeMap.item(index);
	}

	@JSFunction("removeNamedItem")
	public Object removeNamedItem(String name) {
		return delegateNamedNodeMap.removeNamedItem(name);
	}

	@JSFunction("removeNamedItemNS")
	public Object removeNamedItemNS(String namespaceURI, String localName) {
		return delegateNamedNodeMap.removeNamedItemNS(namespaceURI, localName);
	}

	@JSFunction("setNamedItem")
	public Object setNamedItem(Object arg) {
		return delegateNamedNodeMap.setNamedItem(convertArg(arg, Node.class));
	}

	@JSFunction("setNamedItemNS")
	public Object setNamedItemNS(Object arg) {
		return delegateNamedNodeMap.setNamedItemNS(convertArg(arg, Node.class));
	}

	/**
	 * Contained items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
