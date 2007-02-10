package net.lojjic.xul.xbl.impl;

import org.apache.xalan.xsltc.trax.DOM2SAX;
import org.w3c.dom.*;

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
	 */
	private static class NodeWrapper implements Node {
		private Node delegate;

		public NodeWrapper(Node node) {
			this.delegate = node;
		}

		/**
		 * @return child nodes after XBL content template evaluation
		 */
		public NodeList getChildNodes() {
			if(delegate instanceof ElementXBLImpl) {
				final NodeList nodes = ((ElementXBLImpl)delegate).getXblChildNodes();
				return new NodeList() {
					public int getLength() {
						return nodes.getLength();
					}
					public Node item(int index) {
						return new NodeWrapper(nodes.item(index));
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
			if(delegate instanceof ElementXBLImpl) {
				node = ((ElementXBLImpl)delegate).getXblFirstChild();
			}
			else {
				node = delegate.getFirstChild();
			}
			return (node == null ? null : new NodeWrapper(node));
		}

		/**
		 * @return last child node after XBL content template evaluation
		 */
		public Node getLastChild() {
			Node node;
			if(delegate instanceof ElementXBLImpl) {
				node = ((ElementXBLImpl)delegate).getXblLastChild();
			}
			else {
				node = delegate.getLastChild();
			}
			return (node == null ? null : new NodeWrapper(node));
		}

		/**
		 * @return next sibling node after XBL content template evaluation
		 */
		public Node getNextSibling() {
			Node node;
			if(delegate instanceof ElementXBLImpl) {
				node = ((ElementXBLImpl)delegate).getXblNextSibling();
			}
			else {
				node = delegate.getNextSibling();
			}
			return (node == null ? null : new NodeWrapper(node));
		}

		/**
		 * @return previous sibling node after XBL content template evaluation
		 */
		public Node getPreviousSibling() {
			Node node;
			if(delegate instanceof ElementXBLImpl) {
				node = ((ElementXBLImpl)delegate).getXblPreviousSibling();
			}
			else {
				node = delegate.getPreviousSibling();
			}
			return (node == null ? null : new NodeWrapper(node));
		}

		/**
		 * @return parent node after XBL content template evaluation
		 */
		public Node getParentNode() {
			Node node;
			if(delegate instanceof ElementXBLImpl) {
				node = ((ElementXBLImpl)delegate).getXblParentNode();
			}
			else {
				node = delegate.getPreviousSibling();
			}
			return (node == null ? null : new NodeWrapper(node));
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
