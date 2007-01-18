package net.lojjic.xul.xbl.impl;

import net.lojjic.xul.xbl.ElementXBL;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.apache.xerces.dom.ElementNSImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link net.lojjic.xul.xbl.ElementXBL} implementation
 */
public class ElementXBLImpl extends ElementNSImpl implements ElementXBL {

	protected DocumentXBLImpl ownerDocumentXBL;
	protected ElementXBLImpl bindingOwner;
	protected ElementXBLImpl anonymousParent;
	protected List<Node> xblChildNodes;
	protected boolean isTopLevelAnonymousNode = false;
	protected List<XBLBinding> appliedBindings;

	/**
	 * Constructor.
	 */
	public ElementXBLImpl(DocumentXBLImpl documentXBL, String namespaceURI, String qualifiedName) throws DOMException {
		super(documentXBL, namespaceURI, qualifiedName);
		this.ownerDocumentXBL = documentXBL;
	}

	/**
	 * Override getParentNode() so that if this is a top-level node
	 * of an anonymous content template, then it returns the bindingOwner.
	 */
	@Override
	public Node getParentNode() {
		if(isTopLevelAnonymousNode) {
			return getBindingOwner();
		}
		return super.getParentNode();
	}

	/**
	 * The xblChildNodes property is a NodeList that represents the true children of the
	 * element after insertion points and element tags have been applied. This property
	 * can be used to navigate the content model according to XBL after bindings have
	 * moved explicit and anonymous children using element and children tags.
	 */
	public NodeList getXblChildNodes() {
		if(xblChildNodes != null && xblChildNodes.size() > 0) {
			return new NodeList() {
				public int getLength() {
					return xblChildNodes.size();
				}
				public Node item(int index) {
					return xblChildNodes.get(index);
				}
			};
		}
		return getChildNodes();
	}

	/**
	 * The bindingOwner property is used to obtain the bound element with the binding
	 * attached that is responsible for the generation of the specified anonymous node.
	 * This property enables an author to determine the scope of any content node. When
	 * content at the document-level scope is passed in as an argument, the property's
	 * value is null. See http://www.mozilla.org/projects/xbl/xbl.html#scoping for
	 * more information.
	 */
	public Element getBindingOwner() {
		return bindingOwner;
	}

	/**
	 * The anonymousParent property's value is the anonymous parent for an element
	 * that was placed underneath an insertion point using the children element in
	 * XBL or for a bound element that was repositioned using the element tag. When
	 * an element that was not repositioned is passed in as an argument, the
	 * property's value is null.
	 */
	public Element getAnonymousParent() {
		return anonymousParent;
	}

	/**
	 * The addBinding method attaches the specified binding (and any bindings that
	 * the binding inherits from) to an element. This call is not necessarily synchronous.
	 * The binding may not be attached yet when the call completes. See
	 * http://www.mozilla.org/projects/xbl/xbl.html#attach-dom for more information.
	 * <p>
	 * Implementation note: currently if this is called for a binding URL that is already
	 * attached, nothing happens. The correct behavior is undefined in the spec.
	 *
	 * @param bindingURL A URI that specifies the location of a specific binding to attach.
	 */
	public void addBinding(String bindingURL) {
		XBLBinding binding = ownerDocumentXBL.xblBindingManager.getBindingForURL(bindingURL);
		if(appliedBindings == null) {
			 appliedBindings = new ArrayList<XBLBinding>();
		}
		if(!appliedBindings.contains(binding)) {
			appliedBindings.add(binding);
			applyBindings();
		}
	}

	/**
	 * The removeBinding method detaches the specified binding (and any bindings that the
	 * binding inherits from explicitly using the extends attribute) from the element. See
	 * http://www.mozilla.org/projects/xbl/xbl.html#binding-detached for more information.
	 *
	 * @param bindingURL A URL that specifies the location of a specific binding to detach.
	 */
	public void removeBinding(String bindingURL) {
		XBLBinding binding = ownerDocumentXBL.xblBindingManager.getBindingForURL(bindingURL);
		appliedBindings.remove(binding);
		applyBindings();
	}


