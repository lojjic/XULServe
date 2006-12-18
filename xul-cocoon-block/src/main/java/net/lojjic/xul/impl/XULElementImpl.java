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

	private String id;
	private String className;
	private String align;
	private String dir;
	private String flex;
	private String flexGroup;
	private String ordinal;
	private String orient;
	private String pack;
	private boolean hidden;
	private boolean collapsed;
	private String observes;
	private String width;
	private String height;
	private String minWidth;
	private String minHeight;
	private String maxWidth;
	private String maxHeight;
	private String persist;
	private String left;
	private String top;
	private String datasources;
	private String ref;
	private String tooltipText;
	private String statusText;
	private boolean allowEvents;
	private CSSStyleDeclaration style;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	// FIXME - this method clashes with ScriptableObject.getClassName()
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFlex() {
		return flex;
	}

	public void setFlex(String flex) {
		this.flex = flex;
	}

	public String getFlexGroup() {
		return flexGroup;
	}

	public void setFlexGroup(String flexGroup) {
		this.flexGroup = flexGroup;
	}

	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public String getOrient() {
		return orient;
	}

	public void setOrient(String orient) {
		this.orient = orient;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public String getObserves() {
		return observes;
	}

	public void setObserves(String observes) {
		this.observes = observes;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getPersist() {
		return persist;
	}

	public void setPersist(String persist) {
		this.persist = persist;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getDatasources() {
		return datasources;
	}

	public void setDatasources(String datasources) {
		this.datasources = datasources;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public boolean isAllowEvents() {
		return allowEvents;
	}

	public void setAllowEvents(boolean allowEvents) {
		this.allowEvents = allowEvents;
	}

	public CSSStyleDeclaration getStyle() {
		return style;
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
		NodeList all = getElementsByTagName("*");
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
