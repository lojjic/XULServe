package net.lojjic.xul;

public interface XULLabeledControlElement extends XULControlElement {

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
	
}
