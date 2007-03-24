package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMImplementationSource;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementationSource}
 */
@JSClassName("DOMImplementationSource")
public class ScriptableDOMImplementationSource extends ScriptableDOMObject {

	private DOMImplementationSource delegateDOMImplementationSource;

	public ScriptableDOMImplementationSource(Scriptable scope, DOMImplementationSource delegateDOMImplementationSource) {
		super(scope, delegateDOMImplementationSource);
		this.delegateDOMImplementationSource = delegateDOMImplementationSource;
	}

	@JSFunction("getDOMImplementation")
	public Object getDOMImplementation(String features) {
		return delegateDOMImplementationSource.getDOMImplementation(features);
	}

	@JSFunction("getDOMImplementationList")
	public Object getDOMImplementationList(String features) {
		return delegateDOMImplementationSource.getDOMImplementationList(features);
	}
}
