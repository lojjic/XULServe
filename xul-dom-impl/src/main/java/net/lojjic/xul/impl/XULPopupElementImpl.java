package net.lojjic.xul.impl;

import net.lojjic.xul.XULPopupElement;
import org.w3c.dom.Element;

/**
 * {@link net.lojjic.xul.XULPopupElement} implementation
 */
public class XULPopupElementImpl extends XULElementImpl implements XULPopupElement {

	private String position;

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
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void showPopup(short alignment, Element target, Element anchor) {
		setHidden(false);
		// TODO handle alignment
	}

	public void hidePopup() {
		setHidden(true);
	}
}
