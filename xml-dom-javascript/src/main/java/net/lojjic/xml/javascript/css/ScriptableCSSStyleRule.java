package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSStyleRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSStyleRule}
 */
@JSClassName("CSSStyleRule")
public class ScriptableCSSStyleRule<T extends CSSStyleRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSStyleRule() {
	}

	public ScriptableCSSStyleRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSGetter("selectorText")
	public String getSelectorText() {
		return unwrap().getSelectorText();
	}

	@JSGetter("style")
	public Object getStyle() {
		return convertReturnValue(unwrap().getStyle());
	}

	@JSSetter("selectorText")
	public void setSelectorText(String selectorText) throws DOMException {
		unwrap().setSelectorText(selectorText);
	}
}
