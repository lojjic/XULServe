package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULElement;
import org.mozilla.javascript.Wrapper;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;

/**
 *
 */
@JSClassName("XULElement")
public class XULElementJSWrapper implements /*XULElement,*/ Wrapper {

	protected XULElement wrappedXULElement;

	public XULElementJSWrapper(XULElement wrappedXULElement) {
		this.wrappedXULElement = wrappedXULElement;
	}

	@JSFunction("blur")
	public void blur() {
		wrappedXULElement.blur();
	}

	@JSFunction("click")
	public void click() {
		wrappedXULElement.click();
	}

	@JSFunction("doCommand")
	public void doCommand() {
		wrappedXULElement.doCommand();
	}

	@JSFunction("focus")
	public void focus() {
		wrappedXULElement.focus();
	}

	@JSGetter("align")
	public String getAlign() {
		return wrappedXULElement.getAlign();
	}

	@JSGetter("className")
	public String getClassName() {
		return wrappedXULElement.getClassName();
	}

	@JSGetter("datasources")
	public String getDatasources() {
		return wrappedXULElement.getDatasources();
	}

	@JSGetter("dir")
	public String getDir() {
		return wrappedXULElement.getDir();
	}

	@JSFunction("getElementsByAttribute")
	public NodeList getElementsByAttribute(String name, String value) {
		return wrappedXULElement.getElementsByAttribute(name, value);
	}

	@JSGetter("flex")
	public String getFlex() {
		return wrappedXULElement.getFlex();
	}

	@JSGetter("flexGroup")
	public String getFlexGroup() {
		return wrappedXULElement.getFlexGroup();
	}

	@JSGetter("height")
	public String getHeight() {
		return wrappedXULElement.getHeight();
	}

	@JSGetter("id")
	public String getId() {
		return wrappedXULElement.getId();
	}

	@JSGetter("left")
	public String getLeft() {
		return wrappedXULElement.getLeft();
	}

	@JSGetter("maxHeight")
	public String getMaxHeight() {
		return wrappedXULElement.getMaxHeight();
	}

	@JSGetter("maxWidth")
	public String getMaxWidth() {
		return wrappedXULElement.getMaxWidth();
	}

	@JSGetter("minHeight")
	public String getMinHeight() {
		return wrappedXULElement.getMinHeight();
	}

	@JSGetter("minWidth")
	public String getMinWidth() {
		return wrappedXULElement.getMinWidth();
	}

	@JSGetter("observes")
	public String getObserves() {
		return wrappedXULElement.getObserves();
	}

	@JSGetter("ordinal")
	public String getOrdinal() {
		return wrappedXULElement.getOrdinal();
	}

	@JSGetter("orient")
	public String getOrient() {
		return wrappedXULElement.getOrient();
	}

	@JSGetter("pack")
	public String getPack() {
		return wrappedXULElement.getPack();
	}

	@JSGetter("persist")
	public String getPersist() {
		return wrappedXULElement.getPersist();
	}

	@JSGetter("ref")
	public String getRef() {
		return wrappedXULElement.getRef();
	}

	@JSGetter("statusText")
	public String getStatusText() {
		return wrappedXULElement.getStatusText();
	}

	@JSGetter("style")
	public CSSStyleDeclaration getStyle() {
		return wrappedXULElement.getStyle();
	}

	@JSGetter("tooltipText")
	public String getTooltipText() {
		return wrappedXULElement.getTooltipText();
	}

	@JSGetter("top")
	public String getTop() {
		return wrappedXULElement.getTop();
	}

	@JSGetter("width")
	public String getWidth() {
		return wrappedXULElement.getWidth();
	}

	@JSGetter("allowEvents")
	public boolean isAllowEvents() {
		return wrappedXULElement.isAllowEvents();
	}

	@JSGetter("collapsed")
	public boolean isCollapsed() {
		return wrappedXULElement.isCollapsed();
	}

	@JSGetter("hidden")
	public boolean isHidden() {
		return wrappedXULElement.isHidden();
	}

	@JSSetter("align")
	public void setAlign(String align) {
		wrappedXULElement.setAlign(align);
	}

	@JSSetter("allowEvents")
	public void setAllowEvents(boolean allowEvents) {
		wrappedXULElement.setAllowEvents(allowEvents);
	}

	@JSSetter("className")
	public void setClassName(String className) {
		wrappedXULElement.setClassName(className);
	}

	@JSSetter("collapsed")
	public void setCollapsed(boolean collapsed) {
		wrappedXULElement.setCollapsed(collapsed);
	}

	@JSSetter("datasources")
	public void setDatasources(String datasources) {
		wrappedXULElement.setDatasources(datasources);
	}

	@JSSetter("dir")
	public void setDir(String dir) {
		wrappedXULElement.setDir(dir);
	}

	@JSSetter("flex")
	public void setFlex(String flex) {
		wrappedXULElement.setFlex(flex);
	}

	@JSSetter("flexGroup")
	public void setFlexGroup(String flexGroup) {
		wrappedXULElement.setFlexGroup(flexGroup);
	}

	@JSSetter("height")
	public void setHeight(String height) {
		wrappedXULElement.setHeight(height);
	}

	@JSSetter("hidden")
	public void setHidden(boolean hidden) {
		wrappedXULElement.setHidden(hidden);
	}

	@JSSetter("id")
	public void setId(String id) {
		wrappedXULElement.setId(id);
	}

	@JSSetter("left")
	public void setLeft(String left) {
		wrappedXULElement.setLeft(left);
	}

	@JSSetter("maxHeight")
	public void setMaxHeight(String maxHeight) {
		wrappedXULElement.setMaxHeight(maxHeight);
	}

	@JSSetter("maxWidth")
	public void setMaxWidth(String maxWidth) {
		wrappedXULElement.setMaxWidth(maxWidth);
	}

	@JSSetter("minHeight")
	public void setMinHeight(String minHeight) {
		wrappedXULElement.setMinHeight(minHeight);
	}

	@JSSetter("minWidth")
	public void setMinWidth(String minWidth) {
		wrappedXULElement.setMinWidth(minWidth);
	}

	@JSSetter("observes")
	public void setObserves(String observes) {
		wrappedXULElement.setObserves(observes);
	}

	@JSSetter("ordinal")
	public void setOrdinal(String ordinal) {
		wrappedXULElement.setOrdinal(ordinal);
	}

	@JSSetter("orient")
	public void setOrient(String orient) {
		wrappedXULElement.setOrient(orient);
	}

	@JSSetter("pack")
	public void setPack(String pack) {
		wrappedXULElement.setPack(pack);
	}

	@JSSetter("persist")
	public void setPersist(String persist) {
		wrappedXULElement.setPersist(persist);
	}

	@JSSetter("ref")
	public void setRef(String ref) {
		wrappedXULElement.setRef(ref);
	}

	@JSSetter("statusText")
	public void setStatusText(String statusText) {
		wrappedXULElement.setStatusText(statusText);
	}

	@JSSetter("tooltipText")
	public void setTooltipText(String tooltipText) {
		wrappedXULElement.setTooltipText(tooltipText);
	}

	@JSSetter("top")
	public void setTop(String top) {
		wrappedXULElement.setTop(top);
	}

	@JSSetter("width")
	public void setWidth(String width) {
		wrappedXULElement.setWidth(width);
	}

	public Object unwrap() {
		return wrappedXULElement;
	}
}
