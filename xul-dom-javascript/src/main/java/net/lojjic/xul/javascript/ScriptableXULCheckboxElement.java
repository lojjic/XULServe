package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULCheckboxElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULCheckboxElement}
 */
@JSClassName("XULCheckboxElement")
public class ScriptableXULCheckboxElement<T extends XULCheckboxElement> extends ScriptableXULLabeledControlElement<T> {

	public ScriptableXULCheckboxElement() {
		super();
	}

	public ScriptableXULCheckboxElement(Scriptable scope, T xulCheckboxElement) {
		super(scope, xulCheckboxElement);
	}

	@JSGetter("checkState")
	public int getCheckState() {
		return unwrap().getCheckState();
	}

	@JSGetter("autoCheck")
	public boolean isAutoCheck() {
		return unwrap().isAutoCheck();
	}

	@JSGetter("checked")
	public boolean isChecked() {
		return unwrap().isChecked();
	}

	@JSSetter("autoCheck")
	public void setAutoCheck(boolean autoCheck) {
		unwrap().setAutoCheck(autoCheck);
	}

	@JSSetter("checked")
	public void setChecked(boolean checked) {
		unwrap().setChecked(checked);
	}

	@JSSetter("checkState")
	public void setCheckState(int checkState) {
		unwrap().setCheckState((short)checkState);
	}
}
