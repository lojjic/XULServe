package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Attr;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Attr}
 */
@JSClassName("Attr")
public class ScriptableAttr extends ScriptableNode {

	protected Attr delegateAttr;

	public ScriptableAttr() {
		super();
	}

	public ScriptableAttr(Scriptable scope, Attr attr) {
		super(scope, attr);
		this.delegateAttr = attr;
	}

	@JSGetter("name")
	public String getName() {
		return delegateAttr.getName();
	}

	@JSGetter("ownerElement")
	public Object getOwnerElement() {
		return Context.javaToJS(delegateAttr.getOwnerElement(), getParentScope());
	}

	@JSGetter("schemaTypeInfo")
	public Object getSchemaTypeInfo() {
		return Context.javaToJS(delegateAttr.getSchemaTypeInfo(), getParentScope());
	}

	@JSGetter("specified")
	public boolean getSpecified() {
		return delegateAttr.getSpecified();
	}
	@JSGetter("isId")
	public boolean isId() {
		return delegateAttr.isId();
	}

	@JSGetter("value")
	public String getValue() {
		return delegateAttr.getValue();
	}

	@JSSetter("value")
	public void setValue(String value) {
		delegateAttr.setValue(value);
	}
}
