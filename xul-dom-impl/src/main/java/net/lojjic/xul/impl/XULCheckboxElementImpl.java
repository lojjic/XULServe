package net.lojjic.xul.impl;

import net.lojjic.xul.XULCheckboxElement;

/**
 * {@link net.lojjic.xul.XULCheckboxElement} implementation
 */
public class XULCheckboxElementImpl extends XULLabeledControlElementImpl implements XULCheckboxElement {

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
		return Boolean.parseBoolean(getAttribute("checked"));
	}

	public void setChecked(boolean checked) {
		setAttribute("checked", String.valueOf(checked));
	}

	public short getCheckState() {
		return Short.parseShort(getAttribute("checkState"));
	}

	public void setCheckState(short checkState) {
		setAttribute("checkState", String.valueOf(checkState));
	}

	public boolean isAutoCheck() {
		return Boolean.parseBoolean(getAttribute("autoCheck"));
	}

	public void setAutoCheck(boolean autoCheck) {
		setAttribute("autoCheck", String.valueOf(autoCheck));
	}
}
