package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULPopupElement;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULPopupElement}
 */
public class ScriptableXULPopupElement extends ScriptableXULElement {

	private XULPopupElement delegateXULPopupElement;

	public ScriptableXULPopupElement() {
		super();
	}

	public ScriptableXULPopupElement(Scriptable scope, XULPopupElement delegateXULPopupElement) {
		super(scope, delegateXULPopupElement);
		this.delegateXULPopupElement = delegateXULPopupElement;
	}

	@JSGetter("position")
	public String getPosition() {
		return delegateXULPopupElement.getPosition();
	}

	@JSFunction("hidePopup")
	public void hidePopup() {
		delegateXULPopupElement.hidePopup();
	}

	@JSSetter("position")
	public void setPosition(String position) {
		delegateXULPopupElement.setPosition(position);
	}

	@JSFunction("showPopup")
	public void showPopup(int alignment, Object target, Object anchor) {
		delegateXULPopupElement.showPopup(
				(short)alignment, convertArg(target, Element.class), convertArg(anchor, Element.class));
	}
}
