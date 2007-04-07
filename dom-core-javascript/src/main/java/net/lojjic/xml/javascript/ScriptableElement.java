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
public class ScriptableElement<T extends Element> extends ScriptableNode<T> {

	public ScriptableElement() {
		super();
	}

	public ScriptableElement(Scriptable scope, T element) {
		super(scope, element);
	}
	
	@JSGetter("tagName")
	public String getTagName() {
		return unwrap().getTagName();
	}

	@JSFunction("getAttribute")
	public String getAttribute(String name) {
		return unwrap().getAttribute(name);
	}
	
	@JSFunction("setAttribute")
	public void setAttribute(String name, String value) {
		unwrap().setAttribute(name, value);
	}
	
	@JSFunction("removeAttribute")
	public void removeAttribute(String name) {
		unwrap().removeAttribute(name);
	}
	
	@JSFunction("getAttributeNode")
	public Object getAttributeNode(String name) {
		return Context.javaToJS(unwrap().getAttributeNode(name), getParentScope());
	}
	
	@JSFunction("setAttributeNode")
	public Object setAttributeNode(Object attr) {
		return Context.javaToJS(unwrap().setAttributeNode(convertArg(attr, Attr.class)), getParentScope());
	}
	
	@JSFunction("removeAttributeNode")
	public Object removeAttributeNode(Object attr) {
		return Context.javaToJS(unwrap().removeAttributeNode(convertArg(attr, Attr.class)), getParentScope());
	}
	
	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String name) {
		return Context.javaToJS(unwrap().getElementsByTagName(name), getParentScope());
	}
	
	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String ns, String name) {
		return Context.javaToJS(unwrap().getElementsByTagNameNS(ns, name), getParentScope());
	}
	
	@JSFunction("getAttributeNS")
	public String getAttributeNS(String ns, String name) {
		return unwrap().getAttributeNS(ns, name);
	}
	
	@JSFunction("setAttributeNS")
	public void setAttributeNS(String ns, String name, String value) {
		unwrap().setAttributeNS(ns, name, value);
	}
	
	@JSFunction("removeAttributeNS")
	public void removeAttributeNS(String ns, String name) {
		unwrap().removeAttributeNS(ns, name);
	}
	
	@JSFunction("getAttributeNodeNS")
	public Object getAttributeNodeNS(String ns, String name) {
		return Context.javaToJS(unwrap().getAttributeNodeNS(ns, name), getParentScope());
	}
	
	@JSFunction("setAttributeNodeNS")
	public Object setAttributeNodeNS(Object attr) {
		return Context.javaToJS(unwrap().setAttributeNodeNS(convertArg(attr, Attr.class)), getParentScope());
	}
	
	@JSFunction("hasAttribute")
	public boolean hasAttribute(String name) {
		return unwrap().hasAttribute(name);
	}
	
	@JSFunction("hasAttributeNS")
	public boolean hasAttributeNS(String ns, String name) {
		return unwrap().hasAttributeNS(ns, name);
	}
	
	@JSGetter("schemaTypeInfo")
	public Object getSchemaTypeInfo() {
		return Context.javaToJS(unwrap().getSchemaTypeInfo(), getParentScope());
	}

	@JSFunction("setIdAttribute")
	public void setIdAttribute(String name, boolean makeId) {
		unwrap().setIdAttribute(name, makeId);
	}
	
	@JSFunction("setIdAttributeNS")
	public void setIdAttributeNS(String ns, String name, boolean makeId) {
		unwrap().setIdAttributeNS(ns, name, makeId);
	}
	
	@JSFunction("setIdAttributeNode")
	public void setIdAttributeNode(Object attr, boolean makeId) {
		unwrap().setIdAttributeNode(convertArg(attr, Attr.class), makeId);
	}
}
