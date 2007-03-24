package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Context;
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
		return Context.javaToJS(delegateDocument.adoptNode(convertArg(source, Node.class)), getParentScope());
	}

	@JSFunction("createAttribute")
	public Object createAttribute(String name) {
		return Context.javaToJS(delegateDocument.createAttribute(name), getParentScope());
	}

	@JSFunction("createAttributeNS")
	public Object createAttributeNS(String namespaceURI, String qualifiedName) {
		return Context.javaToJS(delegateDocument.createAttributeNS(namespaceURI, qualifiedName), getParentScope());
	}

	@JSFunction("createCDATASection")
	public Object createCDATASection(String data) {
		return Context.javaToJS(delegateDocument.createCDATASection(data), getParentScope());
	}

	@JSFunction("createComment")
	public Object createComment(String data) {
		return Context.javaToJS(delegateDocument.createComment(data), getParentScope());
	}

	@JSFunction("createDocumentFragment")
	public Object createDocumentFragment() {
		return Context.javaToJS(delegateDocument.createDocumentFragment(), getParentScope());
	}

	@JSFunction("createElement")
	public Object createElement(String tagName) {
		return Context.javaToJS(delegateDocument.createElement(tagName), getParentScope());
	}

	@JSFunction("createElementNS")
	public Object createElementNS(String namespaceURI, String qualifiedName) {
		return Context.javaToJS(delegateDocument.createElementNS(namespaceURI, qualifiedName), getParentScope());
	}

	@JSFunction("createEntityReference")
	public Object createEntityReference(String name) {
		return Context.javaToJS(delegateDocument.createEntityReference(name), getParentScope());
	}

	@JSFunction("createProcessingInstruction")
	public Object createProcessingInstruction(String target, String data) {
		return Context.javaToJS(delegateDocument.createProcessingInstruction(target, data), getParentScope());
	}

	@JSFunction("createTextNode")
	public Object createTextNode(String data) {
		return Context.javaToJS(delegateDocument.createTextNode(data), getParentScope());
	}

	@JSGetter("doctype")
	public Object getDoctype() {
		return Context.javaToJS(delegateDocument.getDoctype(), getParentScope());
	}

	@JSGetter("documentElement")
	public Object getDocumentElement() {
		return Context.javaToJS(delegateDocument.getDocumentElement(), getParentScope());
	}

	@JSGetter("documentURI")
	public String getDocumentURI() {
		return delegateDocument.getDocumentURI();
	}

	@JSGetter("domConfig")
	public Object getDomConfig() {
		return Context.javaToJS(delegateDocument.getDomConfig(), getParentScope());
	}

	@JSFunction("getElementById")
	public Object getElementById(String elementId) {
		return Context.javaToJS(delegateDocument.getElementById(elementId), getParentScope());
	}

	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String tagname) {
		return Context.javaToJS(delegateDocument.getElementsByTagName(tagname), getParentScope());
	}

	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String namespaceURI, String localName) {
		return Context.javaToJS(delegateDocument.getElementsByTagNameNS(namespaceURI, localName), getParentScope());
	}

	@JSGetter("implementation")
	public Object getImplementation() {
		return Context.javaToJS(delegateDocument.getImplementation(), getParentScope());
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
		return Context.javaToJS(delegateDocument.importNode(convertArg(importedNode, Node.class), deep), getParentScope());
	}

	@JSFunction("normalizeDocument")
	public void normalizeDocument() {
		delegateDocument.normalizeDocument();
	}

	@JSFunction("renameNode")
	public Object renameNode(Object n, String namespaceURI, String qualifiedName) {
		return Context.javaToJS(delegateDocument.renameNode(convertArg(n, Node.class), namespaceURI, qualifiedName), getParentScope());
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
		return Context.javaToJS(((DocumentEvent)delegateDocument).createEvent(type), getParentScope());
	}
	
	
	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public Object getDefaultView() {
		if(!(delegateDocument instanceof DocumentView)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentView interface methods.");
		}
		return Context.javaToJS(((DocumentView)delegateDocument).getDefaultView(), getParentScope());
	}

}
