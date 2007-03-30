package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULDescriptionElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULDescriptionElement}
 */
@JSClassName("XULDescriptionElement")
public class ScriptableXULDescriptionElement extends ScriptableXULElement {

	private XULDescriptionElement delegateXULDescriptionElement;

	public ScriptableXULDescriptionElement() {
		super();
	}

	public ScriptableXULDescriptionElement(Scriptable scope, XULDescriptionElement delegateXULDescriptionElement) {
		super(scope, delegateXULDescriptionElement);
		this.delegateXULDescriptionElement = delegateXULDescriptionElement;
	}

	@JSGetter("value")
	public String getValue() {
		return delegateXULDescriptionElement.getValue();
	}

	@JSGetter("crop")
	public boolean isCrop() {
		return delegateXULDescriptionElement.isCrop();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return delegateXULDescriptionElement.isDisabled();
	}

	@JSSetter("crop")
	public void setCrop(boolean crop) {
		delegateXULDescriptionElement.setCrop(crop);
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		delegateXULDescriptionElement.setDisabled(disabled);
	}

	@JSSetter("value")
	public void setValue(String value) {
		delegateXULDescriptionElement.setValue(value);
	}
}
