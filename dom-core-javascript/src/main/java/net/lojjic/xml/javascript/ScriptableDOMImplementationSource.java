package net.lojjic.xml.javascript;

import org.w3c.dom.DOMImplementationSource;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementationSource}
 */
@JSClassName("DOMImplementationSource")
public class ScriptableDOMImplementationSource extends ScriptableDOMObject implements DOMImplementationSource {

	private DOMImplementationSource delegateDOMImplementationSource;

	public ScriptableDOMImplementationSource(Scriptable scope, DOMImplementationSource delegateDOMImplementationSource) {
		super(scope, delegateDOMImplementationSource);
		this.delegateDOMImplementationSource = delegateDOMImplementationSource;
	}

	@JSFunction("getDOMImplementation")
	public DOMImplementation getDOMImplementation(String features) {
		return delegateDOMImplementationSource.getDOMImplementation(features);
	}

	@JSFunction("getDOMImplementationList")
	public DOMImplementationList getDOMImplementationList(String features) {
		return delegateDOMImplementationSource.getDOMImplementationList(features);
	}
}
