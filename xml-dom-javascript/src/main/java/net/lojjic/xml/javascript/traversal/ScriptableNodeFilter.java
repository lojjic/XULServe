package net.lojjic.xml.javascript.traversal;

import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.Node;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.traversal.NodeFilter}
 */
@JSClassName("NodeFilter")
public class ScriptableNodeFilter<T extends NodeFilter> extends ScriptableDOMObject<T> {

	public ScriptableNodeFilter() {
	}

	public ScriptableNodeFilter(Scriptable scope, T nodeFilter) {
		super(scope, nodeFilter);
	}

	@JSStatic @JSGetter("FILTER_ACCEPT")
	public int get_FILTER_ACCEPT(ScriptableObject obj) {
		return NodeFilter.FILTER_ACCEPT;
	}

	@JSStatic @JSGetter("FILTER_REJECT")
	public int get_FILTER_REJECT(ScriptableObject obj) {
		return NodeFilter.FILTER_REJECT;
	}

	@JSStatic @JSGetter("FILTER_SKIP")
	public int get_FILTER_SKIP(ScriptableObject obj) {
		return NodeFilter.FILTER_SKIP;
	}

	@JSStatic @JSGetter("SHOW_ALL")
	public int get_SHOW_ALL(ScriptableObject obj) {
		return NodeFilter.SHOW_ALL;
	}

	@JSStatic @JSGetter("SHOW_ELEMENT")
	public int get_SHOW_ELEMENT(ScriptableObject obj) {
		return NodeFilter.SHOW_ELEMENT;
	}

	@JSStatic @JSGetter("SHOW_ATTRIBUTE")
	public int get_SHOW_ATTRIBUTE(ScriptableObject obj) {
		return NodeFilter.SHOW_ATTRIBUTE;
	}

	@JSStatic @JSGetter("SHOW_TEXT")
	public int get_SHOW_TEXT(ScriptableObject obj) {
		return NodeFilter.SHOW_TEXT;
	}

	@JSStatic @JSGetter("SHOW_CDATA_SECTION")
	public int get_SHOW_CDATA_SECTION(ScriptableObject obj) {
		return NodeFilter.SHOW_CDATA_SECTION;
	}

	@JSStatic @JSGetter("SHOW_ENTITY_REFERENCE")
	public int get_SHOW_ENTITY_REFERENCE(ScriptableObject obj) {
		return NodeFilter.SHOW_ENTITY_REFERENCE;
	}

	@JSStatic @JSGetter("SHOW_ENTITY")
	public int get_SHOW_ENTITY(ScriptableObject obj) {
		return NodeFilter.SHOW_ENTITY;
	}

	@JSStatic @JSGetter("SHOW_PROCESSING_INSTRUCTION")
	public int get_SHOW_PROCESSING_INSTRUCTION(ScriptableObject obj) {
		return NodeFilter.SHOW_PROCESSING_INSTRUCTION;
	}

	@JSStatic @JSGetter("SHOW_COMMENT")
	public int get_SHOW_COMMENT(ScriptableObject obj) {
		return NodeFilter.SHOW_COMMENT;
	}

	@JSStatic @JSGetter("SHOW_DOCUMENT")
	public int get_SHOW_DOCUMENT(ScriptableObject obj) {
		return NodeFilter.SHOW_DOCUMENT;
	}

	@JSStatic @JSGetter("SHOW_DOCUMENT_TYPE")
	public int get_SHOW_DOCUMENT_TYPE(ScriptableObject obj) {
		return NodeFilter.SHOW_DOCUMENT_TYPE;
	}

	@JSStatic @JSGetter("SHOW_DOCUMENT_FRAGMENT")
	public int get_SHOW_DOCUMENT_FRAGMENT(ScriptableObject obj) {
		return NodeFilter.SHOW_DOCUMENT_FRAGMENT;
	}

	@JSStatic @JSGetter("SHOW_NOTATION")
	public int get_SHOW_NOTATION(ScriptableObject obj) {
		return NodeFilter.SHOW_NOTATION;
	}

	@JSFunction("acceptNode")
	public int acceptNode(Object node) {
		return unwrap().acceptNode(convertArg(node, Node.class));
	}
}
