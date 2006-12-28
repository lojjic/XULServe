package net.lojjic.xul.impl;

import net.lojjic.xul.XULSelectControlItemElement;
import net.lojjic.xul.XULSelectControlElement;

/**
 * {@link net.lojjic.xul.XULSelectControlItemElement} implementation
 */
public class XULSelectControlItemElementImpl extends XULElementImpl implements XULSelectControlItemElement {

	private boolean disabled;
	private String crop;
	private String image;
	private String label;
	private String accessKey;
	private String command;
	private String value;


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
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
