package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSMediaRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSMediaRule}
 */
@JSClassName("CSSMediaRule")
public class ScriptableCSSMediaRule<T extends CSSMediaRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSMediaRule() {
	}

	public ScriptableCSSMediaRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSFunction("deleteRule")
	public void deleteRule(int index) throws DOMException {
		unwrap().deleteRule(index);
	}

	@JSGetter("cssRules")
	public Object getCssRules() {
		return convertReturnValue(unwrap().getCssRules());
	}

	@JSGetter("media")
	public Object getMedia() {
		return convertReturnValue(unwrap().getMedia());
	}

	@JSFunction("insertRule")
	public int insertRule(String rule, int index) throws DOMException {
		return unwrap().insertRule(rule, index);
	}
}
