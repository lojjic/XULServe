package net.lojjic.xul;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public interface XULCommandEvent extends UIEvent {

	boolean isCtrlKey();
	boolean isShiftKey();
	boolean isAltKey();
	boolean isMetaKey();
	
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
