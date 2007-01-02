package net.lojjic.xul.xbl.impl;

import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.MouseEvent;

/**
 * XBL event handler
 */
public class XBLHandler implements EventListener {

	public static enum Phase { capturing, bubbling, target }
	public static enum Modifier { shift, alt, control, meta, accel, accesskey }

	private String eventType;
	private Object action; //TODO make JS function object
	private Phase phase = Phase.bubbling;
	private Integer button;
	private Modifier[] modifiers;
	private String keyCode;
	private String charCode;
	private Integer clickCount;
	private boolean preventDefault;
	private String command;


	/**
	 * Implement {@link EventListener#handleEvent(org.w3c.dom.events.Event)}
	 * Handle the given event by executing the handler action.
	 *
	 * @param evt The {@link Event} object
	 */
	public void handleEvent(Event evt) {
		if(applyFilters(evt)) {
			// TODO execute action

			if(preventDefault) {
				evt.preventDefault();
			}
		}
	}

	/**
	 * Apply this handler's filters to the given event.
	 *
	 * @param evt The {@link Event} to test
	 * @return true if all the filters passed, false if one failed.
	 */
	private boolean applyFilters(Event evt) {
		// Event phase filter:
		if((phase == Phase.capturing && evt.getEventPhase() == Event.CAPTURING_PHASE) ||
		   (phase == Phase.target && evt.getEventPhase() == Event.AT_TARGET) ||
		   (phase == Phase.bubbling && evt.getEventPhase() == Event.BUBBLING_PHASE)) {
			return false;
		}

		if(evt instanceof MouseEvent) {
			// Mouse button filter:
			if(button != null && button.equals(((MouseEvent)evt).getButton())) {
				return false;
			}

			// Mouse click count filter:
			if(clickCount != null && clickCount.equals(((MouseEvent)evt).getDetail())) {
				return false;
			}
		}

		// Key code filter:
		// TODO need KeyEvent interface to get key detail

		// Character code filter:
		// TODO need KeyEvent interface to get key detail

		// Modifiers:
		for(Modifier mod : modifiers) {
			// TODO need KeyEvent interface to get key detail
		}

		return true;
	}



	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public void setPhase(String phase) {
		this.phase = Phase.valueOf(phase);
	}

	public Integer getButton() {
		return button;
	}

	public void setButton(Integer button) {
		this.button = button;
	}

	public Modifier[] getModifiers() {
		return modifiers;
	}

	public void setModifiers(Modifier[] modifiers) {
		this.modifiers = modifiers;
	}

	public void setModifiers(String modifiersString) {
		String[] parts = modifiersString.split(",");
		Modifier[] modifiers = new Modifier[parts.length];
		for(int i = 0; i < parts.length; i++) {
			modifiers[i] = Modifier.valueOf(parts[i]);
		}
		this.modifiers = modifiers;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public boolean isPreventDefault() {
		return preventDefault;
	}

	public void setPreventDefault(boolean preventDefault) {
		this.preventDefault = preventDefault;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
