package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULTextBoxElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULTextBoxElement}
 */
@JSClassName("XULTextBoxElement")
public class ScriptableXULTextBoxElement<T extends XULTextBoxElement> extends ScriptableXULControlElement<T> {

	public ScriptableXULTextBoxElement() {
		super();
	}

	public ScriptableXULTextBoxElement(Scriptable scope, T xulTextBoxElement) {
		super(scope, xulTextBoxElement);
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(unwrap().getInputField());
	}

	@JSGetter("maxLength")
	public int getMaxLength() {
		// TODO FIXME casting loses precision
		return (int)unwrap().getMaxLength();
	}

	@JSGetter("selectionEnd")
	public int getSelectionEnd() {
		// TODO FIXME casting loses precision
		return (int)unwrap().getSelectionEnd();
	}

	@JSGetter("selectionStart")
	public int getSelectionStart() {
		// TODO FIXME casting loses precision
		return (int)unwrap().getSelectionStart();
	}

	@JSGetter("textLength")
	public int getTextLength() {
		// TODO FIXME casting loses precision
		return (int)unwrap().getTextLength();
	}

	@JSGetter("type")
	public String getType() {
		return unwrap().getType();
	}

	@JSGetter("value")
	public String getValue() {
		return unwrap().getValue();
	}

	@JSFunction("select")
	public void select() {
		unwrap().select();
	}

	@JSSetter("maxLength")
	public void setMaxLength(int maxLength) {
		unwrap().setMaxLength(maxLength);
	}

	@JSSetter("selectionEnd")
	public void setSelectionEnd(int selectionEnd) {
		unwrap().setSelectionEnd(selectionEnd);
	}

	@JSSetter("setSelectionRange")
	public void setSelectionRange(int selectionStart, int selectionEnd) {
		unwrap().setSelectionRange(selectionStart, selectionEnd);
	}

	@JSSetter("selectionStart")
	public void setSelectionStart(int selectionStart) {
		unwrap().setSelectionStart(selectionStart);
	}

	@JSSetter("type")
	public void setType(String type) {
		unwrap().setType(type);
	}

	@JSSetter("value")
	public void setValue(String value) {
		unwrap().setValue(value);
	}
}
