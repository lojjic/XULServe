package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULTreeElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULTreeElement}
 */
public class ScriptableXULTreeElement extends ScriptableXULElement {

	private XULTreeElement delegateXULTreeElement;

	public ScriptableXULTreeElement() {
		super();
	}

	public ScriptableXULTreeElement(Scriptable scope, XULTreeElement delegateXULTreeElement) {
		super(scope, delegateXULTreeElement);
		this.delegateXULTreeElement = delegateXULTreeElement;
	}

	@JSGetter("body")
	public Object getBody() {
		return convertReturnValue(delegateXULTreeElement.getBody());
	}

	@JSGetter("inputField")
	public Object getInputField() {
		return convertReturnValue(delegateXULTreeElement.getInputField());
	}

	@JSGetter("editable")
	public boolean isEditable() {
		return delegateXULTreeElement.isEditable();
	}

	@JSSetter("editable")
	public void setEditable(boolean editable) {
		delegateXULTreeElement.setEditable(editable);
	}
}
