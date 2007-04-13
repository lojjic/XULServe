package net.lojjic.xul.rdf;

import java.util.Iterator;

/**
 * A wrapper for manipulating RDF containers
 */
public interface RDFContainer {

	/**
	 * Return the underlying {@link RDFDataSource}
	 */
	RDFDataSource getDataSource();

	/**
	 * Return the wrapped {@link RDFResource}
	 */
	RDFResource getResource();

	/**
	 * Initialize the container wrapper to the specified resource
	 * using the specified datasource for context.
	 */
	void init(RDFDataSource dataSource, RDFResource container);

	/**
	 * Return the number of elements in the container. Note that this
	 * may not always be accurate due to aggregation.
	 */
	long getCount();

	/**
	 * Return an Iterator that can be used to enumerate the contents
	 * of the container in ascending order.
	 */
	Iterator<RDFNode> getElements();

	/**
	 * Append an element to the container, assigning it the next
	 * available ordinal.
	 */
	void appendElement(RDFNode element);

	/**
	 * Remove the first occurence of the specified element from the
	 * container. If renumber is 'true', then the underlying RDF graph
	 * will be 're-numbered' to account for the removal.
	 */
	void removeElement(RDFNode element, boolean renumber);

	/**
	 * Insert element at the specified index. If renumber is 'true', then
	 * the underlying RDF graph will be 're-numbered' to accomodate the new
	 * element.
	 */
	void insertElementAt(RDFNode element, long index, boolean renumber);

	/**
	 * Remove the element at the specified index. If renumber is 'true', then
	 * the underlying RDF graph will be 're-numbered' to account for the
	 * removal.
	 *
	 * @return the element that was removed.
	 */
	RDFNode removeElementAt(long index, boolean renumber);

	/**
	 * Determine the index of an element in the container.
	 *
	 * @return The index of the specified element in the container. If
	 *         the element is not contained in the container, this function
	 *         returns '-1'.
	 */
	long indexOf(RDFNode element);

}
