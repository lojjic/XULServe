package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULControlElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULControlElement}
 */
public class ScriptableXULControlElement extends ScriptableXULElement {

	private XULControlElement delegateXULControlElement;

	public ScriptableXULControlElement() {
		super();
	}

	public ScriptableXULControlElement(Scriptable scope, XULControlElement delegateXULControlElement) {
		super(scope, delegateXULControlElement);
		this.delegateXULControlElement = delegateXULControlElement;
	}


	@JSGetter("tabIndex")
	public int getTabIndex() {
		return delegateXULControlElement.getTabIndex();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return delegateXULControlElement.isDisabled();
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		delegateXULControlElement.setDisabled(disabled);
	}

	@JSSetter("tabIndex")
	public void setTabIndex(int tabIndex) {
		delegateXULControlElement.setTabIndex(tabIndex);
	}
}
