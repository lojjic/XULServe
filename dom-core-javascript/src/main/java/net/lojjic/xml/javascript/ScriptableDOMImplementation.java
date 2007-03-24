package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementation}
 */
@JSClassName("DOMImplementation")
public class ScriptableDOMImplementation extends ScriptableDOMObject {

	private DOMImplementation delegateDOMImplementation;

	public ScriptableDOMImplementation(Scriptable scope, DOMImplementation delegateDOMImplementation) {
		super(scope, delegateDOMImplementation);
		this.delegateDOMImplementation = delegateDOMImplementation;
	}

	@JSFunction("createDocument")
	public Object createDocument(String namespaceURI, String qualifiedName, Object doctype) {
		return delegateDOMImplementation.createDocument(namespaceURI, qualifiedName, convertArg(doctype, DocumentType.class));
	}

	@JSFunction("createDocumentType")
	public Object createDocumentType(String qualifiedName, String publicId, String systemId) {
		return delegateDOMImplementation.createDocumentType(qualifiedName, publicId, systemId);
	}

	@JSFunction("getFeature")
	public Object getFeature(String feature, String version) {
		return delegateDOMImplementation.getFeature(feature, version);
	}

	@JSFunction("hasFeature")
	public boolean hasFeature(String feature, String version) {
		return delegateDOMImplementation.hasFeature(feature, version);
	}
}
