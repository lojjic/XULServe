package net.lojjic.xul;

public interface XULSelectControlItemElement extends XULElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	String getCrop();
	void setCrop(String crop);
	
	String getImage();
	void setImage(String image);
	
	String getLabel();
	void setLabel(String label);
	
	String getAccessKey();
	void setAccessKey(String accessKey);
	
	String getCommand();
	void setCommand(String command);
	
	String getValue();
	void setValue(String value);
	
	boolean isSelected(); //readonly
	
	XULSelectControlElement getControl(); //readonly
	
}
