package net.lojjic.xul.impl;

import net.lojjic.xul.XULMultiSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;


/**
 * {@link net.lojjic.xul.XULMultiSelectControlElement} implementation
 */
public class XULMultiSelectControlElementImpl extends XULSelectControlElementImpl implements XULMultiSelectControlElement {

	private SelType selType;
	private int currentIndex;

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULMultiSelectControlElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public String getSelType() {
		return selType.toString();
	}

	public void setSelType(String selType) {
		this.selType = SelType.valueOf(selType);
	}

	public XULSelectControlItemElement getCurrentItem() {
		return items.get(currentIndex);
	}

	public void setCurrentItem(XULSelectControlItemElement currentItem) {
		currentIndex = items.indexOf(currentItem);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public NodeList getSelectedItems()
	{
		return new NodeList() {
			public Node item(int index) {
				return selectedItems.get(index);
			}
			public int getLength() {
				return selectedItems.size();
			}
		};
	}

	public void addItemToSelection(XULSelectControlItemElement item) {
		if(!selectedItems.contains(item)) {
			selectedItems.add(item);
		}
	}

	public void removeItemFromSelection(XULSelectControlItemElement item) {
		selectedItems.remove(item);
	}

	public void toggleItemSelection(XULSelectControlItemElement item) {
		if(selectedItems.contains(item)) {
			selectedItems.remove(item);
		} else {
			selectedItems.add(item);
		}
	}

	public void selectItem(XULSelectControlItemElement item) {
		selectedItems.clear();
		selectedItems.add(item);
	}

	public void selectItemRange(XULSelectControlItemElement startItem, XULSelectControlItemElement endItem) {
		int start = items.indexOf(startItem);
		int end = items.indexOf(endItem);
		if(start == -1 || end == -1) {
			throw new IllegalArgumentException("Both startItem and endItem must be items in this listbox.");
		}
		selectedItems.clear();
		for(int i = Math.min(start, end); i <= Math.max(start, end); i++) {
			selectedItems.add(items.get(i));
		}
	}

	public void selectAll() {
		selectedItems.clear();
		selectedItems.addAll(items);
	}

	public void invertSelection() {
		List<XULSelectControlItemElement> oldSelected = new ArrayList<XULSelectControlItemElement>(selectedItems);
		selectedItems.clear();
		selectedItems.addAll(items);
		selectedItems.removeAll(oldSelected);
	}

	public void clearSelection() {
		selectedItems.clear();
	}
	
	public int getSelectedCount()
	{
		return selectedItems.size();
	}

	public XULSelectControlItemElement getSelectedItem(int index) {
		return selectedItems.get(index);
	}
}
