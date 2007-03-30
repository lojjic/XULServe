package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULButtonElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULButtonElement}
 */
@JSClassName("XULButtonElement")
public class ScriptableXULButtonElement extends ScriptableXULLabeledControlElement {

	private XULButtonElement delegateXULButtonElement;

	public ScriptableXULButtonElement() {
		super();
	}

	public ScriptableXULButtonElement(Scriptable scope, XULButtonElement delegateXULButtonElement) {
		super(scope, delegateXULButtonElement);
		this.delegateXULButtonElement = delegateXULButtonElement;
	}

	@JSGetter("checkState")
	public int getCheckState() {
		return delegateXULButtonElement.getCheckState();
	}

	@JSGetter("dlgType")
	public String getDlgType() {
		return delegateXULButtonElement.getDlgType();
	}

	@JSGetter("group")
	public String getGroup() {
		return delegateXULButtonElement.getGroup();
	}

	@JSGetter("type")
	public String getType() {
		return delegateXULButtonElement.getType();
	}

	@JSGetter("autoCheck")
	public boolean isAutoCheck() {
		return delegateXULButtonElement.isAutoCheck();
	}

	@JSGetter("checked")
	public boolean isChecked() {
		return delegateXULButtonElement.isChecked();
	}

	@JSGetter("open")
	public boolean isOpen() {
		return delegateXULButtonElement.isOpen();
	}

	@JSSetter("autoCheck")
	public void setAutoCheck(boolean autoCheck) {
		delegateXULButtonElement.setAutoCheck(autoCheck);
	}

	@JSSetter("checked")
	public void setChecked(boolean checked) {
		delegateXULButtonElement.setChecked(checked);
	}

	@JSSetter("checkState")
	public void setCheckState(int checkState) {
		delegateXULButtonElement.setCheckState((short)checkState);
	}

	@JSSetter("dlgType")
	public void setDlgType(String dlgType) {
		delegateXULButtonElement.setDlgType(dlgType);
	}

	@JSSetter("group")
	public void setGroup(String group) {
		delegateXULButtonElement.setGroup(group);
	}

	@JSSetter("open")
	public void setOpen(boolean open) {
		delegateXULButtonElement.setOpen(open);
	}

	@JSSetter("type")
	public void setType(String type) {
		delegateXULButtonElement.setType(type);
	}
}
