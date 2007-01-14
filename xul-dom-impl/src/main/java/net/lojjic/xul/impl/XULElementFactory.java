package net.lojjic.xul.impl;

import net.lojjic.xul.XULElement;

/**
 * Factory interface for creating XUL element objects
 */
public interface XULElementFactory {

	/**
	 * Create a XULElement instance.
	 * @param ownerDocument The element's owning document
	 * @param qualifiedName The qualified name of the element
	 * @return The XULElement instance
	 */
	XULElement create(XULDocumentImpl ownerDocument, String qualifiedName);

}
