package net.lojjic.xul.impl;

import net.lojjic.xul.XULCommandDispatcher;
import net.lojjic.xul.XULConstants;
import net.lojjic.xul.XULDocument;
import net.lojjic.xul.xbl.impl.DocumentXBLImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.events.Event;
import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * {@link net.lojjic.xul.XULDocument} implementation
 */
public class XULDocumentImpl extends DocumentXBLImpl implements XULDocument, DocumentView {

	private WindowImpl window;
	private Node popupNode;
	private Node popupRangeParent;
	private long popupRangeOffset;
	private Node tooltipNode;
	private XULCommandDispatcher commandDispatcher;
	private long width;
	private long height;

	private static HashMap<String, XULElementFactory> elementFactories = new HashMap<String, XULElementFactory>();

	@Override
	public DOMImplementation getImplementation() {
		return XULDOMImplementation.getInstance();
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) {
		if(XULConstants.XUL_NAMESPACE.equals(namespaceURI)) {
			// Parse out the prefix:
			String localName = qualifiedName;
			int colon = localName.indexOf(":");
			if(colon != -1) {
				localName = localName.substring(colon + 1);
			}

			// Find the factory:
			XULElementFactory factory = elementFactories.get(localName);
			if(factory == null) {
				factory = XULElementImpl.getFactory();
			}

			// Create the instance:
			return factory.create(this, qualifiedName);
		}

		// Fall back:
		return super.createElementNS(namespaceURI, qualifiedName);
	}


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
		NodeList all = getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "*");
		final List<Node> matching = new ArrayList<Node>();
		for(int i=0; i<all.getLength(); i++) {
			Element elt = (Element)all.item(i);
			String attrVal = elt.getAttribute(name);
			if(attrVal != null && attrVal.equals(value)) {
				matching.add(elt);
			}
		}
		return new NodeList() {
			public Node item(int index) {
				return matching.get(index);
			}
			public int getLength() {
				return matching.size();
			}
		};
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

	public AbstractView getDefaultView() {
		return window;
	}



	static {
		elementFactories.put("button", XULButtonElementImpl.getFactory());
		elementFactories.put("checkbox", XULCheckboxElementImpl.getFactory());
		elementFactories.put("description", XULDescriptionElementImpl.getFactory());
		elementFactories.put("image", XULImageElementImpl.getFactory());
		elementFactories.put("label", XULLabelElementImpl.getFactory());
		elementFactories.put("listbox", XULMultiSelectControlElementImpl.getFactory());
		elementFactories.put("listitem", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menu", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menulist", XULMenuListElementImpl.getFactory());
		elementFactories.put("menuitem", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("menupopup", XULPopupElementImpl.getFactory());
		elementFactories.put("menuseparator", XULSelectControlItemElementImpl.getFactory());
		elementFactories.put("popup", XULPopupElementImpl.getFactory());
		elementFactories.put("textbox", XULTextBoxElementImpl.getFactory());
		elementFactories.put("tree", XULTreeElementImpl.getFactory());
	}

}
