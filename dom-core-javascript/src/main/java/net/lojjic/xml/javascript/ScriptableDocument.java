package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.*;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Document}
 */
@JSClassName("Document")
public class ScriptableDocument extends ScriptableNode implements Document {

	protected Document delegateDocument;
	
	public ScriptableDocument(Scriptable scope, Document document) {
		super(scope, document);
		this.delegateDocument = document;
	}
	
	
	///// Document core interface: /////

	@JSFunction("adoptNode")
	public Node adoptNode(Node source) throws DOMException {
		return delegateDocument.adoptNode(source);
	}

	@JSFunction("createAttribute")
	public Attr createAttribute(String name) throws DOMException {
		return delegateDocument.createAttribute(name);
	}

	@JSFunction("createAttributeNS")
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
		return delegateDocument.createAttributeNS(namespaceURI, qualifiedName);
	}

	@JSFunction("createCDATASection")
	public CDATASection createCDATASection(String data) throws DOMException {
		return delegateDocument.createCDATASection(data);
	}

	@JSFunction("createComment")
	public Comment createComment(String data) {
		return delegateDocument.createComment(data);
	}

	@JSFunction("createDocumentFragment")
	public DocumentFragment createDocumentFragment() {
		return delegateDocument.createDocumentFragment();
	}

	@JSFunction("createElement")
	public Element createElement(String tagName) throws DOMException {
		return delegateDocument.createElement(tagName);
	}

	@JSFunction("createElementNS")
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		return delegateDocument.createElementNS(namespaceURI, qualifiedName);
	}

	@JSFunction("createEntityReference")
	public EntityReference createEntityReference(String name) throws DOMException {
		return delegateDocument.createEntityReference(name);
	}

	@JSFunction("createProcessingInstruction")
	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
		return delegateDocument.createProcessingInstruction(target, data);
	}

	@JSFunction("createTextNode")
	public Text createTextNode(String data) {
		return delegateDocument.createTextNode(data);
	}

	@JSGetter("doctype")
	public DocumentType getDoctype() {
		return delegateDocument.getDoctype();
	}

	@JSGetter("documentElement")
	public Element getDocumentElement() {
		return delegateDocument.getDocumentElement();
	}

	@JSGetter("documentURI")
	public String getDocumentURI() {
		return delegateDocument.getDocumentURI();
	}

	@JSGetter("domConfig")
	public DOMConfiguration getDomConfig() {
		return delegateDocument.getDomConfig();
	}

	@JSFunction("getElementById")
	public Element getElementById(String elementId) {
		return delegateDocument.getElementById(elementId);
	}

	@JSFunction("getElementsByTagName")
	public NodeList getElementsByTagName(String tagname) {
		return delegateDocument.getElementsByTagName(tagname);
	}

	@JSFunction("getElementsByTagNameNS")
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		return delegateDocument.getElementsByTagNameNS(namespaceURI, localName);
	}

	@JSGetter("implementation")
	public DOMImplementation getImplementation() {
		return delegateDocument.getImplementation();
	}

	@JSGetter("inputEncoding")
	public String getInputEncoding() {
		return delegateDocument.getInputEncoding();
	}

	@JSGetter("strictErrorChecking")
	public boolean getStrictErrorChecking() {
		return delegateDocument.getStrictErrorChecking();
	}

	@JSGetter("xmlEncoding")
	public String getXmlEncoding() {
		return delegateDocument.getXmlEncoding();
	}

	@JSGetter("xmlStandalone")
	public boolean getXmlStandalone() {
		return delegateDocument.getXmlStandalone();
	}

	@JSGetter("xmlVersion")
	public String getXmlVersion() {
		return delegateDocument.getXmlVersion();
	}

	@JSFunction("importNode")
	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		return delegateDocument.importNode(importedNode, deep);
	}

	@JSFunction("normalizeDocument")
	public void normalizeDocument() {
		delegateDocument.normalizeDocument();
	}

	@JSFunction("renameNode")
	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
		return delegateDocument.renameNode(n, namespaceURI, qualifiedName);
	}

	@JSSetter("documentURI")
	public void setDocumentURI(String documentURI) {
		delegateDocument.setDocumentURI(documentURI);
	}

	@JSSetter("strictErrorChecking")
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		delegateDocument.setStrictErrorChecking(strictErrorChecking);
	}

	@JSSetter("xmlStandalone")
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		delegateDocument.setXmlStandalone(xmlStandalone);
	}

	@JSSetter("xmlVersion")
	public void setXmlVersion(String xmlVersion) throws DOMException {
		delegateDocument.setXmlVersion(xmlVersion);
	}

	///// DocumentEvent interface: /////

	@JSFunction("createEvent")
	public Event createEvent(String type) {
		if(!(delegateDocument instanceof DocumentEvent)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentEvent interface methods.");
		}
		return ((DocumentEvent)delegateDocument).createEvent(type);
	}
	
	
	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public AbstractView getDefaultView() {
		if(!(delegateDocument instanceof DocumentView)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentView interface methods.");
		}
		return ((DocumentView)delegateDocument).getDefaultView();
	}

}
