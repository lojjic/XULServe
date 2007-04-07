package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xml.javascript.ScriptableElement;
import net.lojjic.xul.XULElement;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

/**
 * Scriptable wrapper for {@link XULElement} instances.
 */
@JSClassName("XULElement")
public class ScriptableXULElement<T extends XULElement> extends ScriptableElement<T> {

	public ScriptableXULElement() {
		super();
	}

	public ScriptableXULElement(Scriptable scope, T element) {
		super(scope, element);
	}
	
	@JSGetter("id")
	public String getId() {
		return unwrap().getId();
	}

	@JSSetter("id")
	public void setId(String id) {
		unwrap().setId(id);
	}

	@JSGetter("className")
	public String getCSSClassName() {
		return unwrap().getClassName();
	}

	@JSSetter("className")
	public void setCSSClassName(String className) {
		unwrap().setClassName(className);
	}
	
	
	// Layout properties
	
	@JSGetter("align")
	public String getAlign() {
		return unwrap().getAlign();
	}
	@JSSetter("align")
	public void setAlign(String align) {
		unwrap().setAlign(align);
	}
	
	@JSGetter("dir")
	public String getDir() {
		return unwrap().getDir();
	}
	@JSSetter("dir")
	public void setDir(String dir) {
		unwrap().setDir(dir);
	}
	
	@JSGetter("flex")
	public String getFlex() {
		return unwrap().getFlex();
	}
	@JSSetter("flex")
	public void setFlex(String flex) {
		unwrap().setFlex(flex);
	}
	
	@JSGetter("flexGroup")
	public String getFlexGroup() {
		return unwrap().getFlexGroup();
	}
	@JSSetter("flexGroup")
	public void setFlexGroup(String flexGroup) {
		unwrap().setFlexGroup(flexGroup);
	}
	
	@JSGetter("ordinal")
	public String getOrdinal() {
		return unwrap().getOrdinal();
	}
	@JSSetter("ordinal")
	public void setOrdinal(String ordinal) {
		unwrap().setOrdinal(ordinal);
	}
	
	@JSGetter("orient")
	public String getOrient() {
		return unwrap().getOrient();
	}
	@JSSetter("orient")
	public void setOrient(String orient) {
		unwrap().setOrient(orient);
	}
	
	@JSGetter("pack")
	public String getPack() {
		return unwrap().getPack();
	}
	@JSSetter("pack")
	public void setPack(String pack) {
		unwrap().setPack(pack);
	}
	
	
	// Properties for hiding element
	
	@JSGetter("hidden")
	public boolean isHidden() {
		return unwrap().isHidden();
	}
	@JSSetter("hidden")
	public void setHidden(boolean hidden) {
		unwrap().setHidden(hidden);
	}
	
	@JSGetter("collapsed")
	public boolean isCollapsed() {
		return unwrap().isCollapsed();
	}
	@JSSetter("collapsed")
	public void setCollapsed(boolean collapsed) {
		unwrap().setCollapsed(collapsed);
	}
	
	
	// Property for hooking up to broadcasters
	
	@JSGetter("observes")
	public String getObserves() {
		return unwrap().getObserves();
	}
	@JSSetter("observes")
	public void setObserves(String observes) {
		unwrap().setObserves(observes);
	}
	
	
	// Properties for hooking up to popups
	
	@JSGetter("width")
	public String getWidth() {
		return unwrap().getWidth();
	}
	@JSSetter("width")
	public void setWidth(String width) {
		unwrap().setWidth(width);
	}
	
	@JSGetter("height")
	public String getHeight() {
		return unwrap().getHeight();
	}
	@JSSetter("height")
	public void setHeight(String height) {
		unwrap().setHeight(height);
	}
	
	@JSGetter("minWidth")
	public String getMinWidth() {
		return unwrap().getMinWidth();
	}
	@JSSetter("minWidth")
	public void setMinWidth(String minWidth) {
		unwrap().setMinWidth(minWidth);
	}
	
	@JSGetter("minHeight")
	public String getMinHeight() {
		return unwrap().getMinHeight();
	}
	@JSSetter("minHeight")
	public void setMinHeight(String minHeight) {
		unwrap().setMinHeight(minHeight);
	}
	
	@JSGetter("maxWidth")
	public String getMaxWidth() {
		return unwrap().getMaxWidth();
	}
	@JSSetter("maxWidth")
	public void setMaxWidth(String maxWidth) {
		unwrap().setMaxWidth(maxWidth);
	}
	
	@JSGetter("maxHeight")
	public String getMaxHeight() {
		return unwrap().getMaxHeight();
	}
	@JSSetter("maxHeight")
	public void setMaxHeight(String maxHeight) {
		unwrap().setMaxHeight(maxHeight);
	}
	
	
	// Persistence
	
	@JSGetter("persist")
	public String getPersist() {
		return unwrap().getPersist();
	}
	@JSSetter("persist")
	public void setPersist(String persist) {
		unwrap().setPersist(persist);
	}
	
	
	// Position properties for
	// * popups = these are screen coordinates
	// * other elements - these are client coordinates relative to parent stack.
	
