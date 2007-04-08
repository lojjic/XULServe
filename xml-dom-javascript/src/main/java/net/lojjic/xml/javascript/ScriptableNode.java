package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.*;
import net.lojjic.xml.javascript.events.ScriptableEventListener;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Node}
 */
@JSClassName("Node")
public class ScriptableNode<T extends Node> extends ScriptableDOMObject<T> {

	public ScriptableNode() {
		super();
	}

	public ScriptableNode(Scriptable scope, T node) {
		super(scope, node);
	}

	@JSStatic @JSGetter("ELEMENT_NODE")
	public static int get_ELEMENT_NODE(ScriptableObject obj) {
		return Node.ELEMENT_NODE;
	}

	@JSStatic @JSGetter("ATTRIBUTE_NODE")
	public static int get_ATTRIBUTE_NODE(ScriptableObject obj) {
		return Node.ATTRIBUTE_NODE;
	}

	@JSStatic @JSGetter("TEXT_NODE")
	public static int get_TEXT_NODE(ScriptableObject obj) {
		return Node.TEXT_NODE;
	}

	@JSStatic @JSGetter("CDATA_SECTION_NODE")
	public static int get_CDATA_SECTION_NODE(ScriptableObject obj) {
		return Node.CDATA_SECTION_NODE;
	}

	@JSStatic @JSGetter("ENTITY_REFERENCE_NODE")
	public static int get_ENTITY_REFERENCE_NODE(ScriptableObject obj) {
		return Node.ENTITY_REFERENCE_NODE;
	}

	@JSStatic @JSGetter("ENTITY_NODE")
	public static int get_ENTITY_NODE(ScriptableObject obj) {
		return Node.ENTITY_NODE;
	}

	@JSStatic @JSGetter("PROCESSING_INSTRUCTION_NODE")
	public static int get_PROCESSING_INSTRUCTION_NODE(ScriptableObject obj) {
		return Node.PROCESSING_INSTRUCTION_NODE;
	}

	@JSStatic @JSGetter("COMMENT_NODE")
	public static int get_COMMENT_NODE(ScriptableObject obj) {
		return Node.COMMENT_NODE;
	}

	@JSStatic @JSGetter("DOCUMENT_NODE")
	public static int get_DOCUMENT_NODE(ScriptableObject obj) {
		return Node.DOCUMENT_NODE;
	}

	@JSStatic @JSGetter("DOCUMENT_TYPE_NODE")
	public static int get_DOCUMENT_TYPE_NODE(ScriptableObject obj) {
		return Node.DOCUMENT_TYPE_NODE;
	}

	@JSStatic @JSGetter("DOCUMENT_FRAGMENT_NODE")
	public static int get_DOCUMENT_FRAGMENT_NODE(ScriptableObject obj) {
		return Node.DOCUMENT_FRAGMENT_NODE;
	}

