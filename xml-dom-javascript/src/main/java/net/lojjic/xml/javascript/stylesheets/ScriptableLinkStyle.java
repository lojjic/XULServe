package net.lojjic.xml.javascript.stylesheets;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.stylesheets.LinkStyle;

/**
 * Scriptable wrapper for {@link org.w3c.dom.stylesheets.LinkStyle}
 */
@JSClassName("LinkStyle")
public class ScriptableLinkStyle<T extends LinkStyle> extends ScriptableDOMObject<T> {

	public ScriptableLinkStyle() {
	}

	public ScriptableLinkStyle(Scriptable scope, T linkStyle) {
		super(scope, linkStyle);
	}

	@JSGetter("sheet")
	public Object getSheet() {
		return convertReturnValue(unwrap().getSheet());
	}
}
