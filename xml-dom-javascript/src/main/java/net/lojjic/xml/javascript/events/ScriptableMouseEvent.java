package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.MouseEvent}
 */
@JSClassName("MouseEvent")
public class ScriptableMouseEvent<T extends MouseEvent> extends ScriptableUIEvent<T> {

	public ScriptableMouseEvent() {
		super();
	}

	public ScriptableMouseEvent(Scriptable scope, T event) {
		super(scope, event);
	}
	

	@JSGetter("altKey")
	public boolean getAltKey() {
		return unwrap().getAltKey();
	}

	@JSGetter("button")
	public int getButton() {
		return unwrap().getButton();
	}

	@JSGetter("clientX")
	public int getClientX() {
		return unwrap().getClientX();
	}

	@JSGetter("clientY")
	public int getClientY() {
		return unwrap().getClientY();
	}

	@JSGetter("ctrlKey")
	public boolean getCtrlKey() {
		return unwrap().getCtrlKey();
	}

	@JSGetter("metaKey")
	public boolean getMetaKey() {
		return unwrap().getMetaKey();
	}

	@JSGetter("relatedTarget")
	public Object getRelatedTarget() {
		return Context.javaToJS(unwrap().getRelatedTarget(), getParentScope());
	}

	@JSGetter("screenX")
	public int getScreenX() {
		return unwrap().getScreenX();
	}

	@JSGetter("screenY")
	public int getScreenY() {
		return unwrap().getScreenY();
	}

	@JSGetter("shiftKey")
	public boolean getShiftKey() {
		return unwrap().getShiftKey();
	}
	
	@JSFunction("initMouseEvent")
	public void initMouseEvent(String type, boolean canBubble, boolean cancelable, Object view,
			int detail, int screenX, int screenY, int clientX, int clientY, boolean ctrlKey, boolean altKey, 
			boolean shiftKey, boolean metaKey, int button, Object relatedTarget) {
		unwrap().initMouseEvent(type, canBubble, cancelable, convertArg(view, AbstractView.class),
				detail, screenX, screenY, clientX, clientY, ctrlKey, altKey, shiftKey, metaKey,
				(short)button, convertArg(relatedTarget, EventTarget.class));
	}

}
