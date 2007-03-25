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
public class ScriptableXULElement extends ScriptableElement {

	protected XULElement delegateXULElement;

	public ScriptableXULElement() {
		super();
	}

	public ScriptableXULElement(Scriptable scope, XULElement element) {
		super(scope, element);
		this.delegateXULElement = element;
	}
	
	@JSGetter("id")
	public String getId() {
		return delegateXULElement.getId();
	}

	@JSSetter("id")
	public void setId(String id) {
		delegateXULElement.setId(id);
	}

	@JSGetter("className")
	public String getCSSClassName() {
		return delegateXULElement.getClassName();
	}

	@JSSetter("className")
	public void setCSSClassName(String className) {
		delegateXULElement.setClassName(className);
	}
	
	
	// Layout properties
	
	@JSGetter("align")
	public String getAlign() {
		return delegateXULElement.getAlign();
	}
	@JSSetter("align")
	public void setAlign(String align) {
		delegateXULElement.setAlign(align);
	}
	
	@JSGetter("dir")
	public String getDir() {
		return delegateXULElement.getDir();
	}
	@JSSetter("dir")
	public void setDir(String dir) {
		delegateXULElement.setDir(dir);
	}
	
	@JSGetter("flex")
	public String getFlex() {
		return delegateXULElement.getFlex();
	}
	@JSSetter("flex")
	public void setFlex(String flex) {
		delegateXULElement.setFlex(flex);
	}
	
	@JSGetter("flexGroup")
	public String getFlexGroup() {
		return delegateXULElement.getFlexGroup();
	}
	@JSSetter("flexGroup")
	public void setFlexGroup(String flexGroup) {
		delegateXULElement.setFlexGroup(flexGroup);
	}
	
	@JSGetter("ordinal")
	public String getOrdinal() {
		return delegateXULElement.getOrdinal();
	}
	@JSSetter("ordinal")
	public void setOrdinal(String ordinal) {
		delegateXULElement.setOrdinal(ordinal);
	}
	
	@JSGetter("orient")
	public String getOrient() {
		return delegateXULElement.getOrient();
	}
	@JSSetter("orient")
	public void setOrient(String orient) {
		delegateXULElement.setOrient(orient);
	}
	
	@JSGetter("pack")
	public String getPack() {
		return delegateXULElement.getPack();
	}
	@JSSetter("pack")
	public void setPack(String pack) {
		delegateXULElement.setPack(pack);
	}
	
	
	// Properties for hiding element
	
	@JSGetter("hidden")
	public boolean isHidden() {
		return delegateXULElement.isHidden();
	}
	@JSSetter("hidden")
	public void setHidden(boolean hidden) {
		delegateXULElement.setHidden(hidden);
	}
	
	@JSGetter("collapsed")
	public boolean isCollapsed() {
		return delegateXULElement.isCollapsed();
	}
	@JSSetter("collapsed")
	public void setCollapsed(boolean collapsed) {
		delegateXULElement.setCollapsed(collapsed);
	}
	
	
	// Property for hooking up to broadcasters
	
	@JSGetter("observes")
	public String getObserves() {
		return delegateXULElement.getObserves();
	}
	@JSSetter("observes")
	public void setObserves(String observes) {
		delegateXULElement.setObserves(observes);
	}
	
	
	// Properties for hooking up to popups
	
	@JSGetter("width")
	public String getWidth() {
		return delegateXULElement.getWidth();
	}
	@JSSetter("width")
	public void setWidth(String width) {
		delegateXULElement.setWidth(width);
	}
	
	@JSGetter("height")
	public String getHeight() {
		return delegateXULElement.getHeight();
	}
	@JSSetter("height")
	public void setHeight(String height) {
		delegateXULElement.setHeight(height);
	}
	
	@JSGetter("minWidth")
	public String getMinWidth() {
		return delegateXULElement.getMinWidth();
	}
	@JSSetter("minWidth")
	public void setMinWidth(String minWidth) {
		delegateXULElement.setMinWidth(minWidth);
	}
	
	@JSGetter("minHeight")
	public String getMinHeight() {
		return delegateXULElement.getMinHeight();
	}
	@JSSetter("minHeight")
	public void setMinHeight(String minHeight) {
		delegateXULElement.setMinHeight(minHeight);
	}
	
	@JSGetter("maxWidth")
	public String getMaxWidth() {
		return delegateXULElement.getMaxWidth();
	}
	@JSSetter("maxWidth")
	public void setMaxWidth(String maxWidth) {
		delegateXULElement.setMaxWidth(maxWidth);
	}
	
