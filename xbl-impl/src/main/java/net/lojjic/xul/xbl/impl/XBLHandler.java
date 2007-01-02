package net.lojjic.xul.xbl.impl;

import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.Event;

/**
 * XBL event handler
 */
public class XBLHandler implements EventListener {

	public static enum Phase { capturing, bubbling, target }
	public static enum Modifier { shift, alt, control, meta, accel, accesskey }

	private String eventType;
	private Object action; //TODO make JS function object
	private Phase phase;
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
