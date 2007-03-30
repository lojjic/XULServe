package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.xul.Window;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.Window}
 */
@JSClassName("Window")
public class ScriptableWindow extends ScriptableDOMObject {

	protected Window delegateWindow;

	public ScriptableWindow() {
		super();
	}

	public ScriptableWindow(Scriptable scope, Window delegateWindow) {
		super(scope, delegateWindow);
		this.delegateWindow = delegateWindow;
	}

	@JSGetter("name")
	public String getName() {
		return delegateWindow.getName();
	}

	@JSGetter("parent")
	public Window getParent() {
		return delegateWindow.getParent();
	}

	@JSGetter("scrollX")
	public long getScrollX() {
		return delegateWindow.getScrollX();
	}

	@JSGetter("scrollY")
	public long getScrollY() {
		return delegateWindow.getScrollY();
	}

	@JSGetter("textZoom")
	public float getTextZoom() {
		return delegateWindow.getTextZoom();
	}

	@JSGetter("top")
	public Object getTop() {
		return Context.javaToJS(delegateWindow.getTop(), getParentScope());
	}

	@JSFunction("scrollBy")
	public void scrollBy(long xScrollDif, long yScrollDif) {
		delegateWindow.scrollBy(xScrollDif, yScrollDif);
	}

	@JSFunction("scrollByLines")
	public void scrollByLines(long numLines) {
		delegateWindow.scrollByLines(numLines);
	}

	@JSFunction("scrollByPages")
	public void scrollByPages(long numPages) {
		delegateWindow.scrollByPages(numPages);
	}

	@JSFunction("scrollTo")
	public void scrollTo(long xScroll, long yScroll) {
		delegateWindow.scrollTo(xScroll, yScroll);
	}

	@JSFunction("setTextZoom")
	@JSGetter("textZoom")
	public void setTextZoom(float textZoom) {
		delegateWindow.setTextZoom(textZoom);
	}

	@JSFunction("sizeToContent")
	public void sizeToContent() {
		delegateWindow.sizeToContent();
	}

	@JSGetter("document")
	public Object getDocument() {
		return Context.javaToJS(delegateWindow.getDocument(), getParentScope());
	}
}
