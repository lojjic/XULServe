package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULPopupElement;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULPopupElement}
 */
@JSClassName("XULPopupElement")
public class ScriptableXULPopupElement<T extends XULPopupElement> extends ScriptableXULElement<T> {

	public ScriptableXULPopupElement() {
		super();
	}

	public ScriptableXULPopupElement(Scriptable scope, T xulPopupElement) {
		super(scope, xulPopupElement);
	}

	@JSGetter("position")
	public String getPosition() {
		return unwrap().getPosition();
	}

	@JSFunction("hidePopup")
	public void hidePopup() {
		unwrap().hidePopup();
	}

	@JSSetter("position")
	public void setPosition(String position) {
		unwrap().setPosition(position);
	}

	@JSFunction("showPopup")
	public void showPopup(int alignment, Object target, Object anchor) {
		unwrap().showPopup((short)alignment, convertArg(target, Element.class), convertArg(anchor, Element.class));
	}
}
