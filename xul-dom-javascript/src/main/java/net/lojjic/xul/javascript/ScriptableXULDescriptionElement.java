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
public class ScriptableXULDescriptionElement<T extends XULDescriptionElement> extends ScriptableXULElement<T> {

	public ScriptableXULDescriptionElement() {
		super();
	}

	public ScriptableXULDescriptionElement(Scriptable scope, T xulDescriptionElement) {
		super(scope, xulDescriptionElement);
	}

	@JSGetter("value")
	public String getValue() {
		return unwrap().getValue();
	}

	@JSGetter("crop")
	public boolean isCrop() {
		return unwrap().isCrop();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return unwrap().isDisabled();
	}

	@JSSetter("crop")
	public void setCrop(boolean crop) {
		unwrap().setCrop(crop);
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		unwrap().setDisabled(disabled);
	}

	@JSSetter("value")
	public void setValue(String value) {
		unwrap().setValue(value);
	}
}
