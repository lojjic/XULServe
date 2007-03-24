package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.views.DocumentView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Document}
 */
@JSClassName("Document")
public class ScriptableDocument extends ScriptableNode {

	protected Document delegateDocument;
	
	public ScriptableDocument(Scriptable scope, Document document) {
		super(scope, document);
		this.delegateDocument = document;
	}
	
	
	///// Document core interface: /////

	@JSFunction("adoptNode")
	public Object adoptNode(Object source) {
		return delegateDocument.adoptNode(convertArg(source, Node.class));
	}

	@JSFunction("createAttribute")
	public Object createAttribute(String name) {
		return delegateDocument.createAttribute(name);
	}

	@JSFunction("createAttributeNS")
	public Object createAttributeNS(String namespaceURI, String qualifiedName) {
		return delegateDocument.createAttributeNS(namespaceURI, qualifiedName);
	}

	@JSFunction("createCDATASection")
	public Object createCDATASection(String data) {
		return delegateDocument.createCDATASection(data);
	}

	@JSFunction("createComment")
	public Object createComment(String data) {
		return delegateDocument.createComment(data);
	}

	@JSFunction("createDocumentFragment")
	public Object createDocumentFragment() {
		return delegateDocument.createDocumentFragment();
	}

	@JSFunction("createElement")
	public Object createElement(String tagName) {
		return delegateDocument.createElement(tagName);
	}

	@JSFunction("createElementNS")
	public Object createElementNS(String namespaceURI, String qualifiedName) {
		return delegateDocument.createElementNS(namespaceURI, qualifiedName);
	}

	@JSFunction("createEntityReference")
	public Object createEntityReference(String name) {
		return delegateDocument.createEntityReference(name);
	}

	@JSFunction("createProcessingInstruction")
	public Object createProcessingInstruction(String target, String data) {
		return delegateDocument.createProcessingInstruction(target, data);
	}

	@JSFunction("createTextNode")
	public Object createTextNode(String data) {
		return delegateDocument.createTextNode(data);
	}

	@JSGetter("doctype")
	public Object getDoctype() {
		return delegateDocument.getDoctype();
	}

	@JSGetter("documentElement")
	public Object getDocumentElement() {
		return delegateDocument.getDocumentElement();
	}

	@JSGetter("documentURI")
	public String getDocumentURI() {
		return delegateDocument.getDocumentURI();
	}

	@JSGetter("domConfig")
	public Object getDomConfig() {
		return delegateDocument.getDomConfig();
	}

	@JSFunction("getElementById")
	public Object getElementById(String elementId) {
		return delegateDocument.getElementById(elementId);
	}

	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String tagname) {
		return delegateDocument.getElementsByTagName(tagname);
	}

	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String namespaceURI, String localName) {
		return delegateDocument.getElementsByTagNameNS(namespaceURI, localName);
	}

	@JSGetter("implementation")
	public Object getImplementation() {
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
	public Object importNode(Object importedNode, boolean deep) {
		return delegateDocument.importNode(convertArg(importedNode, Node.class), deep);
	}

	@JSFunction("normalizeDocument")
	public void normalizeDocument() {
		delegateDocument.normalizeDocument();
	}

	@JSFunction("renameNode")
	public Object renameNode(Object n, String namespaceURI, String qualifiedName) {
		return delegateDocument.renameNode(convertArg(n, Node.class), namespaceURI, qualifiedName);
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
	public void setXmlStandalone(boolean xmlStandalone) {
		delegateDocument.setXmlStandalone(xmlStandalone);
	}

	@JSSetter("xmlVersion")
	public void setXmlVersion(String xmlVersion) {
		delegateDocument.setXmlVersion(xmlVersion);
	}

	///// DocumentEvent interface: /////

	@JSFunction("createEvent")
	public Object createEvent(String type) {
		if(!(delegateDocument instanceof DocumentEvent)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentEvent interface methods.");
		}
		return ((DocumentEvent)delegateDocument).createEvent(type);
	}
	
	
	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public Object getDefaultView() {
		if(!(delegateDocument instanceof DocumentView)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentView interface methods.");
		}
		return ((DocumentView)delegateDocument).getDefaultView();
	}

}
