package net.lojjic.xul;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

/**
 * This interface is supported by command events, which are dispatched to
 * XUL elements as a result of mouse or keyboard activation.
 */
public interface XULCommandEvent extends UIEvent {

	boolean isCtrlKey();
	boolean isShiftKey();
	boolean isAltKey();
	boolean isMetaKey();

	/**
	 * If the command event was redispatched because of a command= attribute
	 * on the original target, sourceEvent will be set to the original DOM Event.
	 * Otherwise, sourceEvent is null.
	 */
	Event getSourceEvent();

	/**
	 * Creates a new command event with the given attributes.
	 */
	void initCommandEvent(
			String typeArg,
			boolean canBubbleArg,
			boolean cancelableArg,
			AbstractView viewArg,
			int detailArg,
			boolean ctrlKeyArg,
			boolean altKeyArg,
			boolean shiftKeyArg,
			boolean metaKeyArg,
			Event sourceEvent
	);
}
