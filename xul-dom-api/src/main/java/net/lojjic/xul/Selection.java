package net.lojjic.xul;

import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;

/**
 * Selection interface
 */
public interface Selection {

	/**
	 * The node representing one end of the selection.
	 */
	Node getAnchorNode();

	/**
	 * The offset within the (text) node where the selection begins.
	 */
	int getAnchorOffset();

	/**
	 * The node with keyboard focus.
	 */
	Node getFocusNode();

	/**
	 * The offset within the (text) node where focus starts.
	 */
	int getFocusOffset();

	/**
	 * Indicates if the selection is collapsed or not.
	 */
	boolean isCollapsed();

	/**
	 * Returns the number of ranges in the selection.
	 */
	int getRangeCount();


	/**
	 * Adds a range to the current selection.
	 */
	void addRange(Range range);

	/**
	 * Collapses the selection to a single point, at the specified offset in the given DOM node.
	 * When the selection is collapsed, and the content is focused and editable, the caret will blink there.
	 * @param parentNode The given dom node where the selection will be set
	 * @param offset Where in given dom node to place the selection (the offset into the given node)
	 */
	void collapse(Node parentNode, int offset);

	/**
	 * Collapses the whole selection to a single point at the end of the current selection
	 * (irrespective of direction). If content is focused and editable, the caret will blink there.
	 */
	void collapseToEnd();

	/**
	 * Collapses the whole selection to a single point at the start of the current selection
	 * (irrespective of direction). If content is focused and editable, the caret will blink there.
	 */
	void collapseToStart();

	/**
	 * The value of entirelyContained determines the detail of the search to determine if the
	 * selection contains the node. If entirelyContained is set to PR_TRUE, t or false if
	 * @param node The node where the selection will be extended to
	 * @param entirelyContained
	 * @return
	 */
	boolean containsNode(Node node, boolean entirelyContained);

	/**
	 * Deletes this selection from document the nodes belong to.
	 */
	void deleteFromDocument();

	/**
	 * Extends the selection by moving the focus to the specified node and offset, preserving the
	 * anchor postion. The new selection end result will always be from the anchor to the new
	 * focus, regardless of direction.
	 * @param parentNode  The node where the selection will be extended to
	 * @param offset  Where in node to place the offset in the new focused node
	 */
	void extend(Node parentNode, int offset);

	/**
	 * Returns the range at the specified index.
	 */
	Range getRangeAt(int index);

	/**
	 * Removes all ranges from the current selection.
	 */
	void removeAllRanges();

	/**
	 * Removes a range from the current selection.
	 */
	void removeRange(Range range);

	/**
	 * Adds all children of the specified node to the selection.
	 * @param parentNode the parent of the children to be added to the selection.
	 */
	void selectAllChildren(Node parentNode);

	/**
	 * Modifies the cursor Bidi level after a change in keyboard direction
	 * @param langRTL is PR_TRUE if the new language is right-to-left or PR_FALSE if the new language is left-to-right.
	 */
	void selectionLanguageChange(boolean langRTL);

	/**
	 * Returns the whole selection into a plain text string.
	 */
	String toString();

}
