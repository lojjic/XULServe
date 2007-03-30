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
public class ScriptableXULCheckboxElement extends ScriptableXULLabeledControlElement {

	private XULCheckboxElement delegateXULCheckboxElement;

	public ScriptableXULCheckboxElement() {
		super();
	}

	public ScriptableXULCheckboxElement(Scriptable scope, XULCheckboxElement delegateXULCheckboxElement) {
		super(scope, delegateXULCheckboxElement);
		this.delegateXULCheckboxElement = delegateXULCheckboxElement;
	}

	@JSGetter("checkState")
	public int getCheckState() {
		return delegateXULCheckboxElement.getCheckState();
	}

	@JSGetter("autoCheck")
	public boolean isAutoCheck() {
		return delegateXULCheckboxElement.isAutoCheck();
	}

	@JSGetter("checked")
	public boolean isChecked() {
		return delegateXULCheckboxElement.isChecked();
	}

	@JSSetter("autoCheck")
	public void setAutoCheck(boolean autoCheck) {
		delegateXULCheckboxElement.setAutoCheck(autoCheck);
	}

	@JSSetter("checked")
	public void setChecked(boolean checked) {
		delegateXULCheckboxElement.setChecked(checked);
	}

	@JSSetter("checkState")
	public void setCheckState(int checkState) {
		delegateXULCheckboxElement.setCheckState((short)checkState);
	}
}
