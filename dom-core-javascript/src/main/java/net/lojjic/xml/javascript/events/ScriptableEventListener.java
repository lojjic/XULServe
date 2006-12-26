package net.lojjic.xml.javascript.events;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import net.lojjic.xml.javascript.ScriptableDOMObject;

public class ScriptableEventListener extends ScriptableDOMObject {
	
	public static String JS_CLASS_NAME = "EventListener";
	
	protected EventListener delegateEventListener;

	public EventListener getDelegateEventListener() {
		return delegateEventListener;
	}

	public ScriptableEventListener(Scriptable scope, EventListener eventListener) {
		super(scope);
		this.delegateEventListener = eventListener;
	}
	
	
	public void jsFunction_handleEvent(Object event) {
		if(event instanceof ScriptableEvent) {
			event = ((ScriptableEvent)event).delegateEvent;
		}
		if(!(event instanceof Event)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, "handleEvent() expects an Event object");
		}
		delegateEventListener.handleEvent((Event)event);
	}

}
