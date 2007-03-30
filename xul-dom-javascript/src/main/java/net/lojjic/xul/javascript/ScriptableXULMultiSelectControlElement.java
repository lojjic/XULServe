package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULMultiSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULMultiSelectControlElement}
 */
public class ScriptableXULMultiSelectControlElement extends ScriptableXULSelectControlElement {

	private XULMultiSelectControlElement delegateXULMultiSelectControlElement;

	public ScriptableXULMultiSelectControlElement() {
		super();
	}

	public ScriptableXULMultiSelectControlElement(Scriptable scope, XULMultiSelectControlElement delegateXULMultiSelectControlElement) {
		super(scope, delegateXULMultiSelectControlElement);
		this.delegateXULMultiSelectControlElement = delegateXULMultiSelectControlElement;
	}

	@JSFunction("addItemToSelection")
	public void addItemToSelection(Object item) {
		delegateXULMultiSelectControlElement.addItemToSelection(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("clearSelection")
	public void clearSelection() {
		delegateXULMultiSelectControlElement.clearSelection();
	}

	@JSGetter("currentIndex")
	public int getCurrentIndex() {
		return delegateXULMultiSelectControlElement.getCurrentIndex();
	}

	@JSGetter("currentItem")
	public Object getCurrentItem() {
		return convertReturnValue(delegateXULMultiSelectControlElement.getCurrentItem());
	}

	@JSGetter("selectedCount")
	public int getSelectedCount() {
		return delegateXULMultiSelectControlElement.getSelectedCount();
	}

	@JSGetter("selectedItem")
	public Object getSelectedItem(int index) {
		return convertReturnValue(delegateXULMultiSelectControlElement.getSelectedItem(index));
	}

	@JSGetter("selectedItems")
	public Object getSelectedItems() {
		return convertReturnValue(delegateXULMultiSelectControlElement.getSelectedItems());
	}

	@JSGetter("selType")
	public String getSelType() {
		return delegateXULMultiSelectControlElement.getSelType();
	}

	@JSFunction("invertSelection")
	public void invertSelection() {
		delegateXULMultiSelectControlElement.invertSelection();
	}

	@JSFunction("removeItemFromSelection")
	public void removeItemFromSelection(Object item) {
		delegateXULMultiSelectControlElement.removeItemFromSelection(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("selectAll")
	public void selectAll() {
		delegateXULMultiSelectControlElement.selectAll();
	}

	@JSFunction("selectItem")
	public void selectItem(Object item) {
		delegateXULMultiSelectControlElement.selectItem(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("selectItemRange")
	public void selectItemRange(Object startItem, Object endItem) {
		delegateXULMultiSelectControlElement.selectItemRange(
				convertArg(startItem, XULSelectControlItemElement.class),
				convertArg(endItem, XULSelectControlItemElement.class)
		);
	}

	@JSSetter("currentIndex")
	public void setCurrentIndex(int currentIndex) {
		delegateXULMultiSelectControlElement.setCurrentIndex(currentIndex);
	}

	@JSSetter("currentItem")
	public void setCurrentItem(Object currentItem) {
		delegateXULMultiSelectControlElement.setCurrentItem(convertArg(currentItem, XULSelectControlItemElement.class));
	}

	@JSSetter("selType")
	public void setSelType(String selType) {
		delegateXULMultiSelectControlElement.setSelType(selType);
	}

	@JSFunction("toggleItemSelection")
	public void toggleItemSelection(Object item) {
		delegateXULMultiSelectControlElement.toggleItemSelection(convertArg(item, XULSelectControlItemElement.class));
	}
}
