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
public class ScriptableDOMErrorHandler extends ScriptableDOMObject {

	private DOMErrorHandler delegateDOMErrorHandler;

	public ScriptableDOMErrorHandler() {
		super();
	}

	public ScriptableDOMErrorHandler(Scriptable scope, DOMErrorHandler delegateDOMErrorHandler) {
		super(scope, delegateDOMErrorHandler);
		this.delegateDOMErrorHandler = delegateDOMErrorHandler;
	}

	@JSFunction("handleError")
	public boolean handleError(Object error) {
		return delegateDOMErrorHandler.handleError(convertArg(error, DOMError.class));
	}
}
