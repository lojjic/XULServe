package net.lojjic.xul.impl;

import net.lojjic.xul.XULControlElement;

/**
 * {@link net.lojjic.xul.XULControlElement} implementation
 */
public class XULControlElementImpl extends XULElementImpl implements XULControlElement {

	private boolean disabled;
	private long tabIndex;

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
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public long getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(long tabIndex) {
		this.tabIndex = tabIndex;
	}
}
