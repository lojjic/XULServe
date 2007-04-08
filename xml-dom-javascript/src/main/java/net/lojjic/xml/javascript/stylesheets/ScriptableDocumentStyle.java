package net.lojjic.xml.javascript.stylesheets;

import org.w3c.dom.stylesheets.DocumentStyle;
import org.mozilla.javascript.Scriptable;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.stylesheets.DocumentStyle}
 */
@JSClassName("DocumentStyle")
public class ScriptableDocumentStyle<T extends DocumentStyle> extends ScriptableDOMObject<T> {

	public ScriptableDocumentStyle() {
	}

	public ScriptableDocumentStyle(Scriptable scope, T documentStyle) {
		super(scope, documentStyle);
	}

	@JSGetter("styleSheets")
	public Object getStyleSheets() {
		return convertReturnValue(unwrap().getStyleSheets());
	}
}
