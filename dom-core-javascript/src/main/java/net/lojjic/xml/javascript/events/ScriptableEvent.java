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
	
	// TODO implement properties/methods

}
