package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULControlElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULControlElement}
 */
@JSClassName("XULControlElement")
public class ScriptableXULControlElement<T extends XULControlElement> extends ScriptableXULElement<T> {

	public ScriptableXULControlElement() {
		super();
	}

	public ScriptableXULControlElement(Scriptable scope, T xulControlElement) {
		super(scope, xulControlElement);
	}

	@JSGetter("tabIndex")
	public int getTabIndex() {
		return unwrap().getTabIndex();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return unwrap().isDisabled();
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		unwrap().setDisabled(disabled);
	}

	@JSSetter("tabIndex")
	public void setTabIndex(int tabIndex) {
		unwrap().setTabIndex(tabIndex);
	}
}
