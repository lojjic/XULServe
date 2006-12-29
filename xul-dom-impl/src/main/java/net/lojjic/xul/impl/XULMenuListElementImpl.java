package net.lojjic.xul.impl;

import net.lojjic.xul.XULMenuListElement;
import net.lojjic.xul.XULTextBoxElement;

/**
 * {@link net.lojjic.xul.XULMenuListElement} implementation
 */
public class XULMenuListElementImpl extends XULSelectControlElementImpl implements XULMenuListElement {

	private boolean editable;
	private boolean open;
	private String label;
	private String crop;
	private String image;

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULMenuListElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// For editable menu lists only.
	public XULTextBoxElement getInputField() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
