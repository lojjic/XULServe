package net.lojjic.xml.javascript.stylesheets;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.stylesheets.StyleSheetList;

/**
 * Scriptable wrapper for {@link org.w3c.dom.stylesheets.StyleSheetList}
 */
@JSClassName("StyleSheetList")
public class ScriptableStyleSheetList<T extends StyleSheetList> extends ScriptableDOMObject<T> {

	public ScriptableStyleSheetList() {
	}

	public ScriptableStyleSheetList(Scriptable scope, T styleSheetList) {
		super(scope, styleSheetList);
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
	 * Indexed properties are a shortcut to {@link #item(int)}.
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
