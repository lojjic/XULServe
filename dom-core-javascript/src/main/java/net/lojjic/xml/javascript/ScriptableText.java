package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;
import org.w3c.dom.Text;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Text}
 */
@JSClassName("Text")
public class ScriptableText<T extends Text> extends ScriptableCharacterData<T> {

	public ScriptableText() {
		super();
	}

	public ScriptableText(Scriptable scope, T text) {
		super(scope, text);
	}
	
	@JSGetter("wholeText")
	public String getWholeText() {
		return unwrap().getWholeText();
	}

	@JSGetter("isElementContentWhitespace")
	public boolean isElementContentWhitespace() {
		return unwrap().isElementContentWhitespace();
	}

	@JSFunction("replaceWholeText")
	public Object replaceWholeText(String content) {
		return Context.javaToJS(unwrap().replaceWholeText(content), getParentScope());
	}
	
	@JSFunction("splitText")
	public Object splitText(int offset) {
		return Context.javaToJS(unwrap().splitText(offset), getParentScope());
	}

}
