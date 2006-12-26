package net.lojjic.xml.javascript.events;

import net.lojjic.xml.javascript.ScriptableNode;
import net.lojjic.xml.javascript.views.ScriptableAbstractView;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

public class ScriptableMouseEvent extends ScriptableUIEvent {
	
	public static String JS_CLASS_NAME = "MouseEvent";
	
	protected MouseEvent delegateMouseEvent;

	public ScriptableMouseEvent(Scriptable scope, MouseEvent event) {
		super(scope, event);
		this.delegateMouseEvent = event;
	}
	
	
	public boolean jsGet_altKey() {
		return delegateMouseEvent.getAltKey();
	}

	public short jsGet_button() {
		return delegateMouseEvent.getButton();
	}

	public int jsGet_clientX() {
		return delegateMouseEvent.getClientX();
	}

	public int jsGet_clientY() {
		return delegateMouseEvent.getClientY();
	}

	public boolean jsGet_ctrlKey() {
		return delegateMouseEvent.getCtrlKey();
	}

	public boolean jsGet_metaKey() {
		return delegateMouseEvent.getMetaKey();
	}

	public Object jsGet_relatedTarget() {
		return delegateMouseEvent.getRelatedTarget();
	}

	public int jsGet_screenX() {
		return delegateMouseEvent.getScreenX();
	}

	public int jsGet_screenY() {
		return delegateMouseEvent.getScreenY();
	}

	public boolean jsGet_shiftKey() {
		return delegateMouseEvent.getShiftKey();
	}
	
	public void jsFunction_initMouseEvent(String type, boolean canBubble, boolean cancelable, Object view, 
			int detail, int screenX, int screenY, int clientX, int clientY, boolean ctrlKey, boolean altKey, 
			boolean shiftKey, boolean metaKey, short button, Object relatedTarget) {
		
		if(view instanceof ScriptableAbstractView) {
			view = ((ScriptableAbstractView)view).getDelegateAbstractView();
		}
		if(!(view instanceof AbstractView)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR,
					"Unrecognized value for 'viewArg'; must be of type AbstractView.");
		}
		
		if(relatedTarget instanceof ScriptableNode) {
			relatedTarget = ((ScriptableNode)relatedTarget).getDelegateNode();
		}
		if(!(relatedTarget instanceof EventTarget)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR,
					"Unrecognized value for 'relatedTarget' argument; must be of type EventTarget.");
		}
		
		delegateMouseEvent.initMouseEvent(type, canBubble, cancelable, (AbstractView)view, detail, screenX, screenY, 
				clientX, clientY, ctrlKey, altKey, shiftKey, metaKey, button, (EventTarget)relatedTarget);
	}

}
