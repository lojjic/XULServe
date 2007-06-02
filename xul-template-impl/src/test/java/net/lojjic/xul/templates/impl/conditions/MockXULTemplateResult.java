package net.lojjic.xul.templates.impl.conditions;

import net.lojjic.xul.templates.XULTemplateResult;
import net.lojjic.xul.rdf.RDFResource;
import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * Simple {@link net.lojjic.xul.templates.XULTemplateResult} implementation that
 * only allows retrieving variable bindings.
 */
public class MockXULTemplateResult implements XULTemplateResult {

	private HashMap<String, Object> bindings = new HashMap<String, Object>();

	/**
	 * Add a new variable binding.
	 */
	public void setBinding(String var, Object value) {
		bindings.put(var, value);
	}

	public String getBindingFor(String var) {
		Object obj = getBindingObjectFor(var);
		return obj == null ? null : obj.toString();
	}

	public Object getBindingObjectFor(String var) {
		return bindings.get(var);
	}



	//===== The rest are unimplemented as they are not needed... =====//

	public boolean isContainer() {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean mayProcessChildren() {
		return false;
	}

	public String getId() {
		return null;
	}

	public RDFResource getResource() {
		return null;
	}

	public String getType() {
		return null;
	}

	public void ruleMatched(Object query, Node ruleNode) {
	}

	public void hasBeenRemoved() {
	}
}
