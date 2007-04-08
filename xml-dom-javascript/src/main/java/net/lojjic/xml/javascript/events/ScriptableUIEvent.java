package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.UIEvent}
 */
@JSClassName("UIEvent")
public class ScriptableUIEvent<T extends UIEvent> extends ScriptableEvent<T> {

	public ScriptableUIEvent() {
		super();
	}

	public ScriptableUIEvent(Scriptable scope, T event) {
		super(scope, event);
	}
	
	@JSGetter("detail")
	public int getDetail() {
		return unwrap().getDetail();
	}

	@JSGetter("view")
	public Object getView() {
		return Context.javaToJS(unwrap().getView(), getParentScope());
	}

	@JSFunction("initUIEvent")
	public void initUIEvent(String type, boolean canBubble, boolean cancelable, Object view, int detail) {
		unwrap().initUIEvent(type, canBubble, cancelable, convertArg(view, AbstractView.class), detail);
	}

}
