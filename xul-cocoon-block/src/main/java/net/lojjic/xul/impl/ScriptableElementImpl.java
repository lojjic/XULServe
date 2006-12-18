package net.lojjic.xul.impl;

import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class ScriptableElementImpl extends ScriptableObject implements Element, EventTarget {

	//=================================//
	//=== ScriptableObject methods: ===//
	
	public String getClassName() {
		return getClass().getSimpleName();
	}
	
	public void jsConstructor() {
		// XXX is this needed?
	}
	
	
	
	//==================================//
	//=== Element interface methods: ===//

	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAttribute(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public void removeAttribute(String arg0) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public Attr getAttributeNode(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Attr setAttributeNode(Attr arg0) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public Attr removeAttributeNode(Attr arg0) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeList getElementsByTagName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttributeNS(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAttributeNS(String arg0, String arg1, String arg2) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public void removeAttributeNS(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public Attr getAttributeNodeNS(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public Attr setAttributeNodeNS(Attr arg0) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeList getElementsByTagNameNS(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasAttribute(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasAttributeNS(String arg0, String arg1) throws DOMException {
		// TODO Auto-generated method stub
		return false;
	}

	public TypeInfo getSchemaTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setIdAttribute(String arg0, boolean arg1) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public void setIdAttributeNS(String arg0, String arg1, boolean arg2) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public void setIdAttributeNode(Attr arg0, boolean arg1) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public String getNodeName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNodeValue() throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNodeValue(String arg0) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public short getNodeType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Node getParentNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getFirstChild() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getLastChild() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getPreviousSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getNextSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	public NamedNodeMap getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Document getOwnerDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node insertBefore(Node arg0, Node arg1) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node replaceChild(Node arg0, Node arg1) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node removeChild(Node arg0) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node appendChild(Node arg0) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildNodes() {
		// TODO Auto-generated method stub
		return false;
	}

	public Node cloneNode(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void normalize() {
		// TODO Auto-generated method stub
		
	}

	public boolean isSupported(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPrefix(String arg0) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getBaseURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public short compareDocumentPosition(Node arg0) throws DOMException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getTextContent() throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTextContent(String arg0) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	public boolean isSameNode(Node arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String lookupPrefix(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDefaultNamespace(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String lookupNamespaceURI(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEqualNode(Node arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getFeature(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object setUserData(String arg0, Object arg1, UserDataHandler arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUserData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//======================================//
	//=== EventTarget interface methods: ===//

	public void addEventListener(String arg0, EventListener arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	public void removeEventListener(String arg0, EventListener arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	public boolean dispatchEvent(Event arg0) throws EventException {
		// TODO Auto-generated method stub
		return false;
	}

}
