package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.ElementCSSInlineStyle}
 */
@JSClassName("ElementCSSInlineStyle")
public class ScriptableElementCSSInlineStyle<T extends ElementCSSInlineStyle> extends ScriptableDOMObject<T> {

	public ScriptableElementCSSInlineStyle() {
	}

	public ScriptableElementCSSInlineStyle(Scriptable scope, T ElementCSSInlineStyle) {
		super(scope, ElementCSSInlineStyle);
	}

	@JSGetter("style")
	public Object getStyle() {
		return convertReturnValue(unwrap().getStyle());
	}
}
