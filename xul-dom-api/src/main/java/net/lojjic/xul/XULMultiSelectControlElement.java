package net.lojjic.xul;

import org.w3c.dom.NodeList;

public interface XULMultiSelectControlElement extends XULSelectControlElement {

	String getSelType();
	void setSelType(String selType);
	
	XULSelectControlItemElement getCurrentItem();
	void setCurrentItem(XULSelectControlItemElement currentItem);
	
	long getCurrentIndex();
	void setCurrentIndex(long currentIndex);
	
	NodeList getSelectedItems(); //readonly
	
	void addItemToSelection(XULSelectControlItemElement item);
	void removeItemFromSelection(XULSelectControlItemElement item);
	void toggleItemSelection(XULSelectControlItemElement item);
	
	void selectItem(XULSelectControlItemElement item);
	void selectItemRange(XULSelectControlItemElement startItem, XULSelectControlItemElement endItem);
	
	void selectAll();
	void invertSelection();
	void clearSelection();
	
	// XXX - temporary, pending implementation of scriptable, 
	//       mutable nsIDOMNodeList for selectedItems
	long getSelectedCount(); //readonly
	XULSelectControlItemElement getSelectedItem(long index);
	
}
