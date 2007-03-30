package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULTextBoxElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULTextBoxElement}
 */
public class ScriptableXULTextBoxElement extends ScriptableXULControlElement {

	private XULTextBoxElement delegateXULTextBoxElement;

	public ScriptableXULTextBoxElement() {
		super();
	}

	public ScriptableXULTextBoxElement(Scriptable scope, XULTextBoxElement delegateXULTextBoxElement) {
		super(scope, delegateXULTextBoxElement);
		this.delegateXULTextBoxElement = delegateXULTextBoxElement;
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(delegateXULTextBoxElement.getInputField());
	}

	@JSGetter("maxLength")
	public int getMaxLength() {
		// TODO FIXME casting loses precision
		return (int)delegateXULTextBoxElement.getMaxLength();
	}

	@JSGetter("selectionEnd")
	public int getSelectionEnd() {
		// TODO FIXME casting loses precision
		return (int)delegateXULTextBoxElement.getSelectionEnd();
	}

	@JSGetter("selectionStart")
	public int getSelectionStart() {
		// TODO FIXME casting loses precision
		return (int)delegateXULTextBoxElement.getSelectionStart();
	}

	@JSGetter("textLength")
	public int getTextLength() {
		// TODO FIXME casting loses precision
		return (int)delegateXULTextBoxElement.getTextLength();
	}

	@JSGetter("type")
	public String getType() {
		return delegateXULTextBoxElement.getType();
	}

	@JSGetter("value")
	public String getValue() {
		return delegateXULTextBoxElement.getValue();
	}

	@JSFunction("select")
	public void select() {
		delegateXULTextBoxElement.select();
	}

	@JSSetter("maxLength")
	public void setMaxLength(int maxLength) {
		delegateXULTextBoxElement.setMaxLength(maxLength);
	}

	@JSSetter("selectionEnd")
	public void setSelectionEnd(int selectionEnd) {
		delegateXULTextBoxElement.setSelectionEnd(selectionEnd);
	}

	@JSSetter("setSelectionRange")
	public void setSelectionRange(int selectionStart, int selectionEnd) {
		delegateXULTextBoxElement.setSelectionRange(selectionStart, selectionEnd);
	}

	@JSSetter("selectionStart")
	public void setSelectionStart(int selectionStart) {
		delegateXULTextBoxElement.setSelectionStart(selectionStart);
	}

	@JSSetter("type")
	public void setType(String type) {
		delegateXULTextBoxElement.setType(type);
	}

	@JSSetter("value")
	public void setValue(String value) {
		delegateXULTextBoxElement.setValue(value);
	}
}
