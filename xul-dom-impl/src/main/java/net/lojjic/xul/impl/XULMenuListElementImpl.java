package net.lojjic.xul.impl;

import net.lojjic.xul.XULMenuListElement;
import net.lojjic.xul.XULTextBoxElement;
import net.lojjic.xul.XULElement;

/**
 * {@link net.lojjic.xul.XULMenuListElement} implementation
 */
public class XULMenuListElementImpl extends XULSelectControlElementImpl implements XULMenuListElement {

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
		return Boolean.parseBoolean(getAttribute("editable"));
	}

	public void setEditable(boolean editable) {
		setAttribute("editable", String.valueOf(editable));
	}

	public boolean isOpen() {
		return Boolean.parseBoolean(getAttribute("open"));
	}

	public void setOpen(boolean open) {
		setAttribute("open", String.valueOf(open));
	}

	public String getLabel() {
		return getAttribute("label");
	}

	public String getCrop() {
		return getAttribute("crop");
	}

	public void setCrop(String crop) {
		setAttribute("crop", crop);
	}

	public String getImage() {
		return getAttribute("image");
	}

	public void setImage(String image) {
		setAttribute("image", image);
	}

	// For editable menu lists only.
	public XULTextBoxElement getInputField() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULMenuListElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
}
