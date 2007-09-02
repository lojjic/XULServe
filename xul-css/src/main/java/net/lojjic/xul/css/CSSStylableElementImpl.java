package net.lojjic.xul.css;

import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.w3c.dom.DOMException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Implementation of {@link org.apache.batik.css.engine.CSSStylableElement}
 */
public class CSSStylableElementImpl extends ElementNSImpl implements CSSStylableElement {

	protected HashMap<String,StyleMap> styleMaps;

	public CSSStylableElementImpl(DocumentImpl documentXBL, String namespaceURI, String qualifiedName)
			throws DOMException {
		super(documentXBL, namespaceURI, qualifiedName);
	}

	/**
	 * Returns the computed style of this element/pseudo-element.
	 */
	public StyleMap getComputedStyleMap(String pseudoElement) {
		return (styleMaps == null ? null : styleMaps.get(pseudoElement));
	}

	/**
	 * Sets the computed style of this element/pseudo-element.
	 */
	public void setComputedStyleMap(String pseudoElement, StyleMap sm) {
		if(styleMaps == null) {
			styleMaps = new HashMap<String,StyleMap>();
		}
		styleMaps.put(pseudoElement, sm);
	}

	/**
	 * Returns the ID of this element.
	 * Individual Element implementations must override this if they provide ID capability
	 */
	public String getXMLId() {
		return null;
	}

	/**
	 * Returns the class of this element.
	 * Individual Element implementations must override this if they provide class capability
	 */
	public String getCSSClass() {
		return null;
	}

	/**
	 * Returns the CSS base URL of this element.
	 */
	public URL getCSSBase() {
		try {
			return new URL(getBaseURI());
		} catch (MalformedURLException e) {
			throw new DOMException(DOMException.SYNTAX_ERR, "Malformed URL: " + getBaseURI());
		}
	}

	/**
	 * Tells whether this element is an instance of the given pseudo
	 * class.
	 */
	public boolean isPseudoInstanceOf(String pseudoClass) {
		return false;
	}
}
