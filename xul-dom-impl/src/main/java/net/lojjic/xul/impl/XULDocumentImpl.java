package net.lojjic.xul.impl;

import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

/**
 * {@link net.lojjic.xul.XULDocument} implementation
 */
public class XULDocumentImpl extends DocumentImpl implements XULDocument {

	private Node popupNode;
	private Node popupRangeParent;
	private long popupRangeOffset;
	private Node tooltipNode;
	private XULCommandDispatcher commandDispatcher;
	private long width;
	private long height;


	private static HashMap<String, Class> elementImplMap = new HashMap<String, Class>();
	static {
		elementImplMap.put("button", XULButtonElementImpl.class);
		elementImplMap.put("checkbox", XULCheckboxElementImpl.class);
		elementImplMap.put("description", XULDescriptionElementImpl.class);
		elementImplMap.put("image", XULImageElementImpl.class);
		elementImplMap.put("label", XULLabelElementImpl.class);
		elementImplMap.put("listbox", XULMultiSelectControlElementImpl.class);
		elementImplMap.put("listitem", XULSelectControlItemElementImpl.class);
		elementImplMap.put("menu", XULSelectControlItemElementImpl.class);
		elementImplMap.put("menulist", XULMenuListElementImpl.class);
		elementImplMap.put("menuitem", XULSelectControlItemElementImpl.class);
		elementImplMap.put("menupopup", XULPopupElementImpl.class);
		elementImplMap.put("menuseparator", XULSelectControlItemElementImpl.class);
		elementImplMap.put("popup", XULPopupElementImpl.class);
		elementImplMap.put("textbox", XULTextboxElementImpl.class);
		elementImplMap.put("tree", XULTreeElementImpl.class);
	}


	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) {
		if(Constants.XUL_NAMESPACE.equals(namespaceURI)) {
			// Parse out the prefix:
			String localName = qualifiedName;
			int colon = localName.indexOf(":");
			if(colon != -1) {
				localName = localName.substring(colon + 1);
			}
			Class implCls = elementImplMap.get(localName);
			if(implCls != null) {
				try {
					Constructor cons = implCls.getConstructor(XULDocumentImpl.class, String.class);
					return (Element)cons.newInstance(this, qualifiedName);
				}
				catch (Exception e) {
					// TODO log a warning
				}
			}
			return new XULElementImpl(this, qualifiedName);
		}

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
		NodeList all = getElementsByTagNameNS(Constants.XUL_NAMESPACE, "*");
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
}
