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
public class ScriptableDocumentType extends ScriptableNode {

	private DocumentType delegateDocumentType;

	public ScriptableDocumentType(Scriptable scope, DocumentType delegateDocumentType) {
		super(scope, delegateDocumentType);
		this.delegateDocumentType = delegateDocumentType;
	}

	@JSGetter("entities")
	public Object getEntities() {
		return Context.javaToJS(delegateDocumentType.getEntities(), getParentScope());
	}

	@JSGetter("internalSubset")
	public String getInternalSubset() {
		return delegateDocumentType.getInternalSubset();
	}

	@JSGetter("name")
	public String getName() {
		return delegateDocumentType.getName();
	}

	@JSGetter("notations")
	public Object getNotations() {
		return Context.javaToJS(delegateDocumentType.getNotations(), getParentScope());
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return delegateDocumentType.getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return delegateDocumentType.getSystemId();
	}
}
