package net.lojjic.xml.javascript.events;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.Event}
 */
@JSClassName("Event")
public class ScriptableEvent extends ScriptableDOMObject implements Event {
	
	protected Event delegateEvent;

	public ScriptableEvent(Scriptable scope, Event event) {
		super(scope, event);
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
	
	@JSGetter("bubbles")
	public boolean getBubbles() {
		return delegateEvent.getBubbles();
	}
	
	@JSGetter("cancelable")
	public boolean getCancelable() {
		return delegateEvent.getCancelable();
	}
	
	@JSGetter("currentTarget")
	public EventTarget getCurrentTarget() {
		return delegateEvent.getCurrentTarget();
	}
	
	@JSGetter("eventPhase")
	public short getEventPhase() {
		return delegateEvent.getEventPhase();
	}
	
	@JSGetter("target")
	public EventTarget getTarget() {
		return delegateEvent.getTarget();
	}
	
	@JSGetter("timestamp")
	public long getTimeStamp() {
		return delegateEvent.getTimeStamp();
	}
	
	@JSGetter("type")
	public String getType() {
		return delegateEvent.getType();
	}

	@JSFunction("initEvent")
	public void initEvent(String eventType, boolean canBubble, boolean cancelable) {
		delegateEvent.initEvent(eventType, canBubble, cancelable);
	}
	
	@JSFunction("preventDefault")
	public void preventDefault() {
		delegateEvent.preventDefault();
	}

	@JSFunction("stopPropagation")
	public void stopPropagation() {
		delegateEvent.stopPropagation();
	}

}
