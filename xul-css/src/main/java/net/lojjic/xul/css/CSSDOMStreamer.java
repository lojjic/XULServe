package net.lojjic.xul.css;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.xerces.dom.AttrNSImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xml.utils.TreeWalker;
import org.w3c.dom.*;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * Specialization of Xalan's {@link org.apache.xml.utils.TreeWalker} that adds
 * extra attributes when streaming each element which describe the current computed
 * styles of that element.
 */
public class CSSDOMStreamer extends TreeWalker {

	public static final String CSS_ATTR_NAMESPACE = "http://lojjic.net/ns/css-attr";
	public static final String CSS_ATTR_PREFIX = "css";

	public CSSDOMStreamer(ContentHandler contentHandler) {
		super(contentHandler);
	}

	/**
	 * Start processing given node
	 *
	 * @param node Node to process
	 * @throws org.xml.sax.SAXException
	 */
	@Override
	protected void startNode(Node node) throws SAXException {
		super.startNode(wrapNode(node));
	}

	/**
	 * End processing of given node
	 *
	 * @param node Node we just finished processing
	 * @throws org.xml.sax.SAXException
	 */
	@Override
	protected void endNode(Node node) throws SAXException {
		super.endNode(wrapNode(node));
	}


	/**
	 * Wrap the given node
	 * @param node
	 * @return
	 */
	private Node wrapNode(Node node) {
		if(node instanceof CSSStylableElement) {
			return new ElementWrapper((CSSStylableElement)node);
		}
		return node;
	}

	private static class ElementWrapper implements Element {
		private CSSStylableElement delegate;

		private ElementWrapper(CSSStylableElement delegate) {
			this.delegate = delegate;
		}

		public NamedNodeMap getAttributes() {
			return new AttributesWrapper(delegate.getAttributes(), delegate);
		}

		public String getBaseURI() {
			return delegate.getBaseURI();
		}

		public NodeList getChildNodes() {
			return delegate.getChildNodes();
		}

		public Object getFeature(String feature, String version) {
			return delegate.getFeature(feature, version);
		}

		public Node getFirstChild() {
			return delegate.getFirstChild();
		}

		public Node getLastChild() {
			return delegate.getLastChild();
		}

		public String getLocalName() {
			return delegate.getLocalName();
		}

		public String getNamespaceURI() {
			return delegate.getNamespaceURI();
		}

		public Node getNextSibling() {
			return delegate.getNextSibling();
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

		public Node getPreviousSibling() {
			return delegate.getPreviousSibling();
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

		public String getAttribute(String name) {
			return delegate.getAttribute(name);
		}

		public Attr getAttributeNode(String name) {
			return delegate.getAttributeNode(name);
		}

		public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException {
			return delegate.getAttributeNodeNS(namespaceURI, localName);
		}

		public String getAttributeNS(String namespaceURI, String localName) throws DOMException {
			return delegate.getAttributeNS(namespaceURI, localName);
		}

		public NodeList getElementsByTagName(String name) {
			return delegate.getElementsByTagName(name);
		}

		public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException {
			return delegate.getElementsByTagNameNS(namespaceURI, localName);
		}

		public TypeInfo getSchemaTypeInfo() {
			return delegate.getSchemaTypeInfo();
		}

		public String getTagName() {
			return delegate.getTagName();
		}

		public boolean hasAttribute(String name) {
			return delegate.hasAttribute(name);
		}

		public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException {
			return delegate.hasAttributeNS(namespaceURI, localName);
		}

		public void removeAttribute(String name) throws DOMException {
			delegate.removeAttribute(name);
		}

		public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
			return delegate.removeAttributeNode(oldAttr);
		}

		public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
			delegate.removeAttributeNS(namespaceURI, localName);
		}

		public void setAttribute(String name, String value) throws DOMException {
			delegate.setAttribute(name, value);
		}

		public Attr setAttributeNode(Attr newAttr) throws DOMException {
			return delegate.setAttributeNode(newAttr);
		}

		public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
			return delegate.setAttributeNodeNS(newAttr);
		}

		public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
			delegate.setAttributeNS(namespaceURI, qualifiedName, value);
		}

		public void setIdAttribute(String name, boolean isId) throws DOMException {
			delegate.setIdAttribute(name, isId);
		}

		public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
			delegate.setIdAttributeNode(idAttr, isId);
		}

		public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
			delegate.setIdAttributeNS(namespaceURI, localName, isId);
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
	}

	private static class AttributesWrapper implements NamedNodeMap {
		private NamedNodeMap delegate;
		private CSSStylableElement element;
		private CSSEngine engine;
		private StyleMap styleMap;

		private AttributesWrapper(NamedNodeMap delegate, CSSStylableElement element) {
			this.delegate = delegate;
			this.element = element;
			this.engine = ((DocumentCSSImpl)element.getOwnerDocument()).getCssEngine();
			this.styleMap = engine.getCascadedStyleMap(element, null);
		}

		public int getLength() {
			return delegate.getLength() + engine.getNumberOfProperties();
		}

		public Node item(int index) {
			int normalAttrsCount = delegate.getLength();
			if(index < normalAttrsCount) {
				return delegate.item(index);
			} else {
				int cssIndex = index - normalAttrsCount;
				String cssName = engine.getPropertyName(cssIndex);
				Value cssValue = styleMap.getValue(cssIndex);
				Attr attr = new AttrNSImpl((CoreDocumentImpl)element.getOwnerDocument(),
						CSS_ATTR_NAMESPACE, CSS_ATTR_PREFIX + ":" + cssName, cssName);
				attr.setValue(cssValue.getCssText());
				return attr;
			}
		}

		public Node getNamedItem(String name) {
			return delegate.getNamedItem(name);
		}

		public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
			return delegate.getNamedItemNS(namespaceURI, localName);
		}

		public Node removeNamedItem(String name) throws DOMException {
			return delegate.removeNamedItem(name);
		}

		public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
			return delegate.removeNamedItemNS(namespaceURI, localName);
		}

		public Node setNamedItem(Node arg) throws DOMException {
			return delegate.setNamedItem(arg);
		}

		public Node setNamedItemNS(Node arg) throws DOMException {
			return delegate.setNamedItemNS(arg);
		}

	}

}
