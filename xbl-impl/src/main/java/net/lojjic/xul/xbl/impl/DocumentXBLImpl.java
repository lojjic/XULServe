package net.lojjic.xul.xbl.impl;

import net.lojjic.xul.xbl.DocumentXBL;
import net.lojjic.xul.xbl.ElementXBL;
import org.w3c.dom.*;
import org.apache.xerces.dom.DocumentImpl;

/**
 * {@link net.lojjic.xul.xbl.DocumentXBL} implementation
 */
public class DocumentXBLImpl extends DocumentImpl implements DocumentXBL {

	protected BindingManager xblBindingManager = new BindingManager();


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
	public Document loadBindingDocument(String documentURL) {
		try {
			return xblBindingManager.loadBindingDocument(documentURL);
		}
		catch(XBLException e) {
			// TODO log warning?
		}
		return null;
	}

	/**
	 * The bindingDocuments property is a NamedNodeMap of all binding documents loaded by
	 * the bound document. Documents are referenced using their URLs.
	 */
	public NamedNodeMap getBindingDocuments() {
		return xblBindingManager.getBindingDocuments();
	}


	//////////////////////////////////////////////////////
	///// Methods defined in Mozilla implementation: /////

	public void addBinding(Element elt, String bindingURL) {
		((ElementXBL)elt).addBinding(bindingURL);
	}

	public Element getAnonymousElementByAttribute(Element elt, String attrName, String attrValue) {
		NodeList anonNodes = getAnonymousNodes(elt);
		for(int i=0; i<anonNodes.getLength(); i++) {
			Node node = anonNodes.item(i);
			if(node instanceof Element) {
				String val = ((Element)node).getAttribute(attrName);
				if(val != null && val.equals(attrValue)) {
					return (Element)node;
				}
			}
		}
		return null;
	}

	public NodeList getAnonymousNodes(Element elt) {
		return ((ElementXBL)elt).getXblChildNodes();
	}

	public Element getBindingParent(Node node) {
		return ((ElementXBL)node).getBindingOwner();
	}

	public void removeBinding(Element elt, String bindingURL) {
		((ElementXBL)elt).removeBinding(bindingURL);
	}
}
