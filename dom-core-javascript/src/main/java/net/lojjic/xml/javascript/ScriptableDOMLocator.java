package net.lojjic.xml.javascript;

import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMLocator}
 */
@JSClassName("DOMLocator")
public class ScriptableDOMLocator extends ScriptableDOMObject implements DOMLocator {

	private DOMLocator delegateDOMLocator;

	public ScriptableDOMLocator(Scriptable scope, DOMLocator delegateDOMLocator) {
		super(scope, delegateDOMLocator);
		this.delegateDOMLocator = delegateDOMLocator;
	}

	@JSGetter("byteOffset")
	public int getByteOffset() {
		return delegateDOMLocator.getByteOffset();
	}

	@JSGetter("columnNumber")
	public int getColumnNumber() {
		return delegateDOMLocator.getColumnNumber();
	}

	@JSGetter("lineNumber")
	public int getLineNumber() {
		return delegateDOMLocator.getLineNumber();
	}

	@JSGetter("relatedNode")
	public Node getRelatedNode() {
		return delegateDOMLocator.getRelatedNode();
	}

	@JSGetter("uri")
	public String getUri() {
		return delegateDOMLocator.getUri();
	}

	@JSGetter("utf16Offset")
	public int getUtf16Offset() {
		return delegateDOMLocator.getUtf16Offset();
	}
}
