package net.lojjic.xml.javascript;

import org.w3c.dom.Notation;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Notation}
 */
@JSClassName("Notation")
public class ScriptableNotation extends ScriptableNode {

	private Notation delegateNotation;

	public ScriptableNotation(Scriptable scope, Notation delegateNotation) {
		super(scope, delegateNotation);
		this.delegateNotation = delegateNotation;
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return delegateNotation.getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return delegateNotation.getSystemId();
	}
}
