package net.lojjic.xul;

public interface XULLabelElement extends XULDescriptionElement {

	String getAccessKey();
	void setAccessKey(String accessKey);
	
	String getControl();
	void setControl(String control);
}
