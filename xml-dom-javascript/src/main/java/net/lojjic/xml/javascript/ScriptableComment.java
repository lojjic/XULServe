package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Comment;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Comment}
 */
@JSClassName("Comment")
public class ScriptableComment<T extends Comment> extends ScriptableCharacterData<T> {

	public ScriptableComment() {
		super();
	}

	public ScriptableComment(Scriptable scope, T delegateComment) {
		super(scope, delegateComment);
	}

}
