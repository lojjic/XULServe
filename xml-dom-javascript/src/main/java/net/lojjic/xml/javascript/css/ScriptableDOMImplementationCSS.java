package net.lojjic.xml.javascript.css;

import org.w3c.dom.css.DOMImplementationCSS;
import org.mozilla.javascript.Scriptable;
import net.lojjic.xml.javascript.ScriptableDOMImplementation;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.DOMImplementationCSS}
 */
@JSClassName("DOMImplementationCSS")
public class ScriptableDOMImplementationCSS<T extends DOMImplementationCSS> extends ScriptableDOMImplementation<T> {

	public ScriptableDOMImplementationCSS() {
	}

	public ScriptableDOMImplementationCSS(Scriptable scope, T domImplementationCSS) {
		super(scope, domImplementationCSS);
	}

	@JSFunction("createCSSStyleSheet")
	public Object createCSSStyleSheet(String title, String media) {
		return convertReturnValue(unwrap().createCSSStyleSheet(title, media));
	}
}
