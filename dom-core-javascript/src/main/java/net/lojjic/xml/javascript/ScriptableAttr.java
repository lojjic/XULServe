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
public class ScriptableAttr<T extends Attr> extends ScriptableNode<T> {

	public ScriptableAttr() {
		super();
	}

	public ScriptableAttr(Scriptable scope, T attr) {
		super(scope, attr);
	}

	@JSGetter("name")
	public String getName() {
		return unwrap().getName();
	}

	@JSGetter("ownerElement")
	public Object getOwnerElement() {
		return Context.javaToJS(unwrap().getOwnerElement(), getParentScope());
	}

	@JSGetter("schemaTypeInfo")
	public Object getSchemaTypeInfo() {
		return Context.javaToJS(unwrap().getSchemaTypeInfo(), getParentScope());
	}

	@JSGetter("specified")
	public boolean getSpecified() {
		return unwrap().getSpecified();
	}
	@JSGetter("isId")
	public boolean isId() {
		return unwrap().isId();
	}

	@JSGetter("value")
	public String getValue() {
		return unwrap().getValue();
	}

	@JSSetter("value")
	public void setValue(String value) {
		unwrap().setValue(value);
	}
}
