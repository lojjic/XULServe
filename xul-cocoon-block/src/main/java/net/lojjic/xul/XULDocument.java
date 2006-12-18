package net.lojjic.xul;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;

public interface XULDocument extends Document, DocumentEvent {
	
	Node getPopupNode();
	void setPopupNode(Node node);
	
	
	/*
	 * These attributes correspond to trustedGetPopupNode().rangeOffset and
	 * rangeParent. They will help you find where in the DOM the popup is
	 * happening. Can be accessed from chrome only, and only during a popup
	 * event. Accessing any other time will be an error.
	 */
	Node getPopupRangeParent(); //readonly
	long getPopupRangeOffset(); //readonly
	
	
	Node getTooltipNode();
	void setTooltipNode(Node node);
	
	
	XULCommandDispatcher getCommandDispatcher(); //readonly
	
	
	long getWidth(); //readonly
	long getHeight(); //readonly
	
	
	/**
	 * Retrieve all Elements within the document for which the named attribute 
	 * has the given value. A value of "*" is a wildcard signifying all 
	 * elements with the attribute.
	 * 
	 * @param name The name of the attribute
	 * @param value The value of the attribute. A value of "*" is a wildcard 
	 * signifying all elements with the attribute.
	 * @return A NodeList of matching Elements
	 */
	NodeList getElementsByAttribute(String name, String value);
	
	void addBroadcastListenerFor(Element broadcaster, Element observer, String attr);
	
	void removeBroadcastListenerFor(Element broadcaster, Element observer, String attr);
		
	void persist(String id, String attr);
		
	void loadOverlay(String url, Observer observer);
	
	
	
	Node trustedGetPopupNode();
	
	Node trustedGetTooltipNode();
	
	Event getTrustedPopupEvent();
	void setTrustedPopupEvent(Event event);
	
}
