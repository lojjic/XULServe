package net.lojjic.xul;

public interface XULCheckboxElement extends XULLabeledControlElement {

	final static short CHECKSTATE_UNCHECKED = 0;
	final static short CHECKSTATE_CHECKED = 1;
	final static short CHECKSTATE_MIXED = 2;
	
	boolean isChecked();
	void setChecked(boolean checked);
	
	short getCheckState();
	void setCheckState(short checkState);
	
	boolean isAutoCheck();
	void setAutoCheck(boolean autoCheck);
	
}
