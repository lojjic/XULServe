package net.lojjic.xul.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.excalibur.xml.xpath.NodeListImpl;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;

import net.lojjic.xul.Constants;
import net.lojjic.xul.XULCommandDispatcher;
import net.lojjic.xul.XULDocument;

public class XULDocumentImpl extends DocumentImpl implements XULDocument {
	
	
	
	private Set<XULElementImpl> modifiedElements = new HashSet<XULElementImpl>();
	
	// TODO maybe make this an event listener instead of calling it manually?
	public void elementModified(XULElementImpl element) {
		modifiedElements.add(element);
	}
	
	public void clearUpdatedElements() {
		modifiedElements.clear();
	}
	
	
	
	private Node popupNode;
	private Node popupRangeParent;
	private long popupRangeOffset;
	private Node tooltipNode;
	private XULCommandDispatcher commandDispatcher;
	private long width;
	private long height;
	
	

	public Node getPopupNode() {
		return popupNode;
	}

	public void setPopupNode(Node popupNode) {
		this.popupNode = popupNode;
	}

	public Node getPopupRangeParent() {
		return popupRangeParent;
	}

	public long getPopupRangeOffset() {
		return popupRangeOffset;
	}

	public Node getTooltipNode() {
		return tooltipNode;
	}

	public void setTooltipNode(Node tooltipNode) {
		this.tooltipNode = tooltipNode;
	}

	public XULCommandDispatcher getCommandDispatcher() {
		return commandDispatcher;
	}

	public long getWidth() {
		return width;
	}

	public long getHeight() {
		return height;
	}

	public NodeList getElementsByAttribute(String name, String value) {
		// TODO find a more efficient way to perform this search
		NodeList all = getElementsByTagNameNS(Constants.XUL_NAMESPACE, "*");
		List<Node> matching = new ArrayList<Node>();
		for(int i=0; i<all.getLength(); i++) {
			Element elt = (Element)all.item(i);
			String attrVal = elt.getAttribute(name);
			if(attrVal != null && attrVal.equals(value)) {
				matching.add(elt);
			}
		}
		return new NodeListImpl(matching.toArray(new Node[0]));
	}

	public void addBroadcastListenerFor(Element broadcaster, Element observer, String attr) {
		// TODO Auto-generated method stub
		
	}

	public void removeBroadcastListenerFor(Element broadcaster, Element observer, String attr) {
		// TODO Auto-generated method stub
		
	}

	public void persist(String id, String attr) {
		// TODO Auto-generated method stub
		
	}

	public void loadOverlay(String url) {
		// TODO Auto-generated method stub
		
	}

	public Node trustedGetPopupNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node trustedGetTooltipNode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Event getTrustedPopupEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTrustedPopupEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
