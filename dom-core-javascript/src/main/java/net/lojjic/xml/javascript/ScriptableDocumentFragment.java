package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DocumentFragment;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DocumentFragment}
 */
@JSClassName("DocumentFragment")
public class ScriptableDocumentFragment<T extends DocumentFragment> extends ScriptableNode<T> {

	public ScriptableDocumentFragment() {
		super();
	}

	public ScriptableDocumentFragment(Scriptable scope, T delegateDocumentFragment) {
		super(scope, delegateDocumentFragment);
	}

}
