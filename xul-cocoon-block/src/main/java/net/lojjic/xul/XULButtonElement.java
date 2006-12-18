package net.lojjic.xul;

public interface XULButtonElement extends XULLabeledControlElement {

	final static short CHECKSTATE_UNCHECKED = 0;
	final static short CHECKSTATE_CHECKED = 1;
	final static short CHECKSTATE_MIXED = 2;
	
	String getType();
	void setType(String type);
	
	String getDlgType();
	void setDlgType(String dlgType);
	
	
	// For buttons of type="menu" only.
	
	boolean isOpen();
	void setOpen(boolean open);
	
	
	// For buttons of type="checkbox" only.
	
	boolean isChecked();
	void setChecked(boolean checked);
	
	short getCheckState();
	void setCheckState(short checkState);
	
	boolean isAutoCheck();
	void setAutoCheck(boolean autoCheck);
	
	
	// For buttons of type="radio" only.
	
	String getGroup();
	void setGroup(String group);
}
