package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.CSSUnknownRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSUnknownRule}
 */
@JSClassName("CSSUnknownRule")
public class ScriptableCSSUnknownRule<T extends CSSUnknownRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSUnknownRule() {
	}

	public ScriptableCSSUnknownRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}
}
