package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULSelectControlElement;
import net.lojjic.xul.XULSelectControlItemElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULSelectControlElement}
 */
@JSClassName("XULSelectControlElement")
public class ScriptableXULSelectControlElement<T extends XULSelectControlElement> extends ScriptableXULControlElement<T> {

	public ScriptableXULSelectControlElement() {
		super();
	}

	public ScriptableXULSelectControlElement(Scriptable scope, T xulSelectControlElement) {
		super(scope, xulSelectControlElement);
	}

	@JSFunction("appendItem")
	public Object appendItem(String label, String value) {
		return convertReturnValue(unwrap().appendItem(label, value));
	}

	@JSGetter("selectedIndex")
	public int getSelectedIndex() {
		return unwrap().getSelectedIndex();
	}

	@JSGetter("selectedItem")
	public Object getSelectedItem() {
		return convertReturnValue(unwrap().getSelectedItem());
	}

	@JSGetter("value")
	public String getValue() {
		return unwrap().getValue();
	}

	@JSFunction("insertItemAt")
	public Object insertItemAt(int index, String label, String value) {
		return convertReturnValue(unwrap().insertItemAt(index, label, value));
	}

	@JSFunction("removeItemAt")
	public Object removeItemAt(int index) {
		return convertReturnValue(unwrap().removeItemAt(index));
	}

	@JSSetter("selectedIndex")
	public void setSelectedIndex(int selectedIndex) {
		unwrap().setSelectedIndex(selectedIndex);
	}

	@JSSetter("selectedItem")
	public void setSelectedItem(Object selectedItem) {
		unwrap().setSelectedItem(convertArg(selectedItem, XULSelectControlItemElement.class));
	}

	@JSSetter("value")
	public void setValue(String value) {
		unwrap().setValue(value);
	}
}
