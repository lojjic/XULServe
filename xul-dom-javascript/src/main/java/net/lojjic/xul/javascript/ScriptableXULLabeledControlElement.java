package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULLabeledControlElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULLabeledControlElement}
 */
public class ScriptableXULLabeledControlElement extends ScriptableXULControlElement {

	private XULLabeledControlElement delegateXULLabeledControlElement;

	public ScriptableXULLabeledControlElement() {
		super();
	}

	public ScriptableXULLabeledControlElement(Scriptable scope, XULLabeledControlElement delegateXULLabeledControlElement) {
		super(scope, delegateXULLabeledControlElement);
		this.delegateXULLabeledControlElement = delegateXULLabeledControlElement;
	}

	@JSGetter("accesskey")
	public String getAccessKey() {
		return delegateXULLabeledControlElement.getAccessKey();
	}

	@JSGetter("command")
	public String getCommand() {
		return delegateXULLabeledControlElement.getCommand();
	}

	@JSGetter("crop")
	public String getCrop() {
		return delegateXULLabeledControlElement.getCrop();
	}

	@JSGetter("image")
	public String getImage() {
		return delegateXULLabeledControlElement.getImage();
	}

	@JSGetter("label")
	public String getLabel() {
		return delegateXULLabeledControlElement.getLabel();
	}

	@JSSetter("accesskey")
	public void setAccessKey(String accessKey) {
		delegateXULLabeledControlElement.setAccessKey(accessKey);
	}

	@JSSetter("command")
	public void setCommand(String command) {
		delegateXULLabeledControlElement.setCommand(command);
	}

	@JSSetter("crop")
	public void setCrop(String crop) {
		delegateXULLabeledControlElement.setCrop(crop);
	}

	@JSSetter("image")
	public void setImage(String image) {
		delegateXULLabeledControlElement.setImage(image);
	}

	@JSSetter("label")
	public void setLabel(String label) {
		delegateXULLabeledControlElement.setLabel(label);
	}
}
