package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMConfiguration;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMConfiguration}
 */
@JSClassName("DOMConfiguration")
public class ScriptableDOMConfiguration extends ScriptableDOMObject {

	private DOMConfiguration delegateDOMConfiguration;

	public ScriptableDOMConfiguration(Scriptable scope, DOMConfiguration delegateDOMConfiguration) {
		super(scope, delegateDOMConfiguration);
		this.delegateDOMConfiguration = delegateDOMConfiguration;
	}

	@JSFunction("canSetParameter")
	public boolean canSetParameter(String name, Object value) {
		return delegateDOMConfiguration.canSetParameter(name, value);
	}

	@JSFunction("getParameter")
	public Object getParameter(String name) {
		return delegateDOMConfiguration.getParameter(name);
	}

	@JSGetter("parameterNames")
	public Object getParameterNames() {
		return delegateDOMConfiguration.getParameterNames();
	}

	@JSFunction("setParameter")
	public void setParameter(String name, Object value) {
		delegateDOMConfiguration.setParameter(name, value);
	}

	// TODO expose parameters as camel-cased properties
}
