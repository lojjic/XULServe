package net.lojjic.xul.impl;

import net.lojjic.xul.XULSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;

import java.util.List;
import java.util.ArrayList;

/**
 * {@link net.lojjic.xul.XULSelectControlElement} implementation
 */
public class XULSelectControlElementImpl extends XULControlElementImpl implements XULSelectControlElement {

	private int selectedIndex;
	private String value;
	private List<XULSelectControlItemElement> items = new ArrayList<XULSelectControlItemElement>();

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
		return items.get(selectedIndex);
	}

	public void setSelectedItem(XULSelectControlItemElement selectedItem) {
		selectedIndex = items.indexOf(selectedItem);
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
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
		return items.remove(index);
	}
}
