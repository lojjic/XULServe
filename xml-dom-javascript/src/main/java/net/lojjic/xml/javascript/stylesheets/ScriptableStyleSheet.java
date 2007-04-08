package net.lojjic.xml.javascript.stylesheets;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.stylesheets.StyleSheet;

/**
 * Scriptable wrapper for {@link org.w3c.dom.stylesheets.StyleSheet}
 */
@JSClassName("StyleSheet")
public class ScriptableStyleSheet<T extends StyleSheet> extends ScriptableDOMObject<T> {

	public ScriptableStyleSheet() {
	}

	public ScriptableStyleSheet(Scriptable scope, T styleSheet) {
		super(scope, styleSheet);
	}

	@JSGetter("disabled")
	public boolean getDisabled() {
		return unwrap().getDisabled();
	}

	@JSGetter("href")
	public String getHref() {
		return unwrap().getHref();
	}

	@JSGetter("media")
	public Object getMedia() {
		return convertReturnValue(unwrap().getMedia());
	}

	@JSGetter("ownerNode")
	public Object getOwnerNode() {
		return convertReturnValue(unwrap().getOwnerNode());
	}

	@JSGetter("parentStyleSheet")
	public Object getParentStyleSheet() {
		return convertReturnValue(unwrap().getParentStyleSheet());
	}

	@JSGetter("title")
	public String getTitle() {
		return unwrap().getTitle();
	}

	@JSGetter("type")
	public String getType() {
		return unwrap().getType();
	}

	@JSSetter("disabled")
	public void setDisabled(boolean disabled) {
		unwrap().setDisabled(disabled);
	}
}
