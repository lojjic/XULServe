package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULMenuListElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULMenuListElement}
 */
public class ScriptableXULMenuListElement extends ScriptableXULSelectControlElement {

	private XULMenuListElement delegateXULMenuListElement;

	public ScriptableXULMenuListElement() {
		super();
	}

	public ScriptableXULMenuListElement(Scriptable scope, XULMenuListElement delegateXULMenuListElement) {
		super(scope, delegateXULMenuListElement);
		this.delegateXULMenuListElement = delegateXULMenuListElement;
	}

	@JSGetter("crop")
	public String getCrop() {
		return delegateXULMenuListElement.getCrop();
	}

	@JSGetter("image")
	public String getImage() {
		return delegateXULMenuListElement.getImage();
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(delegateXULMenuListElement.getInputField());
	}

	@JSGetter("label")
	public String getLabel() {
		return delegateXULMenuListElement.getLabel();
	}

	@JSGetter("editable")
	public boolean isEditable() {
		return delegateXULMenuListElement.isEditable();
	}

	@JSGetter("open")
	public boolean isOpen() {
		return delegateXULMenuListElement.isOpen();
	}

	@JSSetter("crop")
	public void setCrop(String crop) {
		delegateXULMenuListElement.setCrop(crop);
	}

	@JSSetter("editable")
	public void setEditable(boolean editable) {
		delegateXULMenuListElement.setEditable(editable);
	}

	@JSSetter("image")
	public void setImage(String image) {
		delegateXULMenuListElement.setImage(image);
	}

	@JSSetter("open")
	public void setOpen(boolean open) {
		delegateXULMenuListElement.setOpen(open);
	}
}
