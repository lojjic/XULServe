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
public class ScriptableDOMStringList extends ScriptableDOMObject {

	private DOMStringList delegateDOMStringList;

	public ScriptableDOMStringList() {
		super();
	}

	public ScriptableDOMStringList(Scriptable scope, DOMStringList delegateDOMStringList) {
		super(scope, delegateDOMStringList);
		this.delegateDOMStringList = delegateDOMStringList;
	}

	@JSFunction("contains")
	public boolean contains(String str) {
		return delegateDOMStringList.contains(str);
	}

	@JSGetter("length")
	public int getLength() {
		return delegateDOMStringList.getLength();
	}

	@JSFunction("item")
	public String item(int index) {
		return delegateDOMStringList.item(index);
	}

	/**
	 * Contained items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}

}
