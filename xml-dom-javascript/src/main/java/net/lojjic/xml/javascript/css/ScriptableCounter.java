package net.lojjic.xml.javascript.css;

import org.w3c.dom.css.Counter;
import org.mozilla.javascript.Scriptable;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.Counter}
 */
@JSClassName("Counter")
public class ScriptableCounter<T extends Counter> extends ScriptableDOMObject<T> {

	public ScriptableCounter() {
	}

	public ScriptableCounter(Scriptable scope, T wrappedObject) {
		super(scope, wrappedObject);
	}

	@JSGetter("identifier")
	public String getIdentifier() {
		return unwrap().getIdentifier();
	}

	@JSGetter("listStyle")
	public String getListStyle() {
		return unwrap().getListStyle();
	}

	@JSGetter("separator")
	public String getSeparator() {
		return unwrap().getSeparator();
	}
}
