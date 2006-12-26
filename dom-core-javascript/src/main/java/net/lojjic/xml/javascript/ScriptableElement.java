package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;

public class ScriptableElement extends ScriptableNode {

	public static String JS_CLASS_NAME = "Element";
		
	protected Element delegateElement;
	
	public ScriptableElement(Scriptable scope, Element element) {
		super(scope, element);
		this.delegateElement = element;
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
	
	public Object jsFunction_getAttributeNode(String name) {
		return wrap(delegateElement.getAttributeNode(name));
	}
	
	public Object jsFunction_setAttributeNode(ScriptableAttr attr) {
		delegateElement.setAttributeNode(attr.delegateAttr);
		return attr;
	}
	
	public Object jsFunction_removeAttributeNode(ScriptableAttr attr) {
		delegateElement.removeAttributeNode(attr.delegateAttr);
		return attr;
	}
	
	public Object jsFunction_getElementsByTagName(String name) {
		return wrap(delegateElement.getElementsByTagName(name));
	}
	
	public Object jsFunction_getElementsByTagNameNS(String ns, String name) {
		return wrap(delegateElement.getElementsByTagNameNS(ns, name));
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
	
	public Object jsFunction_getAttributeNodeNS(String ns, String name) {
		return wrap(delegateElement.getAttributeNodeNS(ns, name));
	}
	
	public Object jsFunction_setAttributeNodeNS(ScriptableAttr attr) {
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
