package net.lojjic.xul;

public interface XULSelectControlElement extends XULControlElement {

	XULSelectControlItemElement getSelectedItem();
	void setSelectedItem(XULSelectControlItemElement selectedItem);
	
	int getSelectedIndex();
	void setSelectedIndex(int selectedIndex);
	
	String getValue();
	void setValue(String value);
	
	XULSelectControlItemElement appendItem(String label, String value);
	
	XULSelectControlItemElement insertItemAt(int index, String label, String value);
	
	XULSelectControlItemElement removeItemAt(int index);
	
}
