package net.lojjic.xml.javascript;

import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMError;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMErrorHandler}
 */
@JSClassName("DOMErrorHandler")
public class ScriptableDOMErrorHandler<T extends DOMErrorHandler> extends ScriptableDOMObject<T> {

	public ScriptableDOMErrorHandler() {
		super();
	}

	public ScriptableDOMErrorHandler(Scriptable scope, T domErrorHandler) {
		super(scope, domErrorHandler);
	}

	@JSFunction("handleError")
	public boolean handleError(Object error) {
		return unwrap().handleError(convertArg(error, DOMError.class));
	}
}
