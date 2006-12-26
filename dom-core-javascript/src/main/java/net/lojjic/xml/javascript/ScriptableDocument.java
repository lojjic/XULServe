package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

public class ScriptableDocument extends ScriptableNode {
	
	public static String JS_CLASS_NAME = "Document";

	protected Document delegateDocument;
	
	
	public ScriptableDocument(Scriptable scope, Document document) {
		super(scope, document);
		this.delegateDocument = document;
	}
	
	
	///// Document core interface: /////
	
	
	
	
	
	///// DocumentEvent interface: /////
	
	public Object jsFunction_createEvent(String type) {
		if(!(delegateDocument instanceof DocumentEvent)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentEvent interface methods.");
		}
		Event event = ((DocumentEvent)delegateDocument).createEvent(type);
		return wrap(event);
	}
	
	
	///// DocumentView interface: /////
	
	public Object jsGet_defaultView() {
		if(!(delegateDocument instanceof DocumentView)) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
					"This Document object does not support DocumentView interface methods.");
		}
		AbstractView view = ((DocumentView)delegateDocument).getDefaultView();
		return wrap(view);
	}

}
