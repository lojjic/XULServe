package net.lojjic.xul.xbl.impl;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.xml.utils.TreeWalker;
import org.w3c.dom.Node;

/**
 * Specialization of Xalan's {@link TreeWalker} that is aware of the XBL
 * anonymous content model.  Walks the XBL DOM tree complete with anonymous
 * nodes, producing SAX events along the way.
 */
public class XBLAwareDOMStreamer extends TreeWalker {

	public XBLAwareDOMStreamer(ContentHandler contentHandler) {
		super(contentHandler);
	}

	/**
	 * Same as superclass implementation, except that for ElementXBL nodes
	 * it calls the XBL-specific traversal methods.
	 */
	public void traverse(Node pos, Node top) throws SAXException {
		getContentHandler().startDocument();

		while (null != pos) {
			startNode(pos);

			Node nextNode = (pos instanceof ElementXBLImpl) ? ((ElementXBLImpl)pos).getXblFirstChild() : pos.getFirstChild();

			while (null == nextNode) {
				endNode(pos);

				if ((null != top) && top.equals(pos)) {
					break;
				}

				nextNode = (pos instanceof ElementXBLImpl) ? ((ElementXBLImpl)pos).getXblNextSibling() : pos.getNextSibling();

				if (null == nextNode) {
					pos = (pos instanceof ElementXBLImpl) ? ((ElementXBLImpl)pos).getXblParentNode() : pos.getParentNode();

					if ((null == pos) || ((null != top) && top.equals(pos))) {
						nextNode = null;
						break;
					}
				}
			}

			pos = nextNode;
		}

		getContentHandler().endDocument();
	}


	public void traverse(Node pos) throws SAXException {
		traverse(pos, pos);
	}

}
