package net.lojjic.xml.javascript;

import org.w3c.dom.DOMStringList;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMStringList}
 */
@JSClassName("DOMStringList")
public class ScriptableDOMStringList<T extends DOMStringList> extends ScriptableDOMObject<T> {

	public ScriptableDOMStringList() {
		super();
	}

	public ScriptableDOMStringList(Scriptable scope, T domStringList) {
		super(scope, domStringList);
	}

	@JSFunction("contains")
	public boolean contains(String str) {
		return unwrap().contains(str);
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSFunction("item")
	public String item(int index) {
		return unwrap().item(index);
	}

	/**
	 * Contained items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}

}
