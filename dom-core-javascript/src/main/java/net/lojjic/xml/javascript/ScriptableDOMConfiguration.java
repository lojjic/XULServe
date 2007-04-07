package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMConfiguration;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMConfiguration}
 */
@JSClassName("DOMConfiguration")
public class ScriptableDOMConfiguration<T extends DOMConfiguration> extends ScriptableDOMObject<T> {

	public ScriptableDOMConfiguration() {
		super();
	}

	public ScriptableDOMConfiguration(Scriptable scope, T domConfiguration) {
		super(scope, domConfiguration);
	}

	@JSFunction("canSetParameter")
	public boolean canSetParameter(String name, Object value) {
		return unwrap().canSetParameter(name, value);
	}

	@JSFunction("getParameter")
	public Object getParameter(String name) {
		return Context.javaToJS(unwrap().getParameter(name), getParentScope());
	}

	@JSGetter("parameterNames")
	public Object getParameterNames() {
		return Context.javaToJS(unwrap().getParameterNames(), getParentScope());
	}

	@JSFunction("setParameter")
	public void setParameter(String name, Object value) {
		unwrap().setParameter(name, value);
	}

	// TODO expose parameters as camel-cased properties
}
