package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;

public class ScriptableNode extends ScriptableDOMObject {
	
	public static String JS_CLASS = "Node";

	protected Node delegateNode;
	
	public ScriptableNode(Scriptable scope, Node node) {
		super(scope);
		this.delegateNode = node;
	}	
	
	public String jsGet_nodeName() {
		return delegateNode.getNodeName();
	}
	
	public String jsGet_nodeValue() {
		return delegateNode.getNodeValue();
	}
	
	public void jsSet_nodeValue(String val) {
		delegateNode.setNodeValue(val);
	}
	
	public short jsGet_nodeType() {
		return delegateNode.getNodeType();
	}
	
	public ScriptableNode jsGet_parentNode() {
		return new ScriptableNode(getParentScope(), delegateNode.getParentNode());
	}
	
	public ScriptableNodeList jsGet_childNodes() {
		return new ScriptableNodeList(getParentScope(), delegateNode.getChildNodes());
	}
	
	public ScriptableNode jsGet_firstChild() {
		return new ScriptableNode(getParentScope(), delegateNode.getFirstChild());
	}
	
	public ScriptableNode jsGet_lastChild() {
		return new ScriptableNode(getParentScope(), delegateNode.getLastChild());
	}
	
	public ScriptableNode jsGet_previousSibling() {
		return new ScriptableNode(getParentScope(), delegateNode.getPreviousSibling());
	}
	
	public ScriptableNode jsGet_nextSibling() {
		return new ScriptableNode(getParentScope(), delegateNode.getNextSibling());
	}
	
	public Object jsGet_attributes() {
		return null; //TODO need NamedNodeMap wrapper
	}
	
	public ScriptableDocument jsGet_ownerDocument() {
		return new ScriptableDocument(getParentScope(), delegateNode.getOwnerDocument());
	}
	
	public ScriptableNode jsFunction_insertBefore(ScriptableNode newNode, ScriptableNode refNode) {
		delegateNode.insertBefore(newNode.delegateNode, (refNode == null ? null : refNode.delegateNode));
		return newNode;
	}

	public ScriptableNode jsFunction_replaceChild(ScriptableNode newNode, ScriptableNode refNode) {
		delegateNode.replaceChild(newNode.delegateNode, refNode.delegateNode);
		return newNode;
	}

	public ScriptableNode jsFunction_removeChild(ScriptableNode node) {
		delegateNode.removeChild(node.delegateNode);
		return node;
	}

	public ScriptableNode jsFunction_appendChild(ScriptableNode node) {
		return jsFunction_insertBefore(node, null);
	}
	
	public boolean jsFunction_hasChildNodes() {
		return delegateNode.hasChildNodes();
	}
	
	public ScriptableNode jsFunction_cloneNode(boolean deep) {
		return new ScriptableNode(getParentScope(), delegateNode.cloneNode(deep));
	}
	
	public void jsFunction_normalize() {
		delegateNode.normalize();
	}
	
	public boolean jsFunction_isSupported(String feature, String version) {
		return delegateNode.isSupported(feature, version);
	}
	
	public String jsGet_namespaceURI() {
		return delegateNode.getNamespaceURI();
	}
	
	public String jsGet_prefix() {
		return delegateNode.getPrefix();
	}
	
	public void jsSet_prefix(String prefix) {
		delegateNode.setPrefix(prefix);
	}
	
	public String jsGet_localName() {
		return delegateNode.getLocalName();
	}
	
	public boolean jsFunction_hasAttributes() {
		return delegateNode.hasAttributes();
	}
	
	public String jsGet_baseURI() {
		return delegateNode.getBaseURI();
	}
	
	public short jsFunction_compareDocumentPosition(ScriptableNode node) {
		return delegateNode.compareDocumentPosition(node.delegateNode);
	}
	
	public String jsGet_textContent() {
		return delegateNode.getTextContent();
	}
	
	public void jsSet_textContent(String text) {
		delegateNode.setTextContent(text);
	}
	
	public boolean jsFunction_isSameNode(ScriptableNode node) {
		return delegateNode.isSameNode(node.delegateNode);
	}
	
	public String jsFunction_lookupNamespaceURI(String uri) {
		return delegateNode.lookupNamespaceURI(uri);
	}
	
	public boolean jsFunction_isEqualNode(ScriptableNode node) {
		return delegateNode.isEqualNode(node.delegateNode);
	}
	
	public Object jsFunction_getFeature(String feature, String version) {
		return delegateNode.getFeature(feature, version);
	}
	
	/* TODO
	public Object jsFunction_setUserData(String key, Object data, UserDataHandler handler) {
		return delegateNode.setUserData(key, data, handler);
	}
	
	public Object jsFunction_getUserData(String key) {
		return delegateNode.getUserData(key);
	}
	*/

}
