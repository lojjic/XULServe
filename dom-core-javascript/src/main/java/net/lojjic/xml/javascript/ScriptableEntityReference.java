package net.lojjic.xml.javascript;

import org.w3c.dom.EntityReference;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;

/**
 * Scriptable wrapper for {@link org.w3c.dom.EntityReference}
 */
@JSClassName("EntityReference")
public class ScriptableEntityReference extends ScriptableNode {

	private EntityReference delegateEntityReference;

	public ScriptableEntityReference(Scriptable scope, EntityReference delegateEntityReference) {
		super(scope, delegateEntityReference);
		this.delegateEntityReference = delegateEntityReference;
	}
}
