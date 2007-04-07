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
public class ScriptableXULButtonElement<T extends XULButtonElement> extends ScriptableXULLabeledControlElement<T> {

	public ScriptableXULButtonElement() {
		super();
	}

	public ScriptableXULButtonElement(Scriptable scope, T xulButtonElement) {
		super(scope, xulButtonElement);
	}

	@JSGetter("checkState")
	public int getCheckState() {
		return unwrap().getCheckState();
	}

	@JSGetter("dlgType")
	public String getDlgType() {
		return unwrap().getDlgType();
	}

	@JSGetter("group")
	public String getGroup() {
		return unwrap().getGroup();
	}

	@JSGetter("type")
	public String getType() {
		return unwrap().getType();
	}

	@JSGetter("autoCheck")
	public boolean isAutoCheck() {
		return unwrap().isAutoCheck();
	}

	@JSGetter("checked")
	public boolean isChecked() {
		return unwrap().isChecked();
	}

	@JSGetter("open")
	public boolean isOpen() {
		return unwrap().isOpen();
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

	@JSSetter("dlgType")
	public void setDlgType(String dlgType) {
		unwrap().setDlgType(dlgType);
	}

	@JSSetter("group")
	public void setGroup(String group) {
		unwrap().setGroup(group);
	}

	@JSSetter("open")
	public void setOpen(boolean open) {
		unwrap().setOpen(open);
	}

	@JSSetter("type")
	public void setType(String type) {
		unwrap().setType(type);
	}
}
