package net.lojjic.xul;

import org.w3c.dom.NodeList;

public interface XULMultiSelectControlElement extends XULSelectControlElement {

	static enum SelType { single, multiple }

	String getSelType();
	void setSelType(String selType);
	
	XULSelectControlItemElement getCurrentItem();
	void setCurrentItem(XULSelectControlItemElement currentItem);
	
	int getCurrentIndex();
	void setCurrentIndex(int currentIndex);
	
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
	int getSelectedCount(); //readonly
	XULSelectControlItemElement getSelectedItem(int index);
	
}
