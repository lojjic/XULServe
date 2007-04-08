package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.Rect;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.Rect}
 */
@JSClassName("Rect")
public class ScriptableRect<T extends Rect> extends ScriptableDOMObject<T> {

	public ScriptableRect() {
	}

	public ScriptableRect(Scriptable scope, T Rect) {
		super(scope, Rect);
	}

	@JSGetter("top")
	public Object getTop() {
		return convertReturnValue(unwrap().getTop());
	}

	@JSGetter("right")
	public Object getRight() {
		return convertReturnValue(unwrap().getRight());
	}

	@JSGetter("bottom")
	public Object getBottom() {
		return convertReturnValue(unwrap().getBottom());
	}

	@JSGetter("left")
	public Object getLeft() {
		return convertReturnValue(unwrap().getLeft());
	}
}
