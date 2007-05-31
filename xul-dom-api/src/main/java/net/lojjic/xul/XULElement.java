package net.lojjic.xul;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.events.EventTarget;
import net.lojjic.xul.rdf.RDFCompositeDataSource;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.templates.XULTemplateBuilder;

public interface XULElement extends Element, EventTarget {
	
	String getId();
	void setId(String id);
	
	String getClassName();
	void setClassName(String className);
	
	
	// Layout properties
	
	String getAlign();
	void setAlign(String align);
	
	String getDir();
	void setDir(String dir);
	
	String getFlex();
	void setFlex(String flex);
	
	String getFlexGroup();
	void setFlexGroup(String flexGroup);
	
	String getOrdinal();
	void setOrdinal(String ordinal);
	
	String getOrient();
	void setOrient(String orient);
	
	String getPack();
	void setPack(String pack);
	
	
	// Properties for hiding element
	
	boolean isHidden();
	void setHidden(boolean hidden);
	
	boolean isCollapsed();
	void setCollapsed(boolean collapsed);
	
	
	// Property for hooking up to broadcasters
	
	String getObserves();
	void setObserves(String observes);
	
	
	// Properties for hooking up to popups
	
	String getWidth();
	void setWidth(String width);
	
	String getHeight();
	void setHeight(String height);
	
	String getMinWidth();
	void setMinWidth(String minWidth);
	
	String getMinHeight();
	void setMinHeight(String minHeight);
	
	String getMaxWidth();
	void setMaxWidth(String maxWidth);
	
	String getMaxHeight();
	void setMaxHeight(String maxHeight);
	
	
	// Persistence
	
	String getPersist();
	void setPersist(String persist);
	
	
	// Position properties for
	// * popups = these are screen coordinates
	// * other elements - these are client coordinates relative to parent stack.
	
	String getLeft();
	void setLeft(String left);
	
	String getTop();
	void setTop(String top);
	
	
	// XUL Template Builder
	
	String getDatasources();
	void setDatasources(String datasources);
	
	String getRef();
	void setRef(String ref);
	
	
	// Tooltip and status info
	
	String getTooltipText();
	void setTooltipText(String tooltipText);
	
	String getStatusText();
	void setStatusText(String statusText);
	
	boolean isAllowEvents();
	void setAllowEvents(boolean allowEvents);
	
	
	CSSStyleDeclaration getStyle(); //readonly
	
	
	RDFCompositeDataSource getDatabase(); //readonly
	XULTemplateBuilder getBuilder(); //readonly
	RDFResource getResource(); //readonly
	
	//Controllers getControllers(); //readonly
	//BoxObject getBoxObject(); //readonly
	
	
	void focus();
	void blur();
	void click();
	void doCommand();
	
	
	
	/**
	 * Retrieve all descdendant Elements for which the named attribute 
	 * has the given value. A value of "*" is a wildcard signifying all 
	 * elements with the attribute.
	 * 
	 * @param name The name of the attribute
	 * @param value The value of the attribute. A value of "*" is a wildcard 
	 * signifying all elements with the attribute.
	 * @return A NodeList of matching Elements
	 */
	NodeList getElementsByAttribute(String name, String value);
	
}
