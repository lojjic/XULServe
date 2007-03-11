package net.lojjic.xul.impl;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;
import org.apache.xerces.dom.events.EventImpl;
import net.lojjic.xul.XULCommandEvent;

/**
 * {@link net.lojjic.xul.XULCommandEvent} implementation
 */
public class XULCommandEventImpl extends EventImpl implements XULCommandEvent {

	protected boolean ctrlKey;
	protected boolean shiftKey;
	protected boolean altKey;
	protected boolean metaKey;
	protected Event sourceEvent;
	protected AbstractView view;
	protected int detail;

	public boolean isCtrlKey() {
		return ctrlKey;
	}

	public boolean isShiftKey() {
		return shiftKey;
	}

	public boolean isAltKey() {
		return altKey;
	}

	public boolean isMetaKey() {
		return metaKey;
	}

	public Event getSourceEvent() {
		return sourceEvent;
	}

	public AbstractView getView() {
		return view;
	}

	public int getDetail() {
		return detail;
	}

	public String getType() {
		return type;
	}

	public EventTarget getTarget() {
		return target;
	}

	public void initCommandEvent(String type, boolean canBubble, boolean cancelable,
	                             AbstractView view, int detail, boolean ctrlKey, boolean altKey,
	                             boolean shiftKey, boolean metaKey, Event sourceEvent) {
		initUIEvent(type, canBubble, cancelable, view, detail);
		this.ctrlKey = ctrlKey;
		this.altKey = altKey;
		this.shiftKey = shiftKey;
		this.metaKey = metaKey;
		this.sourceEvent = sourceEvent;
	}

	public void initUIEvent(String type, boolean canBubble, boolean cancelable,
	                        AbstractView view, int detail) {
		initEvent(type, canBubble, cancelable);
		this.view = view;
		this.detail = detail;
	}
}
