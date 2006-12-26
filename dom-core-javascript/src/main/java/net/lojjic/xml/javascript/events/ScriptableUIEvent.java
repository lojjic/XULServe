package net.lojjic.xml.javascript.events;

import net.lojjic.xml.javascript.views.ScriptableAbstractView;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public class ScriptableUIEvent extends ScriptableEvent {
	
	public static String JS_CLASS_NAME = "UIEvent";
	
	protected UIEvent delegateUIEvent;

	public ScriptableUIEvent(Scriptable scope, UIEvent event) {
		super(scope, event);
		this.delegateUIEvent = event;
	}
	
	
	public int jsGet_detail() {
		return delegateUIEvent.getDetail();
	}
	
	public Object jsGet_view() {
		return wrap(delegateUIEvent.getView());
	}
	
	public void jsFunction_initUIEvent(String type, boolean canBubble, boolean cancelable, Object view, int detail) {
		if(view instanceof ScriptableAbstractView) {
			view = ((ScriptableAbstractView)view).getDelegateAbstractView();
		}
		if(!(view instanceof AbstractView)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR,
					"Unrecognized value for the 'view' argument; must be an AbstractView.");
		}
		delegateUIEvent.initUIEvent(type, canBubble, cancelable, (AbstractView)view, detail);
	}

}
