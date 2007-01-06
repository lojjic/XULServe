package net.lojjic.xul.impl;

import net.lojjic.xul.XULSelectControlItemElement;
import net.lojjic.xul.XULSelectControlElement;

/**
 * {@link net.lojjic.xul.XULSelectControlItemElement} implementation
 */
public class XULSelectControlItemElementImpl extends XULElementImpl implements XULSelectControlItemElement {

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULSelectControlItemElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}


	public boolean isDisabled() {
		return Boolean.parseBoolean(getAttribute("disabled"));
	}

	public void setDisabled(boolean disabled) {
		setAttribute("disabled", String.valueOf(disabled));
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

	public String getLabel() {
		return getAttribute("label");
	}

	public void setLabel(String label) {
		setAttribute("label", label);
	}

	public String getAccessKey() {
		return getAttribute("accesskey");
	}

	public void setAccessKey(String accessKey) {
		setAttribute("accesskey", accessKey);
	}

	public String getCommand() {
		return getAttribute("command");
	}

	public void setCommand(String command) {
		// TODO parse the command script so it can be executed quickly
		setAttribute("command", command);
	}

	public String getValue() {
		return getAttribute("value");
	}

	public void setValue(String value) {
		setAttribute("value", value);
	}

	public boolean isSelected() //readonly
	{
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public XULSelectControlElement getControl() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
