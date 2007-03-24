package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Element}
 */
@JSClassName("Element")
public class ScriptableElement extends ScriptableNode {

	protected Element delegateElement;
	
	public ScriptableElement(Scriptable scope, Element element) {
		super(scope, element);
		this.delegateElement = element;
	}
	
	@JSGetter("tagName")
	public String getTagName() {
		return delegateElement.getTagName();
	}

	@JSFunction("getAttribute")
	public String getAttribute(String name) {
		return delegateElement.getAttribute(name);
	}
	
	@JSFunction("setAttribute")
	public void setAttribute(String name, String value) {
		delegateElement.setAttribute(name, value);
	}
	
	@JSFunction("removeAttribute")
	public void removeAttribute(String name) {
		delegateElement.removeAttribute(name);
	}
	
	@JSFunction("getAttributeNode")
	public Object getAttributeNode(String name) {
		return delegateElement.getAttributeNode(name);
	}
	
	@JSFunction("setAttributeNode")
	public Object setAttributeNode(Object attr) {
		return delegateElement.setAttributeNode(convertArg(attr, Attr.class));
	}
	
	@JSFunction("removeAttributeNode")
	public Object removeAttributeNode(Object attr) {
		return delegateElement.removeAttributeNode(convertArg(attr, Attr.class));
	}
	
	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String name) {
		return delegateElement.getElementsByTagName(name);
	}
	
	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String ns, String name) {
		return delegateElement.getElementsByTagNameNS(ns, name);
	}
	
	@JSFunction("getAttributeNS")
	public String getAttributeNS(String ns, String name) {
		return delegateElement.getAttributeNS(ns, name);
	}
	
	@JSFunction("setAttributeNS")
	public void setAttributeNS(String ns, String name, String value) {
		delegateElement.setAttributeNS(ns, name, value);
	}
	
	@JSFunction("removeAttributeNS")
	public void removeAttributeNS(String ns, String name) {
		delegateElement.removeAttributeNS(ns, name);
	}
	
	@JSFunction("getAttributeNodeNS")
	public Object getAttributeNodeNS(String ns, String name) {
		return delegateElement.getAttributeNodeNS(ns, name);
	}
	
	@JSFunction("setAttributeNodeNS")
	public Object setAttributeNodeNS(Object attr) {
		return delegateElement.setAttributeNodeNS(convertArg(attr, Attr.class));
	}
	
	@JSFunction("hasAttribute")
	public boolean hasAttribute(String name) {
		return delegateElement.hasAttribute(name);
	}
	
	@JSFunction("hasAttributeNS")
	public boolean hasAttributeNS(String ns, String name) {
		return delegateElement.hasAttributeNS(ns, name);
	}
	
	@JSGetter("schemaTypeInfo")
	public Object getSchemaTypeInfo() {
		return delegateElement.getSchemaTypeInfo();
	}

	@JSFunction("setIdAttribute")
	public void setIdAttribute(String name, boolean makeId) {
		delegateElement.setIdAttribute(name, makeId);
	}
	
	@JSFunction("setIdAttributeNS")
	public void setIdAttributeNS(String ns, String name, boolean makeId) {
		delegateElement.setIdAttributeNS(ns, name, makeId);
	}
	
	@JSFunction("setIdAttributeNode")
	public void setIdAttributeNode(Object attr, boolean makeId) {
		delegateElement.setIdAttributeNode(convertArg(attr, Attr.class), makeId);
	}
}
