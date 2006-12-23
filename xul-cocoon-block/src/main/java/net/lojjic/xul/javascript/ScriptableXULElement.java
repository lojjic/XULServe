package net.lojjic.xul.javascript;

import net.lojjic.xul.XULElement;

import org.w3c.dom.css.CSSStyleDeclaration;

/**
 * Wrapper class for {@link XULElement} instances, that lets them
 * behave like normal JavaScript objects.
 */
public class ScriptableXULElement extends ScriptableElement {

	protected XULElement delegateXULElement;
	
	public ScriptableXULElement(XULElement element) {
		super(element);
		this.delegateXULElement = element;
	}
	
	@Override
	public String getClassName() {
		return "XULElement";
	}
	
	
	
	public String jsGet_id() {
		return delegateXULElement.getId();
	}
	
	public void jsSet_id(String id) {
		delegateXULElement.setId(id);
	}
	
	public String jsGet_className() {
		return delegateXULElement.getClassName();
	}
	public void jsSet_className(String className) {
		delegateXULElement.setClassName(className);
	}
	
	
	// Layout properties
	
	public String jsGet_align() {
		return delegateXULElement.getAlign();
	}
	public void jsSet_align(String align) {
		delegateXULElement.setAlign(align);
	}
	
	public String jsGet_dir() {
		return delegateXULElement.getDir();
	}
	public void jsSet_dir(String dir) {
		delegateXULElement.setDir(dir);
	}
	
	public String jsGet_flex() {
		return delegateXULElement.getFlex();
	}
	public void jsSet_flex(String flex) {
		delegateXULElement.setFlex(flex);
	}
	
	public String jsGet_flexGroup() {
		return delegateXULElement.getFlexGroup();
	}
	public void jsSet_flexGroup(String flexGroup) {
		delegateXULElement.setFlexGroup(flexGroup);
	}
	
	public String jsGet_ordinal() {
		return delegateXULElement.getOrdinal();
	}
	public void jsSet_ordinal(String ordinal) {
		delegateXULElement.setOrdinal(ordinal);
	}
	
	public String jsGet_orient() {
		return delegateXULElement.getOrient();
	}
	public void jsSet_orient(String orient) {
		delegateXULElement.setOrient(orient);
	}
	
	public String jsGet_pack() {
		return delegateXULElement.getPack();
	}
	public void jsSet_pack(String pack) {
		delegateXULElement.setPack(pack);
	}
	
	
	// Properties for hiding element
	
	public boolean jsGet_hidden() {
		return delegateXULElement.isHidden();
	}
	public void jsSet_hidden(boolean hidden) {
		delegateXULElement.setHidden(hidden);
	}
	
	public boolean jsGet_collapsed() {
		return delegateXULElement.isCollapsed();
	}
	public void jsSet_collapsed(boolean collapsed) {
		delegateXULElement.setCollapsed(collapsed);
	}
	
	
	// Property for hooking up to broadcasters
	
	public String jsGet_observes() {
		return delegateXULElement.getObserves();
	}
	public void jsSet_observes(String observes) {
		delegateXULElement.setObserves(observes);
	}
	
	
	// Properties for hooking up to popups
	
	public String jsGet_width() {
		return delegateXULElement.getWidth();
	}
	public void jsSet_width(String width) {
		delegateXULElement.setWidth(width);
	}
	
	public String jsGet_height() {
		return delegateXULElement.getHeight();
	}
	public void jsSet_height(String height) {
		delegateXULElement.setHeight(height);
	}
	
	public String jsGet_minWidth() {
		return delegateXULElement.getMinWidth();
	}
	public void jsSet_minWidth(String minWidth) {
		delegateXULElement.setMinWidth(minWidth);
	}
	
	public String jsGet_minHeight() {
		return delegateXULElement.getMinHeight();
	}
	public void jsSet_minHeight(String minHeight) {
		delegateXULElement.setMinHeight(minHeight);
	}
	
	public String jsGet_maxWidth() {
		return delegateXULElement.getMaxWidth();
	}
	public void jsSet_maxWidth(String maxWidth) {
		delegateXULElement.setMaxWidth(maxWidth);
	}
	
	public String jsGet_maxHeight() {
		return delegateXULElement.getMaxHeight();
	}
	public void jsSet_maxHeight(String maxHeight) {
		delegateXULElement.setMaxHeight(maxHeight);
	}
	
	
	// Persistence
	
	public String jsGet_persist() {
		return delegateXULElement.getPersist();
	}
	public void jsSet_persist(String persist) {
		delegateXULElement.setPersist(persist);
	}
	
	
	// Position properties for
	// * popups = these are screen coordinates
	// * other elements - these are client coordinates relative to parent stack.
	
	public String jsGet_left() {
		return delegateXULElement.getLeft();
	}
	public void jsSet_left(String left) {
		delegateXULElement.setLeft(left);
	}
	
	public String jsGet_top() {
		return delegateXULElement.getTop();
	}
	public void jsSet_top(String top) {
		delegateXULElement.setTop(top);
	}
	
	
	// XUL Template Builder
	
	public String jsGet_datasources() {
		return delegateXULElement.getDatasources();
	}
	public void jsSet_datasources(String datasources) {
		delegateXULElement.setDatasources(datasources);
	}
	
	public String jsGet_ref() {
		return delegateXULElement.getRef();
	}
	public void jsSet_ref(String ref) {
		delegateXULElement.setRef(ref);
	}
	
	
	// Tooltip and status info
	
	public String jsGet_tooltipText() {
		return delegateXULElement.getTooltipText();
	}
	public void jsSet_tooltipText(String tooltipText) {
		delegateXULElement.setTooltipText(tooltipText);
	}
	
	public String jsGet_statusText() {
		return delegateXULElement.getStatusText();
	}
	public void jsSet_statusText(String statusText) {
		delegateXULElement.setStatusText(statusText);
	}
	
	public boolean jsGet_allowEvents() {
		return delegateXULElement.isAllowEvents();
	}
	public void jsSet_allowEvents(boolean allowEvents) {
		delegateXULElement.setAllowEvents(allowEvents);
	}
	
	
	public CSSStyleDeclaration jsGet_style() {
		return delegateXULElement.getStyle();
	}
	
	
	// RDFCompositeDataSource getDatabase(); //readonly
	// XULTemplateBuilder getBuilder(); //readonly
	// RDFResource getResource(); //readonly
	// Controllers getControllers(); //readonly
	// BoxObject getBoxObject(); //readonly
	
	
	public void jsFunction_focus() {
		delegateXULElement.focus();
	}
	
	public void jsFunction_blur() {
		delegateXULElement.blur();
	}
	
	public void jsFunction_click() {
		delegateXULElement.click();
	}
	
	public void jsFunction_doCommand() {
		delegateXULElement.doCommand();
	}
	
	
	
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
	public ScriptableNodeList jsFunction_getElementsByAttribute(String name, String value) {
		return new ScriptableNodeList(delegateXULElement.getElementsByAttribute(name, value));
	}

}
