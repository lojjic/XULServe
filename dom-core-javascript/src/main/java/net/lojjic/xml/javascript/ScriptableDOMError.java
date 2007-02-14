package net.lojjic.xml.javascript;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMLocator;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMError}
 */
@JSClassName("DOMError")
public class ScriptableDOMError extends ScriptableDOMObject implements DOMError {

	private DOMError delegateDOMError;

	public ScriptableDOMError(Scriptable scope, DOMError delegateDOMError) {
		super(scope, delegateDOMError);
		this.delegateDOMError = delegateDOMError;
	}

	@JSGetter("location")
	public DOMLocator getLocation() {
		return delegateDOMError.getLocation();
	}

	@JSGetter("message")
	public String getMessage() {
		return delegateDOMError.getMessage();
	}

	@JSGetter("relatedData")
	public Object getRelatedData() {
		return delegateDOMError.getRelatedData();
	}

	@JSGetter("relatedException")
	public Object getRelatedException() {
		return delegateDOMError.getRelatedException();
	}

	@JSGetter("severity")
	public short getSeverity() {
		return delegateDOMError.getSeverity();
	}

	@JSGetter("type")
	public String getType() {
		return delegateDOMError.getType();
	}
}
