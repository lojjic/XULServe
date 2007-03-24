package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.UIEvent}
 */
@JSClassName("UIEvent")
public class ScriptableUIEvent extends ScriptableEvent {
	
	protected UIEvent delegateUIEvent;

	public ScriptableUIEvent(Scriptable scope, UIEvent event) {
		super(scope, event);
		this.delegateUIEvent = event;
	}
	
	@JSGetter("detail")
	public int getDetail() {
		return delegateUIEvent.getDetail();
	}

	@JSGetter("view")
	public Object getView() {
		return delegateUIEvent.getView();
	}

	@JSFunction("initUIEvent")
	public void initUIEvent(String type, boolean canBubble, boolean cancelable, Object view, int detail) {
		delegateUIEvent.initUIEvent(type, canBubble, cancelable, convertArg(view, AbstractView.class), detail);
	}

}
