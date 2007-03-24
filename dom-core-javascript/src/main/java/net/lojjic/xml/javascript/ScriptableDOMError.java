package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.DOMError;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMError}
 */
@JSClassName("DOMError")
public class ScriptableDOMError extends ScriptableDOMObject {

	private DOMError delegateDOMError;

	public ScriptableDOMError(Scriptable scope, DOMError delegateDOMError) {
		super(scope, delegateDOMError);
		this.delegateDOMError = delegateDOMError;
	}

	@JSStatic @JSGetter("SEVERITY_WARNING")
	public static short get_SEVERITY_WARNING(ScriptableObject obj) {
		return DOMError.SEVERITY_WARNING;
	}

	@JSStatic @JSGetter("SEVERITY_ERROR")
	public static short get_SEVERITY_ERROR(ScriptableObject obj) {
		return DOMError.SEVERITY_ERROR;
	}

	@JSStatic @JSGetter("SEVERITY_FATAL_ERROR")
	public static short get_SEVERITY_FATAL_ERROR(ScriptableObject obj) {
		return DOMError.SEVERITY_FATAL_ERROR;
	}

	@JSGetter("location")
	public Object getLocation() {
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
