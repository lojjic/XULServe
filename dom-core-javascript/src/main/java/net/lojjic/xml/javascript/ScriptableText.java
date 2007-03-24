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
public class ScriptableText extends ScriptableCharacterData {

	private Text delegateText;

	public ScriptableText(Scriptable scope, Text text) {
		super(scope, text);
		this.delegateText = text;
	}
	
	@JSGetter("wholeText")
	public String getWholeText() {
		return delegateText.getWholeText();
	}

	@JSGetter("isElementContentWhitespace")
	public boolean isElementContentWhitespace() {
		return delegateText.isElementContentWhitespace();
	}

	@JSFunction("replaceWholeText")
	public Object replaceWholeText(String content) {
		return Context.javaToJS(delegateText.replaceWholeText(content), getParentScope());
	}
	
	@JSFunction("splitText")
	public Object splitText(int offset) {
		return Context.javaToJS(delegateText.splitText(offset), getParentScope());
	}

}
