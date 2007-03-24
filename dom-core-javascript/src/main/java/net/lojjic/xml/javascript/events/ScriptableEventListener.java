package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.EventListener}
 */
@JSClassName("EventListener")
public class ScriptableEventListener extends ScriptableDOMObject {
	
	protected EventListener delegateEventListener;

	public ScriptableEventListener() {
		super();
	}

	public ScriptableEventListener(Scriptable scope, EventListener eventListener) {
		super(scope, eventListener);
		this.delegateEventListener = eventListener;
	}
	
	@JSFunction("handleEvent")
	public void handleEvent(Object event) {
		delegateEventListener.handleEvent(convertArg(event, Event.class));
	}

	public EventListener getDelegateEventListener() {
		return delegateEventListener;
	}
}
