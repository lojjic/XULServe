package net.lojjic.xul.impl;

import net.lojjic.xul.XULImageElement;

/**
 * {@link net.lojjic.xul.XULImageElement} implementation
 */
public class XULImageElementImpl extends XULElementImpl implements XULImageElement {

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULImageElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}


	public String getSrc() {
		return getAttribute("src");
	}

	public void setSrc(String src) {
		setAttribute("src", src);
	}
}
