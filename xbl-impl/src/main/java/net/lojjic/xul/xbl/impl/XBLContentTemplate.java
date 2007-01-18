package net.lojjic.xul.xbl.impl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * XBL &lt;content/> template
 */
public class XBLContentTemplate {

	private ElementXBLImpl element;
	private List<XBLChildrenInsertionPoint> insertionPoints;

	public XBLContentTemplate(ElementXBLImpl element) {
		this.element = element;

		NodeList childrenElts = element.getElementsByTagNameNS(BindingBuilder.XBL_NAMESPACE, "children");
		insertionPoints = new ArrayList<XBLChildrenInsertionPoint>(childrenElts.getLength());
		for(int i=0; i < childrenElts.getLength(); i++) {
			insertionPoints.add(new XBLChildrenInsertionPoint((Element)childrenElts.item(i)));
		}
	}

	public ElementXBLImpl getElement() {
		return element;
	}

	public List<XBLChildrenInsertionPoint> getInsertionPoints() {
		return Collections.unmodifiableList(insertionPoints);
	}

	public boolean hasInsertionPointForNode(Node node) {
		for(XBLChildrenInsertionPoint ins : insertionPoints) {
			if(ins.handlesNode(node)) {
				return true;
			}
		}
		return false;
	}
}
