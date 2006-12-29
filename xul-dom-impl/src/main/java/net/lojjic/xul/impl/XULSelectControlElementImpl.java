package net.lojjic.xul.impl;

import net.lojjic.xul.XULSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;

import java.util.List;
import java.util.ArrayList;

/**
 * {@link net.lojjic.xul.XULSelectControlElement} implementation
 */
public class XULSelectControlElementImpl extends XULControlElementImpl implements XULSelectControlElement {

	protected String value;
	protected List<XULSelectControlItemElement> items = new ArrayList<XULSelectControlItemElement>();
	protected List<XULSelectControlItemElement> selectedItems = new ArrayList<XULSelectControlItemElement>();


	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULSelectControlElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}


	public XULSelectControlItemElement getSelectedItem() {
		if(selectedItems.size() == 0) {
			return null;
		}
		return selectedItems.get(0);
	}

	public void setSelectedItem(XULSelectControlItemElement item) {
		selectedItems.clear();
		selectedItems.add(item);
	}

	public int getSelectedIndex() {
		if(selectedItems.size() == 0) {
			return -1;
		}
		return items.indexOf(selectedItems.get(0));
	}

	public void setSelectedIndex(int index) {
		selectedItems.clear();
		selectedItems.add(items.get(index));
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public XULSelectControlItemElement appendItem(String label, String value) {
		return insertItemAt(items.size(), label, value);
	}

	public XULSelectControlItemElement insertItemAt(int index, String label, String value) {
		XULSelectControlItemElement item = new XULSelectControlItemElementImpl(ownerXULDocument, "item");
		item.setLabel(label);
		item.setValue(value);
		items.add(index, item);
		return item;
	}

	public XULSelectControlItemElement removeItemAt(int index) {
		XULSelectControlItemElement element = items.remove(index);
		selectedItems.remove(element);
		return element;
	}
}
