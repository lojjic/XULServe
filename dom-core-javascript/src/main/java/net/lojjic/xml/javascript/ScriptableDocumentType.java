package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DocumentType;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DocumentType}
 */
@JSClassName("DocumentType")
public class ScriptableDocumentType<T extends DocumentType> extends ScriptableNode<T> {

	public ScriptableDocumentType() {
		super();
	}

	public ScriptableDocumentType(Scriptable scope, T documentType) {
		super(scope, documentType);
	}

	@JSGetter("entities")
	public Object getEntities() {
		return Context.javaToJS(unwrap().getEntities(), getParentScope());
	}

	@JSGetter("internalSubset")
	public String getInternalSubset() {
		return unwrap().getInternalSubset();
	}

	@JSGetter("name")
	public String getName() {
		return unwrap().getName();
	}

	@JSGetter("notations")
	public Object getNotations() {
		return Context.javaToJS(unwrap().getNotations(), getParentScope());
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return unwrap().getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return unwrap().getSystemId();
	}
}
