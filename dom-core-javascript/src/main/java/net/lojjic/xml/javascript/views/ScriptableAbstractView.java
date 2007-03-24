package net.lojjic.xml.javascript.views;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.views.AbstractView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.views.AbstractView}
 */
@JSClassName("AbstractView")
public class ScriptableAbstractView extends ScriptableDOMObject {
	
	public static String JS_CLASS_NAME = "AbstractView";
	
	protected AbstractView delegateAbstractView;

	public ScriptableAbstractView(Scriptable scope, AbstractView view) {
		super(scope, view);
		this.delegateAbstractView = view;
	}

	@JSGetter("document")
	public Object getDocument() {
		return Context.javaToJS(delegateAbstractView.getDocument(), getParentScope());
	}

}
