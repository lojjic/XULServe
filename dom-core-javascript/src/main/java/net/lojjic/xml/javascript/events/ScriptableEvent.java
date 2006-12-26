package net.lojjic.xml.javascript.events;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.Event;

import net.lojjic.xml.javascript.ScriptableDOMObject;

public class ScriptableEvent extends ScriptableDOMObject {
	
	public static String JS_CLASS_NAME = "Event";
	
	protected Event delegateEvent;

	public ScriptableEvent(Scriptable scope, Event event) {
		super(scope);
		this.delegateEvent = event;
	}
	

	
	/* TODO static properties:
	static short 	AT_TARGET
	The event is currently being evaluated at the target EventTarget.
	static short 	BUBBLING_PHASE
	The current event phase is the bubbling phase.
	static short 	CAPTURING_PHASE
	The current event phase is the capturing phase.
	 */
	
	
	public boolean jsGet_bubbles() {
		return delegateEvent.getBubbles();
	}
	
	public boolean jsGet_cancelable() {
		return delegateEvent.getCancelable();
	}
	
	public Object jsGet_currentTarget() {
		return delegateEvent.getCurrentTarget();
	}
	
	public short jsGet_eventPhase() {
		return delegateEvent.getEventPhase();
	}
	
	public Object jsGet_target() {
		return delegateEvent.getTarget();
	}
	
	public long jsGet_timestamp() {
		return delegateEvent.getTimeStamp();
	}
	
	public String jsGet_type() {
		return delegateEvent.getType();
	}
	
	public void jsFunction_initEvent(String eventType, boolean canBubble, boolean cancelable) {
		delegateEvent.initEvent(eventType, canBubble, cancelable);
	}
	
	public void jsFunction_preventDefault() {
		delegateEvent.preventDefault();
	}

	public void jsFunction_stopPropagation() {
		delegateEvent.stopPropagation();
	}

}
