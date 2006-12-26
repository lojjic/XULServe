package net.lojjic.xml.javascript.views;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.views.AbstractView;

import net.lojjic.xml.javascript.ScriptableDOMObject;

public class ScriptableAbstractView extends ScriptableDOMObject {
	
	public static String JS_CLASS_NAME = "AbstractView";
	
	protected AbstractView delegateAbstractView;

	public AbstractView getDelegateAbstractView() {
		return delegateAbstractView;
	}

	public ScriptableAbstractView(Scriptable scope, AbstractView view) {
		super(scope);
		this.delegateAbstractView = view;
	}
	
	
	public Object jsGet_document() {
		return wrap(delegateAbstractView.getDocument());
	}

}
