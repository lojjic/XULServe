package net.lojjic.xul.javascript;

import org.w3c.dom.Element;

public class ScriptableElement extends ScriptableNode {

	protected Element delegateElement;
	
	public ScriptableElement(Element element) {
		super(element);
		this.delegateElement = element;
	}

	@Override
	public String getClassName() {
		return "Element";
	}
	
	
	public String jsGet_tagName() {
		return delegateElement.getTagName();
	}
	
	public String jsFunction_getAttribute(String name) {
		return delegateElement.getAttribute(name);
	}
	
	public void jsFunction_setAttribute(String name, String value) {
		delegateElement.setAttribute(name, value);
	}
	
	public void jsFunction_removeAttribute(String name) {
		delegateElement.removeAttribute(name);
	}
	
	public ScriptableAttr jsFunction_getAttributeNode(String name) {
		return new ScriptableAttr(delegateElement.getAttributeNode(name));
	}
	
	public ScriptableAttr jsFunction_setAttributeNode(ScriptableAttr attr) {
		delegateElement.setAttributeNode(attr.delegateAttr);
		return attr;
	}
	
	public ScriptableAttr jsFunction_removeAttributeNode(ScriptableAttr attr) {
		delegateElement.removeAttributeNode(attr.delegateAttr);
		return attr;
	}
	
	public ScriptableNodeList jsFunction_getElementsByTagName(String name) {
		return new ScriptableNodeList(delegateElement.getElementsByTagName(name));
	}
	
	public ScriptableNodeList jsFunction_getElementsByTagNameNS(String ns, String name) {
		return new ScriptableNodeList(delegateElement.getElementsByTagNameNS(ns, name));
	}
	
	public String jsFunction_getAttributeNS(String ns, String name) {
		return delegateElement.getAttributeNS(ns, name);
	}
	
	public void jsFunction_setAttributeNS(String ns, String name, String value) {
		delegateElement.setAttributeNS(ns, name, value);
	}
	
	public void jsFunction_removeAttributeNS(String ns, String name) {
		delegateElement.removeAttributeNS(ns, name);
	}
	
	public ScriptableAttr jsFunction_getAttributeNodeNS(String ns, String name) {
		return new ScriptableAttr(delegateElement.getAttributeNodeNS(ns, name));
	}
	
	public ScriptableAttr jsFunction_setAttributeNodeNS(ScriptableAttr attr) {
		delegateElement.setAttributeNodeNS(attr.delegateAttr);
		return attr;
	}
	
	public boolean jsFunction_hasAttribute(String name) {
		return delegateElement.hasAttribute(name);
	}
	
	public boolean jsFunction_hasAttributeNS(String ns, String name) {
		return delegateElement.hasAttributeNS(ns, name);
	}
	
	/* TODO
	public TypeInfo jsGet_schemaTypeInfo() {
		return delegateElement.getSchemaTypeInfo();
	}
	*/
	
	public void jsFunction_setIdAttribute(String name, boolean makeId) {
		delegateElement.setIdAttribute(name, makeId);
	}
	
	public void jsFunction_setIdAttributeNS(String ns, String name, boolean makeId) {
		delegateElement.setIdAttributeNS(ns, name, makeId);
	}
	
	public void jsFunction_setIdAttributeNode(ScriptableAttr attr, boolean makeId) {
		delegateElement.setIdAttributeNode(attr.delegateAttr, makeId);
	}
}
