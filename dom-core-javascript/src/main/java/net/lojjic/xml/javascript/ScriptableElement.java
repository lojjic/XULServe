package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;

@JSClassName("Element")
public class ScriptableElement extends ScriptableNode implements Element {

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
	public Attr getAttributeNode(String name) {
		return delegateElement.getAttributeNode(name);
	}
	
	@JSFunction("setAttributeNode")
	public Attr setAttributeNode(Attr attr) {
		return delegateElement.setAttributeNode(attr);
	}
	
	@JSFunction("removeAttributeNode")
	public Attr removeAttributeNode(Attr attr) {
		return delegateElement.removeAttributeNode(attr);
	}
	
	@JSFunction("getElementsByTagName")
	public NodeList getElementsByTagName(String name) {
		return delegateElement.getElementsByTagName(name);
	}
	
	@JSFunction("getElementsByTagNameNS")
	public NodeList getElementsByTagNameNS(String ns, String name) {
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
	public Attr getAttributeNodeNS(String ns, String name) {
		return delegateElement.getAttributeNodeNS(ns, name);
	}
	
	@JSFunction("setAttributeNodeNS")
	public Attr setAttributeNodeNS(Attr attr) {
		return delegateElement.setAttributeNodeNS(attr);
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
	public TypeInfo getSchemaTypeInfo() {
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
	public void setIdAttributeNode(Attr attr, boolean makeId) {
		delegateElement.setIdAttributeNode(attr, makeId);
	}
}
