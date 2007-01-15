package net.lojjic.xul.xbl;

import org.w3c.dom.*;

/**
 * DocumentXBL interface.
 * <p/>
 * See http://www.mozilla.org/projects/xbl/xbl.html#dom-documentxbl}
 */
public interface DocumentXBL {

	////////////////////////////////////////////
	///// Methods defined in XBL 1.0 Spec: /////

	/**
	 * The loadBindingDocument method can be used to synchronously obtain the specified
	 * binding document for use within a particular document (the one on which the
	 * loadBindingDocument method was invoked). The binding document can then be modified
	 * programmatically using the DOM. Any subsequent bindings that are attached to elements
	 * within the document will be constructed from the modified binding document.
	 *
	 * @param documentURL The URL of a binding document.
	 * @return The return value of loadBindingDocument is the binding document used by the
	 *         calling document to attach bindings that are defined in the binding document.
	 */
	Document loadBindingDocument(String documentURL);

	/**
	 * The bindingDocuments property is a NamedNodeMap of all binding documents loaded by
	 * the bound document. Documents are referenced using their URLs.
	 */
	NamedNodeMap getBindingDocuments();



	//////////////////////////////////////////////////////
	///// Methods defined in Mozilla implementation: /////

	NodeList getAnonymousNodes(Element elt);

	Element getAnonymousElementByAttribute(Element elt, String attrName, String attrValue);

	void addBinding(Element elt, String bindingURL);

	void removeBinding(Element elt, String bindingURL);

	Element getBindingParent(Node node);

}
