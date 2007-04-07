package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULMultiSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULMultiSelectControlElement}
 */
@JSClassName("XULMultiSelectControlElement")
public class ScriptableXULMultiSelectControlElement<T extends XULMultiSelectControlElement>
		extends ScriptableXULSelectControlElement<T> {

	public ScriptableXULMultiSelectControlElement() {
		super();
	}

	public ScriptableXULMultiSelectControlElement(Scriptable scope, T xulMultiSelectControlElement) {
		super(scope, xulMultiSelectControlElement);
	}

	@JSFunction("addItemToSelection")
	public void addItemToSelection(Object item) {
		unwrap().addItemToSelection(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("clearSelection")
	public void clearSelection() {
		unwrap().clearSelection();
	}

	@JSGetter("currentIndex")
	public int getCurrentIndex() {
		return unwrap().getCurrentIndex();
	}

	@JSGetter("currentItem")
	public Object getCurrentItem() {
		return convertReturnValue(unwrap().getCurrentItem());
	}

	@JSGetter("selectedCount")
	public int getSelectedCount() {
		return unwrap().getSelectedCount();
	}

	@JSGetter("selectedItem")
	public Object getSelectedItem(int index) {
		return convertReturnValue(unwrap().getSelectedItem(index));
	}

	@JSGetter("selectedItems")
	public Object getSelectedItems() {
		return convertReturnValue(unwrap().getSelectedItems());
	}

	@JSGetter("selType")
	public String getSelType() {
		return unwrap().getSelType();
	}

	@JSFunction("invertSelection")
	public void invertSelection() {
		unwrap().invertSelection();
	}

	@JSFunction("removeItemFromSelection")
	public void removeItemFromSelection(Object item) {
		unwrap().removeItemFromSelection(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("selectAll")
	public void selectAll() {
		unwrap().selectAll();
	}

	@JSFunction("selectItem")
	public void selectItem(Object item) {
		unwrap().selectItem(convertArg(item, XULSelectControlItemElement.class));
	}

	@JSFunction("selectItemRange")
	public void selectItemRange(Object startItem, Object endItem) {
		unwrap().selectItemRange(
				convertArg(startItem, XULSelectControlItemElement.class),
				convertArg(endItem, XULSelectControlItemElement.class)
		);
	}

	@JSSetter("currentIndex")
	public void setCurrentIndex(int currentIndex) {
		unwrap().setCurrentIndex(currentIndex);
	}

	@JSSetter("currentItem")
	public void setCurrentItem(Object currentItem) {
		unwrap().setCurrentItem(convertArg(currentItem, XULSelectControlItemElement.class));
	}

	@JSSetter("selType")
	public void setSelType(String selType) {
		unwrap().setSelType(selType);
	}

	@JSFunction("toggleItemSelection")
	public void toggleItemSelection(Object item) {
		unwrap().toggleItemSelection(convertArg(item, XULSelectControlItemElement.class));
	}
}