	@JSGetter("maxHeight")
	public String getMaxHeight() {
		return delegateXULElement.getMaxHeight();
	}
	@JSSetter("maxHeight")
	public void setMaxHeight(String maxHeight) {
		delegateXULElement.setMaxHeight(maxHeight);
	}
	
	
	// Persistence
	
	@JSGetter("persist")
	public String getPersist() {
		return delegateXULElement.getPersist();
	}
	@JSSetter("persist")
	public void setPersist(String persist) {
		delegateXULElement.setPersist(persist);
	}
	
	
	// Position properties for
	// * popups = these are screen coordinates
	// * other elements - these are client coordinates relative to parent stack.
	
	@JSGetter("left")
	public String getLeft() {
		return delegateXULElement.getLeft();
	}
	@JSSetter("left")
	public void setLeft(String left) {
		delegateXULElement.setLeft(left);
	}
	
	@JSGetter("top")
	public String getTop() {
		return delegateXULElement.getTop();
	}
	@JSSetter("top")
	public void setTop(String top) {
		delegateXULElement.setTop(top);
	}
	
	
	// XUL Template Builder
	
	@JSGetter("datasources")
	public String getDatasources() {
		return delegateXULElement.getDatasources();
	}
	@JSSetter("datasources")
	public void setDatasources(String datasources) {
		delegateXULElement.setDatasources(datasources);
	}
	
	@JSGetter("ref")
	public String getRef() {
		return delegateXULElement.getRef();
	}
	@JSSetter("ref")
	public void setRef(String ref) {
		delegateXULElement.setRef(ref);
	}
	
	
	// Tooltip and status info
	
	@JSGetter("tooltipText")
	public String getTooltipText() {
		return delegateXULElement.getTooltipText();
	}
	@JSSetter("tooltipText")
	public void setTooltipText(String tooltipText) {
		delegateXULElement.setTooltipText(tooltipText);
	}
	
	@JSGetter("statusText")
	public String getStatusText() {
		return delegateXULElement.getStatusText();
	}
	@JSSetter("statusText")
	public void setStatusText(String statusText) {
		delegateXULElement.setStatusText(statusText);
	}
	
	@JSGetter("allowEvents")
	public boolean isAllowEvents() {
		return delegateXULElement.isAllowEvents();
	}
	@JSSetter("allowEvents")
	public void setAllowEvents(boolean allowEvents) {
		delegateXULElement.setAllowEvents(allowEvents);
	}
	
	
	@JSGetter("style")
	public Object getStyle() {
		return Context.javaToJS(delegateXULElement.getStyle(), getParentScope());
	}
	
	@JSGetter("database")
	public Object getDatabase() {
		return Context.javaToJS(delegateXULElement.getDatabase(), getParentScope());
	}

	@JSGetter("builder")
	public Object getBuilder() {
		return Context.javaToJS(delegateXULElement.getBuilder(), getParentScope());
	}

	@JSGetter("resource")
	public Object getResource() {
		return Context.javaToJS(delegateXULElement.getResource(), getParentScope());
	}

	// Controllers getControllers(); //readonly
	// BoxObject getBoxObject(); //readonly
	

	@JSFunction("focus")
	public void focus() {
		delegateXULElement.focus();
	}

	@JSFunction("blur")
	public void blur() {
		delegateXULElement.blur();
	}

	@JSFunction("click")
	public void click() {
		delegateXULElement.click();
	}

	@JSFunction("doCommand")
	public void doCommand() {
		delegateXULElement.doCommand();
	}
	
	@JSFunction("getElementsByAttribute")
	public Object getElementsByAttribute(String name, String value) {
		return Context.javaToJS(delegateXULElement.getElementsByAttribute(name, value), getParentScope());
	}



	///// Event Attributes: /////

	protected Function onclickFunction;
	protected String lastOnclickAttributeValue;

	protected EventListener clickAttributeEventListener = new AttributeEventListener(this, "onclick");

	@JSGetter("onclick")
	public Function getOnclick() {
		String attrValue = delegateXULElement.getAttribute("onclick");
		if(attrValue != null && (onclickFunction == null || !attrValue.equals(lastOnclickAttributeValue))) {
			onclickFunction = compileEventFunction(attrValue);
			lastOnclickAttributeValue = attrValue;
		}
		return onclickFunction;
	}
	@JSSetter("onclick")
	public void setOnclick(Function function) {
		if(function == null) {
			delegateXULElement.removeEventListener("click", clickAttributeEventListener, false);
		} else {
			delegateXULElement.addEventListener("click", clickAttributeEventListener, false);
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
					delegateXULElement.getOwnerDocument().getDocumentURI(), 0, null);
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
