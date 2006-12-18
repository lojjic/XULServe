package net.lojjic.xul;

public interface XULDescriptionElement extends XULElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	boolean isCrop();
	void setCrop(boolean crop);
	
	String getValue();
	void setValue(String value);

}
