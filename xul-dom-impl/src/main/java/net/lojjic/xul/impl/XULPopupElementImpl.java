package net.lojjic.xul.impl;

import net.lojjic.xul.XULPopupElement;
import net.lojjic.xul.XULElement;
import org.w3c.dom.Element;

/**
 * {@link net.lojjic.xul.XULPopupElement} implementation
 */
public class XULPopupElementImpl extends XULElementImpl implements XULPopupElement {

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULPopupElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public String getPosition() {
		return getAttribute("position");
	}

	public void setPosition(String position) {
		setAttribute("position", position);
	}

	public void showPopup(short alignment, Element target, Element anchor) {
		setHidden(false);
		// TODO handle alignment
	}

	public void hidePopup() {
		setHidden(true);
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULPopupElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
}
