package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.CSSFontFaceRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSFontFaceRule}
 */
@JSClassName("CSSFontFaceRule")
public class ScriptableCSSFontFaceRule<T extends CSSFontFaceRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSFontFaceRule() {
	}

	public ScriptableCSSFontFaceRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSGetter("style")
	public Object getStyle() {
		return convertReturnValue(unwrap().getStyle());
	}
}
