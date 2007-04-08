package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSCharsetRule;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSCharsetRule}
 */
@JSClassName("CSSCharsetRule")
public class ScriptableCSSCharsetRule<T extends CSSCharsetRule> extends ScriptableCSSRule<T> {

	public ScriptableCSSCharsetRule() {
	}

	public ScriptableCSSCharsetRule(Scriptable scope, T cssRule) {
		super(scope, cssRule);
	}

	@JSGetter("encoding")
	public String getEncoding() {
		return unwrap().getEncoding();
	}

	@JSSetter("encoding")
	public void setEncoding(String encoding) throws DOMException {
		unwrap().setEncoding(encoding);
	}
}
