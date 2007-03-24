package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.events.Event;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.Event}
 */
@JSClassName("Event")
public class ScriptableEvent extends ScriptableDOMObject {
	
	protected Event delegateEvent;

	public ScriptableEvent(Scriptable scope, Event event) {
		super(scope, event);
		this.delegateEvent = event;
	}

	@JSStatic @JSGetter("AT_TARGET")
	public static short get_AT_TARGET(ScriptableObject obj) {
		return Event.AT_TARGET;
	}

	@JSStatic @JSGetter("BUBBLING_PHASE")
	public static short get_BUBBLING_PHASE(ScriptableObject obj) {
		return Event.BUBBLING_PHASE;
	}

	@JSStatic @JSGetter("CAPTURING_PHASE")
	public static short get_CAPTURING_PHASE(ScriptableObject obj) {
		return Event.CAPTURING_PHASE;
	}

	@JSGetter("bubbles")
	public boolean getBubbles() {
		return delegateEvent.getBubbles();
	}
	
	@JSGetter("cancelable")
	public boolean getCancelable() {
		return delegateEvent.getCancelable();
	}
	
	@JSGetter("currentTarget")
	public Object getCurrentTarget() {
		return delegateEvent.getCurrentTarget();
	}
	
	@JSGetter("eventPhase")
	public short getEventPhase() {
		return delegateEvent.getEventPhase();
	}
	
	@JSGetter("target")
	public Object getTarget() {
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
