package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Document;

public class ScriptableDocument extends ScriptableNode {
	
	public static String JS_CLASS_NAME = "Document";

	protected Document delegateDocument;
	
	
	public ScriptableDocument(Scriptable scope, Document document) {
		super(scope, document);
		this.delegateDocument = document;
	}
	

}
