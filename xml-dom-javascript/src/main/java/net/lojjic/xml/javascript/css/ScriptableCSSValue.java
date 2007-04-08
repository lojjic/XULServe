package net.lojjic.xml.javascript.css;

import org.w3c.dom.css.CSSValue;
import org.w3c.dom.DOMException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSStatic;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSValue}
 */
@JSClassName("CSSValue")
public class ScriptableCSSValue<T extends CSSValue> extends ScriptableDOMObject<T> {

	public ScriptableCSSValue() {
	}

	public ScriptableCSSValue(Scriptable scope, T cssValue) {
		super(scope, cssValue);
	}

	@JSStatic @JSGetter("CSS_INHERIT")
	public static int get_CSS_INHERIT(ScriptableObject obj) {
		return CSSValue.CSS_INHERIT;
	}

	@JSStatic @JSGetter("CSS_PRIMITIVE_VALUE")
	public static int get_CSS_PRIMITIVE_VALUE(ScriptableObject obj) {
		return CSSValue.CSS_PRIMITIVE_VALUE;
	}

	@JSStatic @JSGetter("CSS_VALUE_LIST")
	public static int get_CSS_VALUE_LIST(ScriptableObject obj) {
		return CSSValue.CSS_VALUE_LIST;
	}

	@JSStatic @JSGetter("CSS_CUSTOM")
	public static int get_CSS_CUSTOM(ScriptableObject obj) {
		return CSSValue.CSS_CUSTOM;
	}

	@JSGetter("cssText")
	public String getCssText() {
		return unwrap().getCssText();
	}

	@JSGetter("cssValueType")
	public int getCssValueType() {
		return unwrap().getCssValueType();
	}

	@JSSetter("cssText")
	public void setCssText(String cssText) throws DOMException {
		unwrap().setCssText(cssText);
	}
}
