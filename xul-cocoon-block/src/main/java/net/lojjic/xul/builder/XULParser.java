package net.lojjic.xul.builder;


import java.util.Map;

import net.lojjic.xul.XULDocument;
import net.lojjic.xul.XULElement;
import net.lojjic.xul.impl.XULDocumentImpl;

import org.apache.cocoon.util.location.LocationUtils;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XULParser implements ContentHandler {
	
	private Map<String, XULElementBuilder> xulElementBuilders;
	private Locator locator;
	private XULDocument document;
	private Node currentNode;
	private boolean finished = false;
	
	public XULDocument getXULDocument() {
		if(!finished) {
			throw new IllegalStateException("Parsing of the XML document has not finished.");
		}
		return document;
	}

	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	public void startDocument() throws SAXException {
		document = new XULDocumentImpl();
		currentNode = document;
	}

	public void endDocument() throws SAXException {
		finished = true;
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) 
		throws SAXException {
		
		// Find the XULElementBuilder to use for this element:
		XULElementBuilder builder = xulElementBuilders.get(localName);
		if(null == builder) {
			builder = xulElementBuilders.get("*");
		}
		
		try {
			XULElement element = builder.buildXULElement(atts);
			currentNode.appendChild(element);
			currentNode = element;
		} catch (RuntimeException e) {
			throw new SAXException("Error parsing XUL document at " + LocationUtils.toString(LocationUtils.getLocation(locator)), e);
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		currentNode = currentNode.getParentNode();
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		currentNode.appendChild(document.createTextNode(String.valueOf(ch)));
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		characters(ch, start, length);
	}

	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO handle stylesheet declarations?
	}

	public void skippedEntity(String arg0) throws SAXException {
	}
	
}
