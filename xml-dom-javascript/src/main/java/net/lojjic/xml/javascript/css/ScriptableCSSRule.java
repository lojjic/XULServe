package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.css.CSSRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSRule}
 */
@JSClassName("CSSRule")
public class ScriptableCSSRule<T extends CSSRule> extends ScriptableDOMObject<T> {

	public ScriptableCSSRule() {
	}

	public ScriptableCSSRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSStatic @JSGetter("UNKNOWN_RULE")
	public static int get_UNKNOWN_RULE(ScriptableObject obj) {
		return CSSRule.UNKNOWN_RULE;
	}

	@JSStatic @JSGetter("STYLE_RULE")
	public static int get_STYLE_RULE(ScriptableObject obj) {
		return CSSRule.STYLE_RULE;
	}

	@JSStatic @JSGetter("CHARSET_RULE")
	public static int get_CHARSET_RULE(ScriptableObject obj) {
		return CSSRule.CHARSET_RULE;
	}

	@JSStatic @JSGetter("IMPORT_RULE")
	public static int get_IMPORT_RULE(ScriptableObject obj) {
		return CSSRule.IMPORT_RULE;
	}

	@JSStatic @JSGetter("MEDIA_RULE")
	public static int get_MEDIA_RULE(ScriptableObject obj) {
		return CSSRule.MEDIA_RULE;
	}

	@JSStatic @JSGetter("FONT_FACE_RULE")
	public static int get_FONT_FACE_RULE(ScriptableObject obj) {
		return CSSRule.FONT_FACE_RULE;
	}

	@JSStatic @JSGetter("PAGE_RULE")
	public static int get_(ScriptableObject obj) {
		return CSSRule.PAGE_RULE;
	}

	@JSGetter("cssText")
	public String getCssText() {
		return unwrap().getCssText();
	}

	@JSGetter("parentRule")
	public Object getParentRule() {
		return convertReturnValue(unwrap().getParentRule());
	}

	@JSGetter("parentStyleSheet")
	public Object getParentStyleSheet() {
		return convertReturnValue(unwrap().getParentStyleSheet());
	}

	@JSGetter("type")
	public int getType() {
		return unwrap().getType();
	}

	@JSSetter("cssText")
	public void setCssText(String cssText) {
		unwrap().setCssText(cssText);
	}
}
