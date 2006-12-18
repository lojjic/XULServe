package net.lojjic.xul;

public interface XULControlElement extends XULElement {

	boolean isDisabled();
	void setDisabled(boolean disabled);
	
	long getTabIndex();
	void setTabIndex(long tabIndex);
	
	// XXX defined in XULElement, but should be defined here
	//  readonly attribute nsIControllers controllers;
	//  void focus();
	//  void blur();
	
}