	@JSGetter("left")
	public String getLeft() {
		return unwrap().getLeft();
	}
	@JSSetter("left")
	public void setLeft(String left) {
		unwrap().setLeft(left);
	}
	
	@JSGetter("top")
	public String getTop() {
		return unwrap().getTop();
	}
	@JSSetter("top")
	public void setTop(String top) {
		unwrap().setTop(top);
	}
	
	
	// XUL Template Builder
	
	@JSGetter("datasources")
	public String getDatasources() {
		return unwrap().getDatasources();
	}
	@JSSetter("datasources")
	public void setDatasources(String datasources) {
		unwrap().setDatasources(datasources);
	}
	
	@JSGetter("ref")
	public String getRef() {
		return unwrap().getRef();
	}
	@JSSetter("ref")
	public void setRef(String ref) {
		unwrap().setRef(ref);
	}
	
	
	// Tooltip and status info
	
	@JSGetter("tooltipText")
	public String getTooltipText() {
		return unwrap().getTooltipText();
	}
	@JSSetter("tooltipText")
	public void setTooltipText(String tooltipText) {
		unwrap().setTooltipText(tooltipText);
	}
	
	@JSGetter("statusText")
	public String getStatusText() {
		return unwrap().getStatusText();
	}
	@JSSetter("statusText")
	public void setStatusText(String statusText) {
		unwrap().setStatusText(statusText);
	}
	
	@JSGetter("allowEvents")
	public boolean isAllowEvents() {
		return unwrap().isAllowEvents();
	}
	@JSSetter("allowEvents")
	public void setAllowEvents(boolean allowEvents) {
		unwrap().setAllowEvents(allowEvents);
	}
	
	
	@JSGetter("style")
	public Object getStyle() {
		return Context.javaToJS(unwrap().getStyle(), getParentScope());
	}
	
	@JSGetter("database")
	public Object getDatabase() {
		return Context.javaToJS(unwrap().getDatabase(), getParentScope());
	}

	@JSGetter("builder")
	public Object getBuilder() {
		return Context.javaToJS(unwrap().getBuilder(), getParentScope());
	}

	@JSGetter("resource")
	public Object getResource() {
		return Context.javaToJS(unwrap().getResource(), getParentScope());
	}

	// Controllers getControllers(); //readonly
	// BoxObject getBoxObject(); //readonly
	

	@JSFunction("focus")
	public void focus() {
		unwrap().focus();
	}

	@JSFunction("blur")
	public void blur() {
		unwrap().blur();
	}

	@JSFunction("click")
	public void click() {
		unwrap().click();
	}

	@JSFunction("doCommand")
	public void doCommand() {
		unwrap().doCommand();
	}
	
	@JSFunction("getElementsByAttribute")
	public Object getElementsByAttribute(String name, String value) {
		return Context.javaToJS(unwrap().getElementsByAttribute(name, value), getParentScope());
	}



	///// Event Attributes: /////

	protected Function onclickFunction;
	protected String lastOnclickAttributeValue;

	protected EventListener clickAttributeEventListener = new AttributeEventListener(this, "onclick");

	@JSGetter("onclick")
	public Function getOnclick() {
		String attrValue = unwrap().getAttribute("onclick");
		if(attrValue != null && (onclickFunction == null || !attrValue.equals(lastOnclickAttributeValue))) {
			onclickFunction = compileEventFunction(attrValue);
			lastOnclickAttributeValue = attrValue;
		}
		return onclickFunction;
	}
	@JSSetter("onclick")
	public void setOnclick(Function function) {
		if(function == null) {
			unwrap().removeEventListener("click", clickAttributeEventListener, false);
		} else {
			unwrap().addEventListener("click", clickAttributeEventListener, false);
		}
		onclickFunction = function;
		// The DOM attribute does not get updated per the Mozilla implementation.
	}

	/**
	 * Compile the given script body into an event listener function
	 */
	private Function compileEventFunction(String funcBody) {
		String script = "function(event){" + funcBody + "}";
		return Context.getCurrentContext().compileFunction(getParentScope(), script,
					unwrap().getOwnerDocument().getDocumentURI(), 0, null);
	}

	/**
	 * {@link EventListener} implementation that executes the given event property function, e.g. onclick.
	 */
	protected class AttributeEventListener implements EventListener {
		private Scriptable thisObj;
		private String eventProp;
		public AttributeEventListener(Scriptable thisObj, String eventProp) {
			this.thisObj = thisObj;
			this.eventProp = eventProp;
		}
		public void handleEvent(Event event) {
			Object wrappedEvent = Context.javaToJS(event, getParentScope());
			Object propVal = thisObj.get(eventProp, thisObj);
			if(propVal != null && propVal != NOT_FOUND && propVal instanceof Function) {
				((Function)propVal).call(Context.getCurrentContext(), thisObj.getParentScope(), thisObj, new Object[]{wrappedEvent});
			}
		}
	}

}
