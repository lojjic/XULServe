package net.lojjic.xml.javascript;

import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementation;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMImplementationList}
 */
@JSClassName("DOMImplementationList")
public class ScriptableDOMImplementationList extends ScriptableDOMObject implements DOMImplementationList {

	private DOMImplementationList delegateDOMImplementationList;

	public ScriptableDOMImplementationList(Scriptable scope, DOMImplementationList delegateDOMImplementationList) {
		super(scope, delegateDOMImplementationList);
		this.delegateDOMImplementationList = delegateDOMImplementationList;
	}

	@JSGetter("length")
	public int getLength() {
		return delegateDOMImplementationList.getLength();
	}

	@JSFunction("item")
	public DOMImplementation item(int index) {
		return delegateDOMImplementationList.item(index);
	}

	/**
	 * Items are exposed as indexed properties
	 */
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
