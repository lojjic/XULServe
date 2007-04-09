package net.lojjic.xml.javascript.traversal;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/**
 * Scriptable wrapper for {@link org.w3c.dom.traversal.TreeWalker}
 */
@JSClassName("TreeWalker")
public class ScriptableTreeWalker<T extends TreeWalker> extends ScriptableDOMObject<T> {

	public ScriptableTreeWalker() {
	}

	public ScriptableTreeWalker(Scriptable scope, T treeWalker) {
		super(scope, treeWalker);
	}

	@JSFunction("firstChild")
	public Node firstChild() {
		return unwrap().firstChild();
	}

	@JSGetter("currentNode")
	public Object getCurrentNode() {
		return convertReturnValue(unwrap().getCurrentNode());
	}

	@JSGetter("expandEntityReferences")
	public boolean getExpandEntityReferences() {
		return unwrap().getExpandEntityReferences();
	}

	@JSGetter("filter")
	public NodeFilter getFilter() {
		return unwrap().getFilter();
	}

	@JSGetter("root")
	public Node getRoot() {
		return unwrap().getRoot();
	}

	@JSGetter("whatToShow")
	public int getWhatToShow() {
		return unwrap().getWhatToShow();
	}

	@JSFunction("lastChild")
	public Node lastChild() {
		return unwrap().lastChild();
	}

	@JSFunction("nextNode")
	public Node nextNode() {
		return unwrap().nextNode();
	}

	@JSFunction("nextSibling")
	public Node nextSibling() {
		return unwrap().nextSibling();
	}

	@JSFunction("parentNode")
	public Node parentNode() {
		return unwrap().parentNode();
	}

	@JSFunction("previousNode")
	public Node previousNode() {
		return unwrap().previousNode();
	}

	@JSFunction("previousSibling")
	public Node previousSibling() {
		return unwrap().previousSibling();
	}

	@JSSetter("currentNode")
	public void setCurrentNode(Node currentNode) {
		unwrap().setCurrentNode(currentNode);
	}
}
