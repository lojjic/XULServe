package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMLocator;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMLocator}
 */
@JSClassName("DOMLocator")
public class ScriptableDOMLocator<T extends DOMLocator> extends ScriptableDOMObject<T> {

	public ScriptableDOMLocator() {
		super();
	}

	public ScriptableDOMLocator(Scriptable scope, T domLocator) {
		super(scope, domLocator);
	}

	@JSGetter("byteOffset")
	public int getByteOffset() {
		return unwrap().getByteOffset();
	}

	@JSGetter("columnNumber")
	public int getColumnNumber() {
		return unwrap().getColumnNumber();
	}

	@JSGetter("lineNumber")
	public int getLineNumber() {
		return unwrap().getLineNumber();
	}

	@JSGetter("relatedNode")
	public Object getRelatedNode() {
		return Context.javaToJS(unwrap().getRelatedNode(), getParentScope());
	}

	@JSGetter("uri")
	public String getUri() {
		return unwrap().getUri();
	}

	@JSGetter("utf16Offset")
	public int getUtf16Offset() {
		return unwrap().getUtf16Offset();
	}
}
