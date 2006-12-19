package net.lojjic.xul.impl;

import java.util.ArrayList;
import java.util.List;

import net.lojjic.xul.XULElement;

import org.apache.excalibur.xml.xpath.NodeListImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;

public class XULElementImpl extends ScriptableElementImpl implements XULElement {
	
	public static final String XUL_NAMESPACE = "http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul";

	public String getId() {
		return getAttribute("id");
	}

	public void setId(String id) {
		setAttribute("id", id);
	}
	
	// FIXME - this method clashes with ScriptableObject.getClassName()
	public String getClassName() {
		return getAttribute("class");
	}

	public void setClassName(String className) {
		setAttribute("class", className);
	}

	public String getAlign() {
		return getAttribute("align");
	}

	public void setAlign(String align) {
		setAttribute("align", align);
	}

	public String getDir() {
		return getAttribute("dir");
	}

	public void setDir(String dir) {
		setAttribute("dir", dir);
	}

	public String getFlex() {
		return getAttribute("flex");
	}

	public void setFlex(String flex) {
		setAttribute("flex", flex);
	}

	public String getFlexGroup() {
		return getAttribute("flexgroup");
	}

	public void setFlexGroup(String flexGroup) {
		setAttribute("flexgroup", flexGroup);
	}

	public String getOrdinal() {
		return getAttribute("ordinal");
	}

	public void setOrdinal(String ordinal) {
		setAttribute("ordinal", ordinal);
	}

	public String getOrient() {
		return getAttribute("orient");
	}

	public void setOrient(String orient) {
		setAttribute("orient", orient);
	}

	public String getPack() {
		return getAttribute("pack");
	}

	public void setPack(String pack) {
		setAttribute("pack",  pack);
	}

	public boolean isHidden() {
		return Boolean.parseBoolean(getAttribute("hidden"));
	}

	public void setHidden(boolean hidden) {
		setAttribute("hidden", String.valueOf(hidden));
	}

	public boolean isCollapsed() {
		return Boolean.parseBoolean(getAttribute("collapsed"));
	}

	public void setCollapsed(boolean collapsed) {
		setAttribute("collapsed", String.valueOf(collapsed));
	}

	public String getObserves() {
		return getAttribute("observes");
	}

	public void setObserves(String observes) {
		setAttribute("observes", observes);
	}

	public String getWidth() {
		return getAttribute("width");
	}

	public void setWidth(String width) {
		setAttribute("width", width);
	}

	public String getHeight() {
		return getAttribute("height");
	}

	public void setHeight(String height) {
		setAttribute("height", height);
	}

	public String getMinWidth() {
		return getAttribute("minWidth");
	}

	public void setMinWidth(String minWidth) {
		setAttribute("minWidth", minWidth);
	}

	public String getMinHeight() {
		return getAttribute("minHeight");
	}

	public void setMinHeight(String minHeight) {
		setAttribute("minHeight", minHeight);
	}

	public String getMaxWidth() {
		return getAttribute("maxWidth");
	}

	public void setMaxWidth(String maxWidth) {
		setAttribute("maxWidth", maxWidth);
	}

	public String getMaxHeight() {
		return getAttribute("maxHeight");
	}

	public void setMaxHeight(String maxHeight) {
		setAttribute("maxHeight", maxHeight);
	}

	public String getPersist() {
		return getAttribute("persist");
	}

	public void setPersist(String persist) {
		setAttribute("persist", persist);
	}

	public String getLeft() {
		return getAttribute("left");
	}

	public void setLeft(String left) {
		setAttribute("left", left);
	}

	public String getTop() {
		return getAttribute("top");
	}

	public void setTop(String top) {
		setAttribute("top", top);
	}

	public String getDatasources() {
		return getAttribute("datasources");
	}

	public void setDatasources(String datasources) {
		setAttribute("datasources", datasources);
	}

	public String getRef() {
		return getAttribute("ref");
	}

	public void setRef(String ref) {
		setAttribute("ref", ref);
	}

	public String getTooltipText() {
		return getAttribute("tooltipText");
	}

	public void setTooltipText(String tooltipText) {
		setAttribute("tooltipText", tooltipText);
	}

	public String getStatusText() {
		return getAttribute("statusText");
	}

	public void setStatusText(String statusText) {
		setAttribute("statusText", statusText);
	}

	public boolean isAllowEvents() {
		return Boolean.parseBoolean(getAttribute("allowEvents"));
	}

	public void setAllowEvents(boolean allowEvents) {
		setAttribute("allowEvents", String.valueOf(allowEvents));
	}

	public CSSStyleDeclaration getStyle() {
		return getAttribute("style");
	}

	public void focus() {
		// TODO
	}

	public void blur() {
		// TODO		
	}

	public void click() {
		// TODO
	}

	public void doCommand() {
		// TODO
	}

	public NodeList getElementsByAttribute(String name, String value) {
		// TODO find a more efficient way to perform this search
		NodeList all = getElementsByTagNameNS(XUL_NAMESPACE, "*");
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

}
