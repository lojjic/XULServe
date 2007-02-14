package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xml.javascript.events.ScriptableEventListener;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Node}
 */
@JSClassName("Node")
public class ScriptableNode extends ScriptableDOMObject implements Node {

	protected Node delegateNode;
	
	public Node getDelegateNode() {
		return delegateNode;
	}

	public ScriptableNode(Scriptable scope, Node node) {
		super(scope, node);
		this.delegateNode = node;
	}

	@JSGetter("nodeName")
	public String getNodeName() {
		return delegateNode.getNodeName();
	}

	@JSGetter("nodeValue")
	public String getNodeValue() {
		return delegateNode.getNodeValue();
	}

	@JSSetter("nodeValue")
	public void setNodeValue(String val) {
		delegateNode.setNodeValue(val);
	}

	@JSGetter("nodeType")
	public short getNodeType() {
		return delegateNode.getNodeType();
	}
	
	@JSGetter("parentNode")
	public Node getParentNode() {
		return delegateNode.getParentNode();
	}
	
	@JSGetter("childNodes")
	public NodeList getChildNodes() {
		return delegateNode.getChildNodes();
	}
	
	@JSGetter("firstChild")
	public Node getFirstChild() {
		return delegateNode.getFirstChild();
	}
	
	@JSGetter("lastChild")
	public Node getLastChild() {
		return delegateNode.getLastChild();
	}
	
	@JSGetter("previousSibling")
	public Node getPreviousSibling() {
		return delegateNode.getPreviousSibling();
	}
	
	@JSGetter("nextSibling")
	public Node getNextSibling() {
		return delegateNode.getNextSibling();
	}
	
	@JSGetter("attributes")
	public NamedNodeMap getAttributes() {
		return delegateNode.getAttributes();
	}
	
	@JSGetter("ownerDocument")
	public Document getOwnerDocument() {
		return delegateNode.getOwnerDocument();
	}

	@JSFunction("insertBefore")
	public Node insertBefore(Node newNode, Node refNode) {
		return delegateNode.insertBefore(newNode, refNode);
	}

	@JSFunction("replaceChild")
	public Node replaceChild(Node newNode, Node refNode) {
		return delegateNode.replaceChild(newNode, refNode);
	}

	@JSFunction("removeChild")
	public Node removeChild(Node node) {
		return delegateNode.removeChild(node);
	}

	@JSFunction("appendChild")
	public Node appendChild(Node node) {
		return delegateNode.appendChild(node);
	}
	
	@JSFunction("hasChildNodes")
	public boolean hasChildNodes() {
		return delegateNode.hasChildNodes();
	}
	
	@JSFunction("cloneNode")
	public Node cloneNode(boolean deep) {
		return delegateNode.cloneNode(deep);
	}
	
	@JSFunction("normalize")
	public void normalize() {
		delegateNode.normalize();
	}
	
	@JSFunction("isSupported")
	public boolean isSupported(String feature, String version) {
		return delegateNode.isSupported(feature, version);
	}

	@JSGetter("namespaceURI")
	public String getNamespaceURI() {
		return delegateNode.getNamespaceURI();
	}
	
	@JSGetter("prefix")
	public String getPrefix() {
		return delegateNode.getPrefix();
	}
	
	@JSSetter("prefix")
	public void setPrefix(String prefix) {
		delegateNode.setPrefix(prefix);
	}
	
	@JSGetter("localName")
	public String getLocalName() {
		return delegateNode.getLocalName();
	}
	
	@JSFunction("hasAttributes")
	public boolean hasAttributes() {
		return delegateNode.hasAttributes();
	}
	
	@JSGetter("baseURI")
	public String getBaseURI() {
		return delegateNode.getBaseURI();
	}
	
	@JSFunction("compareDocumentPosition")
	public short compareDocumentPosition(Node node) {
		return delegateNode.compareDocumentPosition(node);
	}
	
	@JSGetter("textContent")
	public String getTextContent() {
		return delegateNode.getTextContent();
	}
	
	@JSSetter("textContent")
	public void setTextContent(String text) {
		delegateNode.setTextContent(text);
	}
	
	@JSFunction("isSameNode")
	public boolean isSameNode(Node node) {
		return delegateNode.isSameNode(node);
	}
	
	@JSFunction("lookupNamespaceURI")
	public String lookupNamespaceURI(String uri) {
		return delegateNode.lookupNamespaceURI(uri);
	}
	
	@JSFunction("isEqualNode")
	public boolean isEqualNode(Node node) {
		return delegateNode.isEqualNode(node);
	}
	
	@JSFunction("getFeature")
	public Object getFeature(String feature, String version) {
		return delegateNode.getFeature(feature, version);
	}

	@JSFunction("isDefaultNamespace")
	public boolean isDefaultNamespace(String namespaceURI) {
		return delegateNode.isDefaultNamespace(namespaceURI);
	}

	@JSFunction("lookupPrefix")
	public String lookupPrefix(String namespaceURI) {
		return delegateNode.lookupPrefix(namespaceURI);
	}

	@JSFunction("setUserData")
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		return delegateNode.setUserData(key, data, handler);
	}

	@JSFunction("getUserData")
	public Object getUserData(String key) {
		return delegateNode.getUserData(key);
	}


	
	///// EventTarget interface: /////
	
	@JSFunction("addEventListener")
	public void addEventListener(String type, Object listener, boolean useCapture) {
		checkEventsSupported();
		if(listener instanceof Function) {
			listener = new JSFunctionEventListener(this, (Function)listener);
		}
		if(listener instanceof ScriptableEventListener) {
			listener = ((ScriptableEventListener)listener).getDelegateEventListener();
		}
		if(!(listener instanceof EventListener)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, "Must supply an EventListener or JavaScript function object.");
		}
		((EventTarget)delegateNode).addEventListener(type, (EventListener)listener, useCapture);
	}
	
	@JSFunction("dispatchEvent")
	public boolean dispatchEvent(Object event) {
		checkEventsSupported();
		return ((EventTarget)delegateNode).dispatchEvent((Event)event);
	}
	
	@JSFunction("removeEventListener")
	public void removeEventListener(String type, Object listener, boolean useCapture) {
		checkEventsSupported();
		if(listener instanceof ScriptableEventListener) {
			listener = ((ScriptableEventListener)listener).getDelegateEventListener();
		}
		if(!(listener instanceof EventListener)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, "Must supply an EventListener or JavaScript function object.");
		}
		((EventTarget)delegateNode).removeEventListener(type, (EventListener)listener, useCapture);
	}
	
	private void checkEventsSupported() {
		if(!(delegateNode instanceof EventTarget)) {
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
