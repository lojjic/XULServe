package net.lojjic.xul.impl;

import net.lojjic.xul.XULCheckboxElement;

/**
 * {@link net.lojjic.xul.XULCheckboxElement} implementation
 */
public class XULCheckboxElementImpl extends XULLabeledControlElementImpl implements XULCheckboxElement {

	private boolean checked;
	private short checkState;
	private boolean autoCheck;

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULCheckboxElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public short getCheckState() {
		return checkState;
	}

	public void setCheckState(short checkState) {
		this.checkState = checkState;
	}

	public boolean isAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(boolean autoCheck) {
		this.autoCheck = autoCheck;
	}
}
