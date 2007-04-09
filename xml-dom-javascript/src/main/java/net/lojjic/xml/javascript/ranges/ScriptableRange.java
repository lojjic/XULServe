package net.lojjic.xml.javascript.ranges;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;

/**
 * Scriptable wrapper for {@link org.w3c.dom.ranges.Range}
 */
@JSClassName("Range")
public class ScriptableRange<T extends Range> extends ScriptableDOMObject<T> {

	public ScriptableRange() {
	}

	public ScriptableRange(Scriptable scope, T range) {
		super(scope, range);
	}

	@JSStatic @JSGetter("START_TO_START")
	public int get_START_TO_START(ScriptableObject obj) {
		return Range.START_TO_START;
	}

	@JSStatic @JSGetter("START_TO_END")
	public int get_START_TO_END(ScriptableObject obj) {
		return Range.START_TO_END;
	}

	@JSStatic @JSGetter("END_TO_END")
	public int get_END_TO_END(ScriptableObject obj) {
		return Range.END_TO_END;
	}

	@JSStatic @JSGetter("END_TO_START")
	public int get_END_TO_START(ScriptableObject obj) {
		return Range.END_TO_START;
	}

	@JSFunction("cloneContents")
	public Object cloneContents() {
		return convertReturnValue(unwrap().cloneContents());
	}

	@JSFunction("cloneRange")
	public Object cloneRange() {
		return convertReturnValue(unwrap().cloneRange());
	}

	@JSFunction("collapse")
	public void collapse(boolean toStart) {
		unwrap().collapse(toStart);
	}

	@JSFunction("compareBoundaryPoints")
	public int compareBoundaryPoints(int how, Object sourceRange) {
		return unwrap().compareBoundaryPoints((short)how, convertArg(sourceRange, Range.class));
	}

	@JSFunction("deleteContents")
	public void deleteContents() {
		unwrap().deleteContents();
	}

	@JSFunction("detach")
	public void detach() {
		unwrap().detach();
	}

	@JSFunction("extractContents")
	public Object extractContents() {
		return convertReturnValue(unwrap().extractContents());
	}

	@JSGetter("collapsed")
	public boolean getCollapsed() {
		return unwrap().getCollapsed();
	}

	@JSGetter("commonAncestorContainer")
	public Object getCommonAncestorContainer() {
		return convertReturnValue(unwrap().getCommonAncestorContainer());
	}

	@JSGetter("endContainer")
	public Object getEndContainer() {
		return convertReturnValue(unwrap().getEndContainer());
	}

	@JSGetter("endOffset")
	public int getEndOffset() {
		return unwrap().getEndOffset();
	}

	@JSGetter("startContainer")
	public Object getStartContainer() {
		return convertReturnValue(unwrap().getStartContainer());
	}

	@JSGetter("startOffset")
	public int getStartOffset() {
		return unwrap().getStartOffset();
	}

	@JSFunction("insertNode")
	public void insertNode(Object newNode) {
		unwrap().insertNode(convertArg(newNode, Node.class));
	}

	@JSFunction("selectNode")
	public void selectNode(Object refNode) {
		unwrap().selectNode(convertArg(refNode, Node.class));
	}

	@JSFunction("selectNodeContents")
	public void selectNodeContents(Object refNode) {
		unwrap().selectNodeContents(convertArg(refNode, Node.class));
	}

	@JSFunction("setEnd")
	public void setEnd(Object refNode, int offset) {
		unwrap().setEnd(convertArg(refNode, Node.class), offset);
	}

	@JSFunction("setEndAfter")
	public void setEndAfter(Object refNode) {
		unwrap().setEndAfter(convertArg(refNode, Node.class));
	}

	@JSFunction("setEndBefore")
	public void setEndBefore(Object refNode) {
		unwrap().setEndBefore(convertArg(refNode, Node.class));
	}

	@JSFunction("setStart")
	public void setStart(Object refNode, int offset) {
		unwrap().setStart(convertArg(refNode, Node.class), offset);
	}

	@JSFunction("setStartAfter")
	public void setStartAfter(Object refNode) {
		unwrap().setStartAfter(convertArg(refNode, Node.class));
	}

	@JSFunction("setStartBefore")
	public void setStartBefore(Object refNode) {
		unwrap().setStartBefore(convertArg(refNode, Node.class));
	}

	@JSFunction("surroundContents")
	public void surroundContents(Object newParent) {
		unwrap().surroundContents(convertArg(newParent, Node.class));
	}

	@JSFunction("toString")
	public String toString() {
		return unwrap().toString();
	}
}
