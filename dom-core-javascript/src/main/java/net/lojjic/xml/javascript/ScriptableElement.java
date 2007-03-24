package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
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
		return Context.javaToJS(delegateElement.getAttributeNode(name), getParentScope());
	}
	
	@JSFunction("setAttributeNode")
	public Object setAttributeNode(Object attr) {
		return Context.javaToJS(delegateElement.setAttributeNode(convertArg(attr, Attr.class)), getParentScope());
	}
	
	@JSFunction("removeAttributeNode")
	public Object removeAttributeNode(Object attr) {
		return Context.javaToJS(delegateElement.removeAttributeNode(convertArg(attr, Attr.class)), getParentScope());
	}
	
	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String name) {
		return Context.javaToJS(delegateElement.getElementsByTagName(name), getParentScope());
	}
	
	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String ns, String name) {
		return Context.javaToJS(delegateElement.getElementsByTagNameNS(ns, name), getParentScope());
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
		return Context.javaToJS(delegateElement.getAttributeNodeNS(ns, name), getParentScope());
	}
	
	@JSFunction("setAttributeNodeNS")
	public Object setAttributeNodeNS(Object attr) {
		return Context.javaToJS(delegateElement.setAttributeNodeNS(convertArg(attr, Attr.class)), getParentScope());
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
		return Context.javaToJS(delegateElement.getSchemaTypeInfo(), getParentScope());
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