	@JSStatic @JSGetter("NOTATION_NODE")
	public static int get_NOTATION_NODE(ScriptableObject obj) {
		return Node.NOTATION_NODE;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_DISCONNECTED")
	public static int get_DOCUMENT_POSITION_DISCONNECTED(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_DISCONNECTED;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_PRECEDING")
	public static int get_DOCUMENT_POSITION_PRECEDING(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_PRECEDING;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_FOLLOWING")
	public static int get_DOCUMENT_POSITION_FOLLOWING(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_FOLLOWING;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_CONTAINS")
	public static int get_DOCUMENT_POSITION_CONTAINS(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_CONTAINS;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_CONTAINED_BY")
	public static int get_DOCUMENT_POSITION_CONTAINED_BY(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_CONTAINED_BY;
	}

	@JSStatic @JSGetter("DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC")
	public static int get_DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC(ScriptableObject obj) {
		return Node.DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC;
	}

	@JSGetter("nodeName")
	public String getNodeName() {
		return unwrap().getNodeName();
	}

	@JSGetter("nodeValue")
	public String getNodeValue() {
		return unwrap().getNodeValue();
	}

	@JSSetter("nodeValue")
	public void setNodeValue(String val) {
		unwrap().setNodeValue(val);
	}

	@JSGetter("nodeType")
	public int getNodeType() {
		return unwrap().getNodeType();
	}
	
	@JSGetter("parentNode")
	public Object getParentNode() {
		return Context.javaToJS(unwrap().getParentNode(), getParentScope());
	}
	
	@JSGetter("childNodes")
	public Object getChildNodes() {
		// Xerces' NodeImpl also implements NodeList, so getChildNodes() returns
		// the node itself, causing the wrong wrapper to be chosen by javaToJS().
		// To avoid this we wrap it in something that only implements NodeList.
		final NodeList origNodeList = unwrap().getChildNodes();
		NodeList nodeList = new NodeList() {
			public int getLength() {
				return origNodeList.getLength();
			}
			public Node item(int index) {
				return origNodeList.item(index);
			}
		};
		return Context.javaToJS(nodeList, getParentScope());
	}
	
	@JSGetter("firstChild")
	public Object getFirstChild() {
		return Context.javaToJS(unwrap().getFirstChild(), getParentScope());
	}
	
	@JSGetter("lastChild")
	public Object getLastChild() {
		return Context.javaToJS(unwrap().getLastChild(), getParentScope());
	}
	
	@JSGetter("previousSibling")
	public Object getPreviousSibling() {
		return Context.javaToJS(unwrap().getPreviousSibling(), getParentScope());
	}
	
	@JSGetter("nextSibling")
	public Object getNextSibling() {
		return Context.javaToJS(unwrap().getNextSibling(), getParentScope());
	}
	
	@JSGetter("attributes")
	public Object getAttributes() {
		return Context.javaToJS(unwrap().getAttributes(), getParentScope());
	}
	
	@JSGetter("ownerDocument")
	public Object getOwnerDocument() {
		return Context.javaToJS(unwrap().getOwnerDocument(), getParentScope());
	}

	@JSFunction("insertBefore")
	public Object insertBefore(Object newNode, Object refNode) {
		Node result = unwrap().insertBefore(convertArg(newNode, Node.class), convertArg(refNode, Node.class));
		return Context.javaToJS(result, getParentScope());
	}

	@JSFunction("replaceChild")
	public Object replaceChild(Object newNode, Object refNode) {
		Node result = unwrap().replaceChild(convertArg(newNode, Node.class), convertArg(refNode, Node.class));
		return Context.javaToJS(result, getParentScope());
	}

	@JSFunction("removeChild")
	public Object removeChild(Object node) {
		return Context.javaToJS(unwrap().removeChild(convertArg(node, Node.class)), getParentScope());
	}

	@JSFunction("appendChild")
	public Object appendChild(Object node) {
		return Context.javaToJS(unwrap().appendChild(convertArg(node, Node.class)), getParentScope());
	}
	
	@JSFunction("hasChildNodes")
	public boolean hasChildNodes() {
		return unwrap().hasChildNodes();
	}
	
	@JSFunction("cloneNode")
	public Object cloneNode(boolean deep) {
		return Context.javaToJS(unwrap().cloneNode(deep), getParentScope());
	}
	
	@JSFunction("normalize")
	public void normalize() {
		unwrap().normalize();
	}
	
	@JSFunction("isSupported")
	public boolean isSupported(String feature, String version) {
		return unwrap().isSupported(feature, version);
	}

	@JSGetter("namespaceURI")
	public String getNamespaceURI() {
		return unwrap().getNamespaceURI();
	}
	
	@JSGetter("prefix")
	public String getPrefix() {
		return unwrap().getPrefix();
	}
	
	@JSSetter("prefix")
	public void setPrefix(String prefix) {
		unwrap().setPrefix(prefix);
	}
	
	@JSGetter("localName")
	public String getLocalName() {
		return unwrap().getLocalName();
	}
	
	@JSFunction("hasAttributes")
	public boolean hasAttributes() {
		return unwrap().hasAttributes();
	}
	
	@JSGetter("baseURI")
	public String getBaseURI() {
		return unwrap().getBaseURI();
	}
	
	@JSFunction("compareDocumentPosition")
	public int compareDocumentPosition(Object node) {
		return unwrap().compareDocumentPosition(convertArg(node, Node.class));
	}
	
	@JSGetter("textContent")
	public String getTextContent() {
		return unwrap().getTextContent();
	}
	
	@JSSetter("textContent")
	public void setTextContent(String text) {
		unwrap().setTextContent(text);
	}
	
	@JSFunction("isSameNode")
	public boolean isSameNode(Object node) {
		return unwrap().isSameNode(convertArg(node, Node.class));
	}
	
	@JSFunction("lookupNamespaceURI")
	public String lookupNamespaceURI(String uri) {
		return unwrap().lookupNamespaceURI(uri);
	}
	
	@JSFunction("isEqualNode")
	public boolean isEqualNode(Object node) {
		return unwrap().isEqualNode(convertArg(node, Node.class));
	}
	
	@JSFunction("getFeature")
	public Object getFeature(String feature, String version) {
		return Context.javaToJS(unwrap().getFeature(feature, version), getParentScope());
	}

	@JSFunction("isDefaultNamespace")
	public boolean isDefaultNamespace(String namespaceURI) {
		return unwrap().isDefaultNamespace(namespaceURI);
	}

	@JSFunction("lookupPrefix")
	public String lookupPrefix(String namespaceURI) {
		return unwrap().lookupPrefix(namespaceURI);
	}

	@JSFunction("setUserData")
	public Object setUserData(String key, Object data, Object handler) {
		Object result = unwrap().setUserData(key, data, convertArg(handler, UserDataHandler.class));
		return Context.javaToJS(result, getParentScope());
	}

	@JSFunction("getUserData")
	public Object getUserData(String key) {
		return Context.javaToJS(unwrap().getUserData(key), getParentScope());
	}


	
	///// EventTarget interface: /////
	
	@JSFunction("addEventListener")
	public void addEventListener(String type, Object listener, boolean useCapture) {
		checkEventsSupported();
		if(listener instanceof Function) {
			listener = new JSFunctionEventListener(this, (Function)listener);
		}
		if(listener instanceof ScriptableEventListener) {
			listener = ((ScriptableEventListener)listener).unwrap();
		}
		if(!(listener instanceof EventListener)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, "Must supply an EventListener or JavaScript function object.");
		}
		((EventTarget)unwrap()).addEventListener(type, (EventListener)listener, useCapture);
	}
	
	@JSFunction("dispatchEvent")
	public boolean dispatchEvent(Object event) {
		checkEventsSupported();
		return ((EventTarget)unwrap()).dispatchEvent((Event)event);
	}
	
	@JSFunction("removeEventListener")
	public void removeEventListener(String type, Object listener, boolean useCapture) {
		checkEventsSupported();
		if(listener instanceof ScriptableEventListener) {
			listener = ((ScriptableEventListener)listener).unwrap();
		}
		if(!(listener instanceof EventListener)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, "Must supply an EventListener or JavaScript function object.");
		}
		((EventTarget)unwrap()).removeEventListener(type, (EventListener)listener, useCapture);
	}
	
	private void checkEventsSupported() {
		if(!(unwrap() instanceof EventTarget)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR, 
					"This DOM node does not support EventTarget interface methods.");
		}
	}
	
	private class JSFunctionEventListener implements EventListener {
		private ScriptableNode node;
		private Function function;
		
		public JSFunctionEventListener(ScriptableNode node, Function function) {
			this.node = node;
			this.function = function;
		}

		public void handleEvent(Event event) {
			function.call(Context.getCurrentContext(), getParentScope(), node, new Object[] {event});
		}
	}

}
