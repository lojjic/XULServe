package net.lojjic.xml.javascript;

import org.w3c.dom.Notation;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Notation}
 */
@JSClassName("Notation")
public class ScriptableNotation<T extends Notation> extends ScriptableNode<T> {

	public ScriptableNotation() {
		super();
	}

	public ScriptableNotation(Scriptable scope, T notation) {
		super(scope, notation);
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return unwrap().getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return unwrap().getSystemId();
	}
}
