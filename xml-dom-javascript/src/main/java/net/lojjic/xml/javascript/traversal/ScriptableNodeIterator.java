package net.lojjic.xml.javascript.traversal;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.traversal.NodeIterator;

/**
 * Scriptable wrapper for {@link org.w3c.dom.traversal.NodeIterator}
 */
@JSClassName("NodeIterator")
public class ScriptableNodeIterator<T extends NodeIterator> extends ScriptableDOMObject<T> {

	public ScriptableNodeIterator() {
	}

	public ScriptableNodeIterator(Scriptable scope, T nodeIterator) {
		super(scope, nodeIterator);
	}

	@JSFunction("detach")
	public void detach() {
		unwrap().detach();
	}

	@JSGetter("expandEntityReferences")
	public boolean getExpandEntityReferences() {
		return unwrap().getExpandEntityReferences();
	}

	@JSGetter("filter")
	public Object getFilter() {
		return convertReturnValue(unwrap().getFilter());
	}

	@JSGetter("root")
	public Object getRoot() {
		return convertReturnValue(unwrap().getRoot());
	}

	@JSGetter("whatToShow")
	public int getWhatToShow() {
		return unwrap().getWhatToShow();
	}

	@JSFunction("nextNode")
	public Object nextNode() {
		return convertReturnValue(unwrap().nextNode());
	}

	@JSFunction("previousNode")
	public Object previousNode() {
		return convertReturnValue(unwrap().previousNode());
	}
}
