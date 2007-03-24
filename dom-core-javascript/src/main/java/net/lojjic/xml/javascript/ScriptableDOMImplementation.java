package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Document;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementation}
 */
@JSClassName("DOMImplementation")
public class ScriptableDOMImplementation extends ScriptableDOMObject {

	private DOMImplementation delegateDOMImplementation;

	public ScriptableDOMImplementation() {
		super();
	}

	public ScriptableDOMImplementation(Scriptable scope, DOMImplementation delegateDOMImplementation) {
		super(scope, delegateDOMImplementation);
		this.delegateDOMImplementation = delegateDOMImplementation;
	}

	@JSFunction("createDocument")
	public Object createDocument(String namespaceURI, String qualifiedName, Object doctype) {
		Document result = delegateDOMImplementation.createDocument(namespaceURI, qualifiedName, convertArg(doctype, DocumentType.class));
		return Context.javaToJS(result, getParentScope());
	}

	@JSFunction("createDocumentType")
	public Object createDocumentType(String qualifiedName, String publicId, String systemId) {
		return Context.javaToJS(delegateDOMImplementation.createDocumentType(qualifiedName, publicId, systemId), getParentScope());
	}

	@JSFunction("getFeature")
	public Object getFeature(String feature, String version) {
		return Context.javaToJS(delegateDOMImplementation.getFeature(feature, version), getParentScope());
	}

	@JSFunction("hasFeature")
	public boolean hasFeature(String feature, String version) {
		return delegateDOMImplementation.hasFeature(feature, version);
	}
}
