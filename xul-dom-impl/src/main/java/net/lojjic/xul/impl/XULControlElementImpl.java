package net.lojjic.xul.impl;

import net.lojjic.xul.XULControlElement;
import net.lojjic.xul.XULElement;

/**
 * {@link net.lojjic.xul.XULControlElement} implementation
 */
public class XULControlElementImpl extends XULElementImpl implements XULControlElement {

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULControlElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public boolean isDisabled() {
		return Boolean.parseBoolean(getAttribute("disabled"));
	}

	public void setDisabled(boolean disabled) {
		setAttribute("disabled", String.valueOf(disabled));
	}

	public int getTabIndex() {
		return Integer.parseInt(getAttribute("tabindex"));
	}

	public void setTabIndex(int tabIndex) {
		setAttribute("tabindex", String.valueOf(tabIndex));
	}


	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULControlElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
}
