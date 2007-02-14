package net.lojjic.xml.javascript;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMConfiguration}
 */
@JSClassName("DOMConfiguration")
public class ScriptableDOMConfiguration extends ScriptableDOMObject implements DOMConfiguration {

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
	public Object getParameter(String name) throws DOMException {
		return delegateDOMConfiguration.getParameter(name);
	}

	@JSGetter("parameterNames")
	public DOMStringList getParameterNames() {
		return delegateDOMConfiguration.getParameterNames();
	}

	@JSFunction("setParameter")
	public void setParameter(String name, Object value) throws DOMException {
		delegateDOMConfiguration.setParameter(name, value);
	}
}
