package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMImplementationSource;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementationSource}
 */
@JSClassName("DOMImplementationSource")
public class ScriptableDOMImplementationSource extends ScriptableDOMObject {

	private DOMImplementationSource delegateDOMImplementationSource;

	public ScriptableDOMImplementationSource() {
		super();
	}

	public ScriptableDOMImplementationSource(Scriptable scope, DOMImplementationSource delegateDOMImplementationSource) {
		super(scope, delegateDOMImplementationSource);
		this.delegateDOMImplementationSource = delegateDOMImplementationSource;
	}

	@JSFunction("getDOMImplementation")
	public Object getDOMImplementation(String features) {
		return Context.javaToJS(delegateDOMImplementationSource.getDOMImplementation(features), getParentScope());
	}

	@JSFunction("getDOMImplementationList")
	public Object getDOMImplementationList(String features) {
		return Context.javaToJS(delegateDOMImplementationSource.getDOMImplementationList(features), getParentScope());
	}
}
