package net.lojjic.xul;

import org.w3c.dom.Element;

public interface XULPopupElement extends XULElement {

	final static short BEFORE_START = 1;
	final static short BEFORE_END = 2;
	final static short AFTER_START = 3;
	final static short AFTER_END = 4;
	final static short START_BEFORE = 5;
	final static short START_AFTER = 6;
	final static short END_BEFORE = 7;
	final static short END_AFTER = 8;
	final static short OVERLAP = 9;
	final static short AT_POINTER = 10;
	final static short AFTER_POINTER = 11;
	
	String getPosition();
	void setPosition(String position);
	
	void showPopup(short alignment, Element target, Element anchor);
	
	void hidePopup();
	
}
