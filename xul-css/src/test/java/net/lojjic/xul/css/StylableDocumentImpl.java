package net.lojjic.xul.css;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;

/**
 * {@link org.w3c.dom.Document} implementation that creates elements using
 * the {@link net.lojjic.xul.css.CSSStylableElementImpl} implementation.
 */
public class StylableDocumentImpl extends DocumentImpl {

	@Override
	public Element createElement(String tagName) throws DOMException {
		return new CSSStylableElementImpl(this, null, tagName);
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		return new CSSStylableElementImpl(this, namespaceURI, qualifiedName);
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
		return createElementNS(namespaceURI, qualifiedName);
	}
}
