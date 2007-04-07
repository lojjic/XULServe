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
public class ScriptableWindow<T extends Window> extends ScriptableDOMObject<T> {

	public ScriptableWindow() {
		super();
	}

	public ScriptableWindow(Scriptable scope, T window) {
		super(scope, window);
	}

	@JSGetter("name")
	public String getName() {
		return unwrap().getName();
	}

	@JSGetter("parent")
	public Window getParent() {
		return unwrap().getParent();
	}

	@JSGetter("scrollX")
	public long getScrollX() {
		return unwrap().getScrollX();
	}

	@JSGetter("scrollY")
	public long getScrollY() {
		return unwrap().getScrollY();
	}

	@JSGetter("textZoom")
	public float getTextZoom() {
		return unwrap().getTextZoom();
	}

	@JSGetter("top")
	public Object getTop() {
		return Context.javaToJS(unwrap().getTop(), getParentScope());
	}

	@JSFunction("scrollBy")
	public void scrollBy(long xScrollDif, long yScrollDif) {
		unwrap().scrollBy(xScrollDif, yScrollDif);
	}

	@JSFunction("scrollByLines")
	public void scrollByLines(long numLines) {
		unwrap().scrollByLines(numLines);
	}

	@JSFunction("scrollByPages")
	public void scrollByPages(long numPages) {
		unwrap().scrollByPages(numPages);
	}

	@JSFunction("scrollTo")
	public void scrollTo(long xScroll, long yScroll) {
		unwrap().scrollTo(xScroll, yScroll);
	}

	@JSFunction("setTextZoom")
	@JSGetter("textZoom")
	public void setTextZoom(float textZoom) {
		unwrap().setTextZoom(textZoom);
	}

	@JSFunction("sizeToContent")
	public void sizeToContent() {
		unwrap().sizeToContent();
	}

	@JSGetter("document")
	public Object getDocument() {
		return Context.javaToJS(unwrap().getDocument(), getParentScope());
	}
}
