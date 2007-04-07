package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.DOMImplementationList;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementationList}
 */
@JSClassName("DOMImplementationList")
public class ScriptableDOMImplementationList<T extends DOMImplementationList> extends ScriptableDOMObject<T> {

	public ScriptableDOMImplementationList() {
		super();
	}

	public ScriptableDOMImplementationList(Scriptable scope, T domImplementationList) {
		super(scope, domImplementationList);
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSFunction("item")
	public Object item(int index) {
		return Context.javaToJS(unwrap().item(index), getParentScope());
	}

	/**
	 * Items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
