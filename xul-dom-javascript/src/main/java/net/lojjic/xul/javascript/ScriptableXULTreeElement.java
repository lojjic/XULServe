package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULTreeElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULTreeElement}
 */
@JSClassName("XULTreeElement")
public class ScriptableXULTreeElement<T extends XULTreeElement> extends ScriptableXULElement<T> {

	public ScriptableXULTreeElement() {
		super();
	}

	public ScriptableXULTreeElement(Scriptable scope, T xulTreeElement) {
		super(scope, xulTreeElement);
	}

	@JSGetter("body")
	public Object getBody() {
		return convertReturnValue(unwrap().getBody());
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(unwrap().getInputField());
	}

	@JSGetter("editable")
	public boolean isEditable() {
		return unwrap().isEditable();
	}

	@JSSetter("editable")
	public void setEditable(boolean editable) {
		unwrap().setEditable(editable);
	}
}
