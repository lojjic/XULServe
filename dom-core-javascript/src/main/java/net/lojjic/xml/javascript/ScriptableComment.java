package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Comment;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Comment}
 */
@JSClassName("Comment")
public class ScriptableComment extends ScriptableCharacterData {

	private Comment delegateComment;

	public ScriptableComment(Scriptable scope, Comment delegateComment) {
		super(scope, delegateComment);
		this.delegateComment = delegateComment;
	}

}
