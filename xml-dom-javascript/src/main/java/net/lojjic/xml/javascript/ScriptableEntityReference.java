package net.lojjic.xml.javascript;

import org.w3c.dom.EntityReference;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.EntityReference}
 */
@JSClassName("EntityReference")
public class ScriptableEntityReference<T extends EntityReference> extends ScriptableNode<T> {

	public ScriptableEntityReference() {
		super();
	}

	public ScriptableEntityReference(Scriptable scope, T entityReference) {
		super(scope, entityReference);
	}
}
