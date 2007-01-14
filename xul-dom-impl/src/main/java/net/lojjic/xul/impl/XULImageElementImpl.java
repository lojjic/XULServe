package net.lojjic.xul.impl;

import net.lojjic.xul.XULImageElement;
import net.lojjic.xul.XULElement;

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



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULImageElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
}
