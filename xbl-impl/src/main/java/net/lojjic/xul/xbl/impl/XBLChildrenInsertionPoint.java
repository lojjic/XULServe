package net.lojjic.xul.xbl.impl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Set;
import java.util.HashSet;

/**
 * XBL &lt;children/> insertion point
 */
public class XBLChildrenInsertionPoint {

	private boolean fallback;
	private Set<String> includes;

	/**
	 * Constructor
	 * @param element - The &lt;xbl:children/> insertion point Element
	 */
	public XBLChildrenInsertionPoint(Element element) {
		String includesAttr = element.getAttribute("includes");
		if(includesAttr == null) {
			fallback = true;
		}
		else {
			includes = new HashSet<String>();
			// TODO some docs say separator is |, some say comma
			for(String tag : includesAttr.split("|")) {
				includes.add(tag.trim());
			}
		}
	}

	/**
	 * Whether this insertion point is a fallback, i.e. has no 'includes' attribute
	 */
	public boolean isFallback() {
		return fallback;
	}

	/**
	 * Whether this insertion point will handle the given element name, i.e.
	 * that element name appears in the 'includes' attribute.
	 * @param node
	 */
	public boolean handlesNode(Node node) {
		return fallback || includes.contains(node.getLocalName());
	}

	/**
	 * Get the element names that this insertion point will handle
	 */
	public String[] getIncludes() {
		return includes.toArray(new String[includes.size()]);
	}
}
