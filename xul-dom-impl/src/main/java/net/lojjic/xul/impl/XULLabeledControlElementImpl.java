package net.lojjic.xul.impl;

import net.lojjic.xul.XULLabeledControlElement;

/**
 * {@link net.lojjic.xul.XULLabeledControlElement} implementation
 */
public class XULLabeledControlElementImpl extends XULControlElementImpl implements XULLabeledControlElement {

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

}
