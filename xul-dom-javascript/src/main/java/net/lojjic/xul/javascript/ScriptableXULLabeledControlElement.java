package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULLabeledControlElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULLabeledControlElement}
 */
@JSClassName("XULLabeledControlElement")
public class ScriptableXULLabeledControlElement<T extends XULLabeledControlElement> extends ScriptableXULControlElement<T> {

	public ScriptableXULLabeledControlElement() {
		super();
	}

	public ScriptableXULLabeledControlElement(Scriptable scope, T xulLabeledControlElement) {
		super(scope, xulLabeledControlElement);
	}

	@JSGetter("accesskey")
	public String getAccessKey() {
		return unwrap().getAccessKey();
	}

	@JSGetter("command")
	public String getCommand() {
		return unwrap().getCommand();
	}

	@JSGetter("crop")
	public String getCrop() {
		return unwrap().getCrop();
	}

	@JSGetter("image")
	public String getImage() {
		return unwrap().getImage();
	}

	@JSGetter("label")
	public String getLabel() {
		return unwrap().getLabel();
	}

	@JSSetter("accesskey")
	public void setAccessKey(String accessKey) {
		unwrap().setAccessKey(accessKey);
	}

	@JSSetter("command")
	public void setCommand(String command) {
		unwrap().setCommand(command);
	}

	@JSSetter("crop")
	public void setCrop(String crop) {
		unwrap().setCrop(crop);
	}

	@JSSetter("image")
	public void setImage(String image) {
		unwrap().setImage(image);
	}

	@JSSetter("label")
	public void setLabel(String label) {
		unwrap().setLabel(label);
	}
}
