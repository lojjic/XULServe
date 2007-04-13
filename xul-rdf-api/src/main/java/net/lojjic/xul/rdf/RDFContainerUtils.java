package net.lojjic.xul.rdf;

/**
 * Utilities for manipulating RDF containers (Seq, Bag, Alt)
 */
public interface RDFContainerUtils {

	/**
	 * Returns 'true' if the property is an RDF ordinal property.
	 */
	boolean isOrdinalProperty(RDFResource property);

	/**
	 * Convert the specified index to an ordinal property.
	 */
	RDFResource indexToOrdinalResource(long index);

	/**
	 * Convert the specified ordinal property into an index
	 */
	long ordinalResourceToIndex(RDFResource ordinal);

	/**
	 * Return 'true' if the specified resource is a container
	 */
	boolean isContainer(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Return 'true' if the specified resource is a container and it is empty
	 */
	boolean isEmpty(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Return 'true' if the specified resource is a bag
	 */
	boolean isBag(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Return 'true' if the specified resource is a sequence
	 */
	boolean isSeq(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Return 'true' if the specified resource is an alternation
	 */
	boolean isAlt(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty bag in the specified data source.
	 */
	RDFContainer makeBag(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty sequence in the specified data source.
	 */
	RDFContainer makeSeq(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty alternation in the specified data source.
	 */
	RDFContainer makeAlt(RDFDataSource dataSource, RDFResource resource);

	/**
	 * Retrieve the index of element in the container. Returns -1 if
	 * the element is not in the container.
	 */
	long indexOf(RDFDataSource dataSource, RDFResource container, RDFNode element);

}
