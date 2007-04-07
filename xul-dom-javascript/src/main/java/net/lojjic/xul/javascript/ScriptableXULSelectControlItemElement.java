package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULSelectControlItemElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULSelectControlItemElement}
 */
@JSClassName("XULSelectControlItemElement")
public class ScriptableXULSelectControlItemElement<T extends XULSelectControlItemElement>
		extends ScriptableXULElement<T> {

	public ScriptableXULSelectControlItemElement() {
		super();
	}

	public ScriptableXULSelectControlItemElement(Scriptable scope, T xulSelectControlItemElement) {
		super(scope, xulSelectControlItemElement);
	}

	@JSGetter("accessKey")
	public String getAccessKey() {
		return unwrap().getAccessKey();
	}

	@JSGetter("command")
	public String getCommand() {
		return unwrap().getCommand();
	}

	@JSGetter("control")
	public Object getControl() {
		return convertReturnValue(unwrap().getControl());
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

	@JSGetter("value")
	public String getValue() {
		return unwrap().getValue();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return unwrap().isDisabled();
	}

	@JSGetter("selected")
	public boolean isSelected() {
		return unwrap().isSelected();
	}

	@JSSetter("accessKey")
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

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		unwrap().setDisabled(disabled);
	}

	@JSSetter("image")
	public void setImage(String image) {
		unwrap().setImage(image);
	}

	@JSSetter("label")
	public void setLabel(String label) {
		unwrap().setLabel(label);
	}

	@JSSetter("value")
	public void setValue(String value) {
		unwrap().setValue(value);
	}
}
