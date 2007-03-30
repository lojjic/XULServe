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
public class ScriptableXULLabelElement extends ScriptableXULDescriptionElement {

	private XULLabelElement delegateXULLabelElement;

	public ScriptableXULLabelElement() {
		super();
	}

	public ScriptableXULLabelElement(Scriptable scope, XULLabelElement delegateXULLabelElement) {
		super(scope, delegateXULLabelElement);
		this.delegateXULLabelElement = delegateXULLabelElement;
	}

	@JSGetter("accessKey")
	public String getAccessKey() {
		return delegateXULLabelElement.getAccessKey();
	}

	@JSGetter("control")
	public String getControl() {
		return delegateXULLabelElement.getControl();
	}

	@JSSetter("accessKey")
	public void setAccessKey(String accessKey) {
		delegateXULLabelElement.setAccessKey(accessKey);
	}

	@JSSetter("control")
	public void setControl(String control) {
		delegateXULLabelElement.setControl(control);
	}
}