	protected void applyBindings() {
		applyImplementation();
		applyHandlers();
		applyContentTemplate();
	}

	protected void applyImplementation() {
		unapplyImplementation();
		// TODO
	}

	protected void applyHandlers() {
		unapplyHandlers();
		for(XBLBinding binding : appliedBindings) {
			for(XBLHandler handler : binding.getHandlers()) {
				addEventListener(handler.getEventType(), handler, (handler.getPhase() == XBLHandler.Phase.capturing));
			}
		}
	}

	protected void applyContentTemplate() {
		unapplyContentTemplate();
		XBLContentTemplate template = getActiveContentTemplate();
		if(template != null) {
			// First verify that all the explicit children have insertion points:
			if(!insertionPointsExistForAllChildren(template)) {
				return;
			}

			// Clone the content template:
			ElementXBLImpl clone = (ElementXBLImpl)template.getElement().cloneNode(true);

			// Walk the template's contents, hooking things together
			NodeList nodes = clone.getElementsByTagName("*");
			for(int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if(node instanceof ElementXBLImpl) {
					ElementXBLImpl elt = (ElementXBLImpl)node;
					ElementXBLImpl parent = (ElementXBLImpl)elt.getParentNode();

					// Set bindingOwner:
					elt.bindingOwner = this;

					// For top-level nodes, flag them as such so that they will return
					// the binding owner as parentNode:
					if(parent == clone) {
						elt.isTopLevelAnonymousNode = true;
					}

					// Handle <children/> insertion points:
					if(BindingBuilder.XBL_NAMESPACE.equals(elt.getNamespaceURI()) &&
							"children".equals(elt.getLocalName())) {
						for(Node kid = getFirstChild(); kid != null; kid = kid.getNextSibling()) {
							// TODO
						}
					}
				}
			}

			// Fire 'contentgenerated' event:
			Event event = ownerDocumentXBL.createEvent("Events");
			event.initEvent("contentgenerated", true, true);
			dispatchEvent(event);
		}
	}

	/**
	 * Determine if the given XBL content template contains &lt;children/> insertion points
	 * for all this element's child nodes. If not, content insertion will not take place.
	 */
	private boolean insertionPointsExistForAllChildren(XBLContentTemplate template) {
		Set<String> includes = new HashSet<String>();
		for(XBLChildrenInsertionPoint insertionPoint : template.getInsertionPoints()) {
			// If fallback template, return true:
			if(insertionPoint.isFallback()) {
				return true;
			}
			for(String tag : insertionPoint.getIncludes()) {
				includes.add(tag);
			}
		}

		for(Node kid = getFirstChild(); kid != null; kid = kid.getNextSibling()) {
			if(!includes.contains(kid.getLocalName())) {
				return false;
			}
		}
		return true;
	}


	protected void unapplyImplementation() {
		// TODO
	}

	protected void unapplyHandlers() {
		// Remove handler event listeners:
		for(XBLBinding binding : appliedBindings) {
			for(XBLHandler handler : binding.getHandlers()) {
				removeEventListener(handler.getEventType(), handler, (handler.getPhase() == XBLHandler.Phase.capturing));
			}
		}
	}

	protected void unapplyContentTemplate() {
		if(xblChildNodes == null) {
			// No anonymous content was applied, so no need to do anything else
			return;
		}

		for(Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
			if(node instanceof ElementXBLImpl) {
				ElementXBLImpl elt = (ElementXBLImpl)node;
				elt.anonymousParent = null;
			}
		}
		xblChildNodes = null;

		// Fire 'contentdestroyed' event:
		Event event = ownerDocumentXBL.createEvent("Events");
		event.initEvent("contentdestroyed", true, true);
		dispatchEvent(event);
	}


	protected XBLContentTemplate getActiveContentTemplate() {
		for(int i = appliedBindings.size() - 1; i >= 0; i--) {
			XBLBinding binding = appliedBindings.get(i);
			while(binding != null) {
				XBLContentTemplate content = binding.getContentTemplate();
				if(content != null) {
					return content;
				}
				binding = binding.getParentBinding();
			}
		}
		return null;
	}
}
