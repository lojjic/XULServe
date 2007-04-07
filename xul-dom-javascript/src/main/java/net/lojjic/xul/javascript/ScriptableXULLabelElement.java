package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULLabelElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULLabelElement}
 */
@JSClassName("XULLabelElement")
public class ScriptableXULLabelElement<T extends XULLabelElement> extends ScriptableXULDescriptionElement<T> {

	public ScriptableXULLabelElement() {
		super();
	}

	public ScriptableXULLabelElement(Scriptable scope, T xulLabelElement) {
		super(scope, xulLabelElement);
	}

	@JSGetter("accessKey")
	public String getAccessKey() {
		return unwrap().getAccessKey();
	}

	@JSGetter("control")
	public String getControl() {
		return unwrap().getControl();
	}

	@JSSetter("accessKey")
	public void setAccessKey(String accessKey) {
		unwrap().setAccessKey(accessKey);
	}

	@JSSetter("control")
	public void setControl(String control) {
		unwrap().setControl(control);
	}
}
