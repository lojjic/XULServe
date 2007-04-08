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
public class ScriptableDocument<T extends Document> extends ScriptableNode<T> {

	public ScriptableDocument() {
		super();
	}

	public ScriptableDocument(Scriptable scope, T document) {
		super(scope, document);
	}
	
	
	///// Document core interface: /////

	@JSFunction("adoptNode")
	public Object adoptNode(Object source) {
		return Context.javaToJS(unwrap().adoptNode(convertArg(source, Node.class)), getParentScope());
	}

	@JSFunction("createAttribute")
	public Object createAttribute(String name) {
		return Context.javaToJS(unwrap().createAttribute(name), getParentScope());
	}

	@JSFunction("createAttributeNS")
	public Object createAttributeNS(String namespaceURI, String qualifiedName) {
		return Context.javaToJS(unwrap().createAttributeNS(namespaceURI, qualifiedName), getParentScope());
	}

	@JSFunction("createCDATASection")
	public Object createCDATASection(String data) {
		return Context.javaToJS(unwrap().createCDATASection(data), getParentScope());
	}

	@JSFunction("createComment")
	public Object createComment(String data) {
		return Context.javaToJS(unwrap().createComment(data), getParentScope());
	}

	@JSFunction("createDocumentFragment")
	public Object createDocumentFragment() {
		return Context.javaToJS(unwrap().createDocumentFragment(), getParentScope());
	}

	@JSFunction("createElement")
	public Object createElement(String tagName) {
		return Context.javaToJS(unwrap().createElement(tagName), getParentScope());
	}

	@JSFunction("createElementNS")
	public Object createElementNS(String namespaceURI, String qualifiedName) {
		return Context.javaToJS(unwrap().createElementNS(namespaceURI, qualifiedName), getParentScope());
	}

	@JSFunction("createEntityReference")
	public Object createEntityReference(String name) {
		return Context.javaToJS(unwrap().createEntityReference(name), getParentScope());
	}

	@JSFunction("createProcessingInstruction")
	public Object createProcessingInstruction(String target, String data) {
		return Context.javaToJS(unwrap().createProcessingInstruction(target, data), getParentScope());
	}

	@JSFunction("createTextNode")
	public Object createTextNode(String data) {
		return Context.javaToJS(unwrap().createTextNode(data), getParentScope());
	}

	@JSGetter("doctype")
	public Object getDoctype() {
		return Context.javaToJS(unwrap().getDoctype(), getParentScope());
	}

	@JSGetter("documentElement")
	public Object getDocumentElement() {
		return Context.javaToJS(unwrap().getDocumentElement(), getParentScope());
	}

	@JSGetter("documentURI")
	public String getDocumentURI() {
		return unwrap().getDocumentURI();
	}

	@JSGetter("domConfig")
	public Object getDomConfig() {
		return Context.javaToJS(unwrap().getDomConfig(), getParentScope());
	}

	@JSFunction("getElementById")
	public Object getElementById(String elementId) {
		return Context.javaToJS(unwrap().getElementById(elementId), getParentScope());
	}

	@JSFunction("getElementsByTagName")
	public Object getElementsByTagName(String tagname) {
		return Context.javaToJS(unwrap().getElementsByTagName(tagname), getParentScope());
	}

	@JSFunction("getElementsByTagNameNS")
	public Object getElementsByTagNameNS(String namespaceURI, String localName) {
		return Context.javaToJS(unwrap().getElementsByTagNameNS(namespaceURI, localName), getParentScope());
	}

	@JSGetter("implementation")
	public Object getImplementation() {
		return Context.javaToJS(unwrap().getImplementation(), getParentScope());
	}

	@JSGetter("inputEncoding")
	public String getInputEncoding() {
		return unwrap().getInputEncoding();
	}

	@JSGetter("strictErrorChecking")
	public boolean getStrictErrorChecking() {
		return unwrap().getStrictErrorChecking();
	}

	@JSGetter("xmlEncoding")
	public String getXmlEncoding() {
		return unwrap().getXmlEncoding();
	}

	@JSGetter("xmlStandalone")
	public boolean getXmlStandalone() {
		return unwrap().getXmlStandalone();
	}

	@JSGetter("xmlVersion")
	public String getXmlVersion() {
		return unwrap().getXmlVersion();
	}

	@JSFunction("importNode")
	public Object importNode(Object importedNode, boolean deep) {
		return Context.javaToJS(unwrap().importNode(convertArg(importedNode, Node.class), deep), getParentScope());
	}

	@JSFunction("normalizeDocument")
	public void normalizeDocument() {
		unwrap().normalizeDocument();
	}

	@JSFunction("renameNode")
	public Object renameNode(Object n, String namespaceURI, String qualifiedName) {
		return Context.javaToJS(unwrap().renameNode(convertArg(n, Node.class), namespaceURI, qualifiedName), getParentScope());
	}

	@JSSetter("documentURI")
	public void setDocumentURI(String documentURI) {
		unwrap().setDocumentURI(documentURI);
	}

	@JSSetter("strictErrorChecking")
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		unwrap().setStrictErrorChecking(strictErrorChecking);
	}

	@JSSetter("xmlStandalone")
	public void setXmlStandalone(boolean xmlStandalone) {
		unwrap().setXmlStandalone(xmlStandalone);
	}

	@JSSetter("xmlVersion")
	public void setXmlVersion(String xmlVersion) {
		unwrap().setXmlVersion(xmlVersion);
	}

	///// DocumentEvent interface: /////

	@JSFunction("createEvent")
	public Object createEvent(String type) {
		if(!(unwrap() instanceof DocumentEvent)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentEvent interface methods.");
		}
		return Context.javaToJS(((DocumentEvent)unwrap()).createEvent(type), getParentScope());
	}
	
	
	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public Object getDefaultView() {
		if(!(unwrap() instanceof DocumentView)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentView interface methods.");
		}
		return Context.javaToJS(((DocumentView)unwrap()).getDefaultView(), getParentScope());
	}

}
