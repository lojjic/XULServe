package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULMenuListElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULMenuListElement}
 */
@JSClassName("XULMenuListElement")
public class ScriptableXULMenuListElement<T extends XULMenuListElement> extends ScriptableXULSelectControlElement<T> {

	public ScriptableXULMenuListElement() {
		super();
	}

	public ScriptableXULMenuListElement(Scriptable scope, T xulMenuListElement) {
		super(scope, xulMenuListElement);
	}

	@JSGetter("crop")
	public String getCrop() {
		return unwrap().getCrop();
	}

	@JSGetter("image")
	public String getImage() {
		return unwrap().getImage();
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(unwrap().getInputField());
	}

	@JSGetter("label")
	public String getLabel() {
		return unwrap().getLabel();
	}

	@JSGetter("editable")
	public boolean isEditable() {
		return unwrap().isEditable();
	}

	@JSGetter("open")
	public boolean isOpen() {
		return unwrap().isOpen();
	}

	@JSSetter("crop")
	public void setCrop(String crop) {
		unwrap().setCrop(crop);
	}

	@JSSetter("editable")
	public void setEditable(boolean editable) {
		unwrap().setEditable(editable);
	}

	@JSSetter("image")
	public void setImage(String image) {
		unwrap().setImage(image);
	}

	@JSSetter("open")
	public void setOpen(boolean open) {
		unwrap().setOpen(open);
	}
}
