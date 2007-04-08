package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.CSSImportRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSImportRule}
 */
@JSClassName("CSSImportRule")
public class ScriptableCSSImportRule<T extends CSSImportRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSImportRule() {
	}

	public ScriptableCSSImportRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSGetter("href")
	public String getHref() {
		return unwrap().getHref();
	}

	@JSGetter("media")
	public Object getMedia() {
		return convertReturnValue(unwrap().getMedia());
	}

	@JSGetter("styleSheet")
	public Object getStyleSheet() {
		return convertReturnValue(unwrap().getStyleSheet());
	}
}
