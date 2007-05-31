package net.lojjic.xul.templates;

import org.w3c.dom.Node;

/**
 * A service used to sort the contents of a XUL widget.
 */
public interface XULSortService {

	/**
	 * Sort the contents of the widget containing <code>aNode</code>
	 * using <code>aSortKey</code> as the comparison key, and
	 * <code>aSortDirection</code> as the direction.
	 *
	 * @param aNode A node in the XUL widget whose children are to be sorted.
	 * @param aSortKey The value to be used as the comparison key.
	 * @param aSortDirection May be either <b>natural</b> to return
	 * the contents to their natural (unsorted) order,
	 * <b>ascending</b> to sort the contents in ascending order, or
	 * <b>descending</b> to sort the contents in descending order.
	 */
	void sort(Node aNode,
	          String aSortKey,
	          String aSortDirection);

}
