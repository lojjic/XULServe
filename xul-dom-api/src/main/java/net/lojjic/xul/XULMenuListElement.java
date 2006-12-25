package net.lojjic.xul;

public interface XULMenuListElement extends XULSelectControlElement {

	boolean isEditable();
	void setEditable(boolean editable);
	
	boolean isOpen();
	void setOpen(boolean open);
	
	// label of selected option or value of textfield for editable menu lists
	String getLabel(); //readonly
	
	String getCrop();
	void setCrop(String crop);
	
	String getImage();
	void setImage(String image);
	
	// For editable menu lists only.
	XULTextBoxElement getInputField(); //readonly
	
}
