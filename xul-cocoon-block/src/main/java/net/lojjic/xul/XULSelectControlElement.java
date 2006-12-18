package net.lojjic.xul;

public interface XULSelectControlElement extends XULControlElement {

	XULSelectControlItemElement getSelectedItem();
	void setSelectedItem(XULSelectControlItemElement selectedItem);
	
	long getSelectedIndex();
	void setSelectedIndex(long selectedIndex);
	
	String getValue();
	void setValue(String value);
	
	XULSelectControlItemElement appendItem(String label, String value);
	
	XULSelectControlItemElement insertItemAt(long index, String label, String value);
	
	XULSelectControlItemElement removeItemAt(long index);
	
}
