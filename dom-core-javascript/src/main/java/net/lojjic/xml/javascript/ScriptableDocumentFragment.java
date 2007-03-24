package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DocumentFragment;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DocumentFragment}
 */
@JSClassName("DocumentFragment")
public class ScriptableDocumentFragment extends ScriptableNode {

	private DocumentFragment delegateDocumentFragment;

	public ScriptableDocumentFragment(Scriptable scope, DocumentFragment delegateDocumentFragment) {
		super(scope, delegateDocumentFragment);
		this.delegateDocumentFragment = delegateDocumentFragment;
	}

}
