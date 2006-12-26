package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Text;

public class ScriptableText extends ScriptableCharacterData {
	
	public static String JS_CLASS_NAME = "Text";
	
	private Text delegateText;

	public ScriptableText(Scriptable scope, Text text) {
		super(scope, text);
		this.delegateText = text;
	}
	
	
	public String jsGet_wholeText() {
		return delegateText.getWholeText();
	}
	
	public boolean jsGet_isElementContentWhitespace() {
		return delegateText.isElementContentWhitespace();
	}
	
	public Object jsFunction_replaceWholeText(String content) {
		return wrap(delegateText.replaceWholeText(content));
	}
	
	public Object jsFunction_splitText(int offset) {
		return wrap(delegateText.splitText(offset));
	}

}
