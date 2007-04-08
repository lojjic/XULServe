package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.stylesheets.ScriptableStyleSheet;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSStyleSheet;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSStyleSheet}
 */
@JSClassName("CSSStyleSheet")
public class ScriptableCSSStyleSheet<T extends CSSStyleSheet> extends ScriptableStyleSheet<T> {

	public ScriptableCSSStyleSheet() {
	}

	public ScriptableCSSStyleSheet(Scriptable scope, T cssStyleSheet) {
		super(scope, cssStyleSheet);
	}

	@JSFunction("deleteRule")
	public void deleteRule(int index) throws DOMException {
		unwrap().deleteRule(index);
	}

	@JSGetter("cssRules")
	public Object getCssRules() {
		return convertReturnValue(unwrap().getCssRules());
	}

	@JSGetter("ownerRule")
	public Object getOwnerRule() {
		return convertReturnValue(unwrap().getOwnerRule());
	}

	@JSFunction("insertRule")
	public int insertRule(String rule, int index) throws DOMException {
		return unwrap().insertRule(rule, index);
	}
}
