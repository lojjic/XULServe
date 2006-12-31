package net.lojjic.xul.xbl.impl;

import net.lojjic.xul.xbl.DocumentXBL;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.apache.xerces.dom.DocumentImpl;

/**
 * {@link net.lojjic.xul.xbl.DocumentXBL} implementation
 */
public class DocumentXBLImpl extends DocumentImpl implements DocumentXBL {

	protected BindingManager xblBindingManager = new BindingManager();


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
	public Document loadBindingDocument(String documentURL) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * The bindingDocuments property is a NamedNodeMap of all binding documents loaded by
	 * the bound document. Documents are referenced using their URLs.
	 */
	public NamedNodeMap getBindingDocuments() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

}
