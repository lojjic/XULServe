package net.lojjic.xul.xbl.impl;

import org.apache.xalan.xsltc.trax.DOM2SAX;
import org.w3c.dom.*;

import net.lojjic.xul.xbl.ElementXBL;

/**
 * Specialized {@link DOM2SAX} that walks the XBL DOM after anonymous
 * content insertion.
 */
public class XBLDOM2SAX extends DOM2SAX {

	public XBLDOM2SAX(Node root) {
		super(new NodeWrapper(root));
	}


	/**
	 * Wrapper for DOM nodes that delegates everything to the wrapped
	 * delegate, except for the methods that navigate immediate children and
	 * siblings. Those methods are overridden to navigate the XBL content
	 * model after repositioning by the content template. This has the effect
	 * of making {@link DOM2SAX#parse()} stream SAX events for the entire
	 * XBL content model including anonymous nodes.
	 * <p>
	 * Implementation note: rather than creating a new NodeWrapper instance,
	 * the navigation methods change the delegate and return the same instance.
	 * This prevents a lot of extra object creation, but relies on an assumption
	 * that the extended {@link org.apache.xalan.xsltc.trax.DOM2SAX#parse()}
	 * will not call any method on the Node after calling one of those overridden
	 * navigation methods.
	 */
	private static class NodeWrapper implements Node {
		private Node delegate;
		private boolean isElementXBL;

		public NodeWrapper(Node node) {
			setDelegateNode(node);
		}

		private void setDelegateNode(Node node) {
			this.delegate = node;
			isElementXBL = (node instanceof ElementXBL);
		}

		/**
		 * @return child nodes after XBL content template evaluation
		 */
		public NodeList getChildNodes() {
			if(isElementXBL) {
				final Node self = this;
				final NodeList nodes = ((ElementXBL)delegate).getXblChildNodes();
				return new NodeList() {
					public int getLength() {
						return nodes.getLength();
					}
					public Node item(int index) {
						setDelegateNode(nodes.item(index));
						return self;
					}
				};
			}
			else {
				return delegate.getChildNodes();
			}
		}

		/**
		 * @return first child node after XBL content template evaluation
		 */
		public Node getFirstChild() {
			Node node;
			if(isElementXBL) {
				NodeList nodes = ((ElementXBL)delegate).getXblChildNodes();
				node = (nodes.getLength() == 0 ? null : nodes.item(0));
			}
			else {
				node = delegate.getFirstChild();
			}
			setDelegateNode(node);
			return this;
		}

		/**
		 * @return last child node after XBL content template evaluation
		 */
		public Node getLastChild() {
			Node node;
			if(isElementXBL) {
				NodeList nodes = ((ElementXBL)delegate).getXblChildNodes();
				node = nodes.getLength() == 0 ? null : nodes.item(nodes.getLength() - 1);
			}
			else {
				node = delegate.getLastChild();
			}
			setDelegateNode(node);
			return this;
		}

		/**
		 * @return next sibling node after XBL content template evaluation
		 */
		public Node getNextSibling() {
			Node node;
			if(isElementXBL) {
				// TODO
			}
			else {
				node = delegate.getNextSibling();
			}
			setDelegateNode(node);
			return this;
		}

		/**
		 * @return previous sibling node after XBL content template evaluation
		 */
		public Node getPreviousSibling() {
			Node node;
			if(isElementXBL) {
				// TODO
			}
			else {
				node = delegate.getPreviousSibling();
			}
			setDelegateNode(node);
			return this;
		}


		public Node appendChild(Node newChild) throws DOMException {
			return delegate.appendChild(newChild);
		}

		public Node cloneNode(boolean deep) {
			return delegate.cloneNode(deep);
		}

		public short compareDocumentPosition(Node other) throws DOMException {
			return delegate.compareDocumentPosition(other);
		}

		public NamedNodeMap getAttributes() {
			return delegate.getAttributes();
		}

		public String getBaseURI() {
			return delegate.getBaseURI();
		}

		public Object getFeature(String feature, String version) {
			return delegate.getFeature(feature, version);
		}

		public String getLocalName() {
			return delegate.getLocalName();
		}

		public String getNamespaceURI() {
			return delegate.getNamespaceURI();
		}

		public String getNodeName() {
			return delegate.getNodeName();
		}

		public short getNodeType() {
			return delegate.getNodeType();
		}

		public String getNodeValue() throws DOMException {
			return delegate.getNodeValue();
		}

		public Document getOwnerDocument() {
			return delegate.getOwnerDocument();
		}

		public Node getParentNode() {
			return delegate.getParentNode();
		}

		public String getPrefix() {
			return delegate.getPrefix();
		}

		public String getTextContent() throws DOMException {
			return delegate.getTextContent();
		}

		public Object getUserData(String key) {
			return delegate.getUserData(key);
		}

		public boolean hasAttributes() {
			return delegate.hasAttributes();
		}

		public boolean hasChildNodes() {
			return delegate.hasChildNodes();
		}

		public Node insertBefore(Node newChild, Node refChild) throws DOMException {
			return delegate.insertBefore(newChild, refChild);
		}

		public boolean isDefaultNamespace(String namespaceURI) {
			return delegate.isDefaultNamespace(namespaceURI);
		}

		public boolean isEqualNode(Node arg) {
			return delegate.isEqualNode(arg);
		}

		public boolean isSameNode(Node other) {
			return delegate.isSameNode(other);
		}

		public boolean isSupported(String feature, String version) {
			return delegate.isSupported(feature, version);
		}

		public String lookupNamespaceURI(String prefix) {
			return delegate.lookupNamespaceURI(prefix);
		}

		public String lookupPrefix(String namespaceURI) {
			return delegate.lookupPrefix(namespaceURI);
		}

		public void normalize() {
			delegate.normalize();
		}

		public Node removeChild(Node oldChild) throws DOMException {
			return delegate.removeChild(oldChild);
		}

		public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
			return delegate.replaceChild(newChild, oldChild);
		}

		public void setNodeValue(String nodeValue) throws DOMException {
			delegate.setNodeValue(nodeValue);
		}

		public void setPrefix(String prefix) throws DOMException {
			delegate.setPrefix(prefix);
		}

		public void setTextContent(String textContent) throws DOMException {
			delegate.setTextContent(textContent);
		}

		public Object setUserData(String key, Object data, UserDataHandler handler) {
			return delegate.setUserData(key, data, handler);
		}
	}

}
