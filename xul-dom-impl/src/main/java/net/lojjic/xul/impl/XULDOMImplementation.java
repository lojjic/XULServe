package net.lojjic.xul.impl;

import net.lojjic.xul.XULElement;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

import java.util.HashMap;

/**
 * DOMImplementation for XUL documents
 */
public class XULDOMImplementation extends DOMImplementationImpl implements DOMImplementation {

	private HashMap<String, XULElementFactory> elementFactories = new HashMap<String, XULElementFactory>();

	/**
	 * Constructor.
	 */
	public XULDOMImplementation() {
		elementFactories.put("button", XULButtonElementImpl.getFactory());
		elementFactories.put("checkbox", XULCheckboxElementImpl.getFactory());
		elementFactories.put("description", XULDescriptionElementImpl.getFactory());
		elementFactories.put("image", XULImageElementImpl.getFactory());
		elementFactories.put("label", XULLabelElementImpl.getFactory());
		elementFactories.put("listbox", XULMultiSelectControlElementImpl.getFactory());
		elementFactories.put("listitem", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menu", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menulist", XULMenuListElementImpl.getFactory());
		elementFactories.put("menuitem", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menupopup", XULPopupElementImpl.getFactory());
		elementFactories.put("menuseparator", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("popup", XULPopupElementImpl.getFactory());
		elementFactories.put("textbox", XULTextBoxElementImpl.getFactory());
		elementFactories.put("tree", XULTreeElementImpl.getFactory());
	}

	/**
	 * Create the appropriate XULElement or subclass instance for the given
	 * element name. Additional element name-to-class mappings can be added
	 * with {@link
	 * @param document
	 * @param qualifiedName
	 * @return
	 */
	public XULElement createXULElement(XULDocumentImpl document, String qualifiedName) {
		// Parse out the prefix:
		String localName = qualifiedName;
		int colon = localName.indexOf(":");
		if(colon != -1) {
			localName = localName.substring(colon + 1);
		}

		// Find the factory:
		XULElementFactory factory = elementFactories.get(localName);
		if(factory == null) {
			factory = XULElementImpl.getFactory();
		}

		// Create the instance:
		return factory.create(document, qualifiedName);
	}


	/**
	 * Add a XULElementFactory to the element name mappings.
	 * @param tagName
	 * @param factory
	 */
	public void addXULElementFactoryMapping(String tagName, XULElementFactory factory) {
		elementFactories.put(tagName, factory);
	}


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
		Document doc = new XULDocumentImpl(this);
		doc.appendChild(doc.createElementNS(namespaceURI, qualifiedName));
		return doc;
	}

}
