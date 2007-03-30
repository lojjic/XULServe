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
public class ScriptableXULSelectControlItemElement extends ScriptableXULElement {

	private XULSelectControlItemElement delegateXULSelectControlItemElement;

	public ScriptableXULSelectControlItemElement() {
		super();
	}

	public ScriptableXULSelectControlItemElement(Scriptable scope, XULSelectControlItemElement delegateXULSelectControlItemElement) {
		super(scope, delegateXULSelectControlItemElement);
		this.delegateXULSelectControlItemElement = delegateXULSelectControlItemElement;
	}

	@JSGetter("accessKey")
	public String getAccessKey() {
		return delegateXULSelectControlItemElement.getAccessKey();
	}

	@JSGetter("command")
	public String getCommand() {
		return delegateXULSelectControlItemElement.getCommand();
	}

	@JSGetter("control")
	public Object getControl() {
		return convertReturnValue(delegateXULSelectControlItemElement.getControl());
	}

	@JSGetter("crop")
	public String getCrop() {
		return delegateXULSelectControlItemElement.getCrop();
	}

	@JSGetter("image")
	public String getImage() {
		return delegateXULSelectControlItemElement.getImage();
	}

	@JSGetter("label")
	public String getLabel() {
		return delegateXULSelectControlItemElement.getLabel();
	}

	@JSGetter("value")
	public String getValue() {
		return delegateXULSelectControlItemElement.getValue();
	}

	@JSGetter("disabled")
	public boolean isDisabled() {
		return delegateXULSelectControlItemElement.isDisabled();
	}

	@JSGetter("selected")
	public boolean isSelected() {
		return delegateXULSelectControlItemElement.isSelected();
	}

	@JSSetter("accessKey")
	public void setAccessKey(String accessKey) {
		delegateXULSelectControlItemElement.setAccessKey(accessKey);
	}

	@JSSetter("command")
	public void setCommand(String command) {
		delegateXULSelectControlItemElement.setCommand(command);
	}

	@JSSetter("crop")
	public void setCrop(String crop) {
		delegateXULSelectControlItemElement.setCrop(crop);
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		delegateXULSelectControlItemElement.setDisabled(disabled);
	}

	@JSSetter("image")
	public void setImage(String image) {
		delegateXULSelectControlItemElement.setImage(image);
	}

	@JSSetter("label")
	public void setLabel(String label) {
		delegateXULSelectControlItemElement.setLabel(label);
	}

	@JSSetter("value")
	public void setValue(String value) {
		delegateXULSelectControlItemElement.setValue(value);
	}
}
