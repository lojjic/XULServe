package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;
import org.w3c.dom.css.DocumentCSS;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.DocumentCSS}
 */
public class ScriptableDocumentCSS<T extends DocumentCSS> extends ScriptableDOMObject<T> {

	public ScriptableDocumentCSS() {
	}

	public ScriptableDocumentCSS(Scriptable scope, T documentCSS) {
		super(scope, documentCSS);
	}

	@JSFunction("getOverrideStyle")
	public Object getOverrideStyle(Object elt, String pseudoElt) {
		return convertReturnValue(unwrap().getOverrideStyle(convertArg(elt, Element.class), pseudoElt));
	}
}
