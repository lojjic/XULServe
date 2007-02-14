package net.lojjic.xml.javascript.views;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.views.AbstractView}
 */
@JSClassName("AbstractView")
public class ScriptableAbstractView extends ScriptableDOMObject implements AbstractView {
	
	public static String JS_CLASS_NAME = "AbstractView";
	
	protected AbstractView delegateAbstractView;

	public ScriptableAbstractView(Scriptable scope, AbstractView view) {
		super(scope, view);
		this.delegateAbstractView = view;
	}

	@JSGetter("document")
	public DocumentView getDocument() {
		return delegateAbstractView.getDocument();
	}

}
