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
public class ScriptableXULSelectControlElement extends ScriptableXULControlElement {

	private XULSelectControlElement delegateXULSelectControlElement;

	public ScriptableXULSelectControlElement() {
		super();
	}

	public ScriptableXULSelectControlElement(Scriptable scope, XULSelectControlElement delegateXULSelectControlElement) {
		super(scope, delegateXULSelectControlElement);
		this.delegateXULSelectControlElement = delegateXULSelectControlElement;
	}

	@JSFunction("appendItem")
	public Object appendItem(String label, String value) {
		return convertReturnValue(delegateXULSelectControlElement.appendItem(label, value));
	}

	@JSGetter("selectedIndex")
	public int getSelectedIndex() {
		return delegateXULSelectControlElement.getSelectedIndex();
	}

	@JSGetter("selectedItem")
	public Object getSelectedItem() {
		return convertReturnValue(delegateXULSelectControlElement.getSelectedItem());
	}

	@JSGetter("value")
	public String getValue() {
		return delegateXULSelectControlElement.getValue();
	}

	@JSFunction("insertItemAt")
	public Object insertItemAt(int index, String label, String value) {
		return convertReturnValue(delegateXULSelectControlElement.insertItemAt(index, label, value));
	}

	@JSFunction("removeItemAt")
	public Object removeItemAt(int index) {
		return convertReturnValue(delegateXULSelectControlElement.removeItemAt(index));
	}

	@JSSetter("selectedIndex")
	public void setSelectedIndex(int selectedIndex) {
		delegateXULSelectControlElement.setSelectedIndex(selectedIndex);
	}

	@JSSetter("selectedItem")
	public void setSelectedItem(Object selectedItem) {
		delegateXULSelectControlElement.setSelectedItem(convertArg(selectedItem, XULSelectControlItemElement.class));
	}

	@JSSetter("value")
	public void setValue(String value) {
		delegateXULSelectControlElement.setValue(value);
	}
}
