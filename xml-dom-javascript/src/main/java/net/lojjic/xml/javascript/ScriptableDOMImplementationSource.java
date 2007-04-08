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
public class ScriptableDOMImplementationSource<T extends DOMImplementationSource> extends ScriptableDOMObject<T> {

	public ScriptableDOMImplementationSource() {
		super();
	}

	public ScriptableDOMImplementationSource(Scriptable scope, T domImplementationSource) {
		super(scope, domImplementationSource);
	}

	@JSFunction("getDOMImplementation")
	public Object getDOMImplementation(String features) {
		return Context.javaToJS(unwrap().getDOMImplementation(features), getParentScope());
	}

	@JSFunction("getDOMImplementationList")
	public Object getDOMImplementationList(String features) {
		return Context.javaToJS(unwrap().getDOMImplementationList(features), getParentScope());
	}
}
