package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Context;
import org.w3c.dom.events.Event;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.Event}
 */
@JSClassName("Event")
public class ScriptableEvent<T extends Event> extends ScriptableDOMObject<T> {

	public ScriptableEvent() {
		super();
	}

	public ScriptableEvent(Scriptable scope, T event) {
		super(scope, event);
	}

	@JSStatic @JSGetter("AT_TARGET")
	public static int get_AT_TARGET(ScriptableObject obj) {
		return Event.AT_TARGET;
	}

	@JSStatic @JSGetter("BUBBLING_PHASE")
	public static int get_BUBBLING_PHASE(ScriptableObject obj) {
		return Event.BUBBLING_PHASE;
	}

	@JSStatic @JSGetter("CAPTURING_PHASE")
	public static int get_CAPTURING_PHASE(ScriptableObject obj) {
		return Event.CAPTURING_PHASE;
	}

	@JSGetter("bubbles")
	public boolean getBubbles() {
		return unwrap().getBubbles();
	}
	
	@JSGetter("cancelable")
	public boolean getCancelable() {
		return unwrap().getCancelable();
	}
	
	@JSGetter("currentTarget")
	public Object getCurrentTarget() {
		return Context.javaToJS(unwrap().getCurrentTarget(), getParentScope());
	}
	
	@JSGetter("eventPhase")
	public int getEventPhase() {
		return unwrap().getEventPhase();
	}
	
	@JSGetter("target")
	public Object getTarget() {
		return Context.javaToJS(unwrap().getTarget(), getParentScope());
	}
	
	@JSGetter("timestamp")
	public long getTimeStamp() {
		return unwrap().getTimeStamp();
	}
	
	@JSGetter("type")
	public String getType() {
		return unwrap().getType();
	}

	@JSFunction("initEvent")
	public void initEvent(String eventType, boolean canBubble, boolean cancelable) {
		unwrap().initEvent(eventType, canBubble, cancelable);
	}
	
	@JSFunction("preventDefault")
	public void preventDefault() {
		unwrap().preventDefault();
	}

	@JSFunction("stopPropagation")
	public void stopPropagation() {
		unwrap().stopPropagation();
	}

}
