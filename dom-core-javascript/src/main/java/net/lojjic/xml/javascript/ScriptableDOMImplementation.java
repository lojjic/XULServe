package net.lojjic.xml.javascript;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMException;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementation}
 */
@JSClassName("DOMImplementation")
public class ScriptableDOMImplementation extends ScriptableDOMObject implements DOMImplementation {

	private DOMImplementation delegateDOMImplementation;

	public ScriptableDOMImplementation(Scriptable scope, DOMImplementation delegateDOMImplementation) {
		super(scope, delegateDOMImplementation);
		this.delegateDOMImplementation = delegateDOMImplementation;
	}

	@JSFunction("createDocument")
	public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
		return delegateDOMImplementation.createDocument(namespaceURI, qualifiedName, doctype);
	}

	@JSFunction("createDocumentType")
	public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
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
