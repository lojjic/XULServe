package net.lojjic.xul.impl;

import net.lojjic.xul.XULLabeledControlElement;

/**
 * {@link net.lojjic.xul.XULLabeledControlElement} implementation
 */
public class XULLabeledControlElementImpl extends XULControlElementImpl implements XULLabeledControlElement {

	private String crop;
	private String image;
	private String label;
	private String accessKey;
	private String command;

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULLabeledControlElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
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
}
