package net.lojjic.xul.xbl.impl;

import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.Event;

/**
 * XBL event handler
 */
public class XBLHandler implements EventListener {

	public static enum Phase { capturing, bubbling, target }
	public static enum AttachTo { element, document, window }
	public static enum Modifier { shift, alt, control, meta, accel, access }

	private String eventType;
	private Object action; //TODO make JS function object
	private Phase phase;
	private AttachTo attachTo;
	private Integer button;
	private Modifier[] modifiers;
	private String keyCode;
	private String charCode;
	private String type = "javascript";


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

	public AttachTo getAttachTo() {
		return attachTo;
	}

	public void setAttachTo(AttachTo attachTo) {
		this.attachTo = attachTo;
	}

	public void setAttachTo(String attachTo) {
		this.attachTo = AttachTo.valueOf(attachTo);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
