package net.lojjic.xul.impl;

import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

/**
 * DOMImplementation for XUL documents
 */
public class XULDOMImplementation extends DOMImplementationImpl implements DOMImplementation {

	private static XULDOMImplementation singleton = new XULDOMImplementation();

	/**
	 * Return singleton instance
	 */
	public static XULDOMImplementation getInstance() {
		return singleton;
	}

	/**
	 * Private constructor
	 */
	private XULDOMImplementation() {}


	/**
	 * Test if the DOM implementation implements a specific feature and
	 * version, as specified in .
	 *
	 * @param feature The name of the feature to test.
	 * @param version This is the version number of the feature to test.
	 * @return <code>true</code> if the feature is implemented in the
	 *         specified version, <code>false</code> otherwise.
	 */
	public boolean hasFeature(String feature, String version) {
		// TODO handle any additional features
		return super.hasFeature(feature, version);
	}

	/**
	 * Creates a DOM Document object of the specified type with its document
	 * element.
	 * @see org.w3c.dom.DOMImplementation#createDocument(String, String, org.w3c.dom.DocumentType)
	 */
	public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype)
			throws DOMException {
		Document doc = new XULDocumentImpl();
		doc.appendChild(doc.createElementNS(namespaceURI, qualifiedName));
		return doc;
	}

}
