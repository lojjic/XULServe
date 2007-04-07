package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMError;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMError}
 */
@JSClassName("DOMError")
public class ScriptableDOMError<T extends DOMError> extends ScriptableDOMObject<T> {

	public ScriptableDOMError() {
		super();
	}

	public ScriptableDOMError(Scriptable scope, T domError) {
		super(scope, domError);
	}

	@JSStatic @JSGetter("SEVERITY_WARNING")
	public static int get_SEVERITY_WARNING(ScriptableObject obj) {
		return DOMError.SEVERITY_WARNING;
	}

	@JSStatic @JSGetter("SEVERITY_ERROR")
	public static int get_SEVERITY_ERROR(ScriptableObject obj) {
		return DOMError.SEVERITY_ERROR;
	}

	@JSStatic @JSGetter("SEVERITY_FATAL_ERROR")
	public static int get_SEVERITY_FATAL_ERROR(ScriptableObject obj) {
		return DOMError.SEVERITY_FATAL_ERROR;
	}

	@JSGetter("location")
	public Object getLocation() {
		return Context.javaToJS(unwrap().getLocation(), getParentScope());
	}

	@JSGetter("message")
	public String getMessage() {
		return unwrap().getMessage();
	}

	@JSGetter("relatedData")
	public Object getRelatedData() {
		return Context.javaToJS(unwrap().getRelatedData(), getParentScope());
	}

	@JSGetter("relatedException")
	public Object getRelatedException() {
		return Context.javaToJS(unwrap().getRelatedException(), getParentScope());
	}

	@JSGetter("severity")
	public int getSeverity() {
		return unwrap().getSeverity();
	}

	@JSGetter("type")
	public String getType() {
		return unwrap().getType();
	}
}
