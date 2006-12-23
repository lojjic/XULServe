package net.lojjic.xul.javascript;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Document;

public class ScriptableDocument extends ScriptableObject {

	protected Document delegateDocument;
	
	
	public ScriptableDocument(Document document) {
		this.delegateDocument = document;
	}
	
	
	@Override
	public String getClassName() {
		return "Document";
	}

}
