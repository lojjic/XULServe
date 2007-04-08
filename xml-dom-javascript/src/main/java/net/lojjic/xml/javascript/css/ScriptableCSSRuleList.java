package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.CSSRuleList;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSRuleList}
 */
@JSClassName("CSSRuleList")
public class ScriptableCSSRuleList<T extends CSSRuleList> extends ScriptableDOMObject<T> {

	public ScriptableCSSRuleList() {
	}

	public ScriptableCSSRuleList(Scriptable scope, T wrappedObject) {
		super(scope, wrappedObject);
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSFunction("item")
	public Object item(int index) {
		return convertReturnValue(unwrap().item(index));
	}

	/**
	 * Indexed properties shortcut to {@link #item(int)}.
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
