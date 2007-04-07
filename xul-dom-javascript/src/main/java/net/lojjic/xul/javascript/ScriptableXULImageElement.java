package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xul.XULImageElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULImageElement}
 */
@JSClassName("XULImageElement")
public class ScriptableXULImageElement<T extends XULImageElement> extends ScriptableXULElement<T> {

	public ScriptableXULImageElement() {
		super();
	}

	public ScriptableXULImageElement(Scriptable scope, T xulImageElement) {
		super(scope, xulImageElement);
	}

	@JSGetter("src")
	public String getSrc() {
		return unwrap().getSrc();
	}

	@JSSetter("src")
	public void setSrc(String src) {
		unwrap().setSrc(src);
	}
}
