package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFContainerUtils}
 */
public class RDFContainerUtilsImpl implements RDFContainerUtils {

	private RDFService rdfService;

	/**
	 * Constructor
	 * @param rdfService
	 */
	public RDFContainerUtilsImpl(RDFService rdfService) {
		this.rdfService = rdfService;
	}

	/**
	 * Returns 'true' if the property is an RDF ordinal property.
	 */
	public boolean isOrdinalProperty(RDFResource property) {
		String prefix = RDFConstants.RDF_NAMESPACE + "_";
		String uri = property.getValue();
		if(!uri.startsWith(prefix)) {
			return false;
		}
		long index;
		try {
			index = Long.parseLong(uri.substring(prefix.length()));
		}
		catch(NumberFormatException e) {
			return false;
		}
		return index > 0;
	}

	/**
	 * Convert the specified index to an ordinal property.
	 */
	public RDFResource indexToOrdinalResource(long index) {
		if(index < 1) {
			throw new IllegalArgumentException("Ordinal index must be 1 or greater");
		}
		return rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_" + index);
	}

	/**
	 * Convert the specified ordinal property into an index
	 */
	public long ordinalResourceToIndex(RDFResource ordinal) {
		if(!isOrdinalProperty(ordinal)) {
			throw new IllegalArgumentException("Supplied RDFResource is not an ordinal property");
		}
		String prefix = RDFConstants.RDF_NAMESPACE + "_";
		return Long.parseLong(ordinal.getValue().substring(prefix.length()));
	}

	/**
	 * Return 'true' if the specified resource is a container
	 */
	public boolean isContainer(RDFDataSource dataSource, RDFResource resource) {
		return isSeq(dataSource, resource) || isBag(dataSource, resource) || isAlt(dataSource, resource);
	}

	/**
	 * Return 'true' if the specified resource is a container and it is empty
	 */
	public boolean isEmpty(RDFDataSource dataSource, RDFResource resource) {
		RDFResource arc = rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_1");
		return !dataSource.hasArcOut(resource, arc);
	}

	/**
	 * Return 'true' if the specified resource is a bag
	 */
	public boolean isBag(RDFDataSource dataSource, RDFResource resource) {
		return dataSource.hasAssertion(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
				rdfService.getResource(RDFConstants.BAG_URI), true);
	}

	/**
	 * Return 'true' if the specified resource is a sequence
	 */
	public boolean isSeq(RDFDataSource dataSource, RDFResource resource) {
		return dataSource.hasAssertion(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
				rdfService.getResource(RDFConstants.SEQ_URI), true);
	}

	/**
	 * Return 'true' if the specified resource is an alternation
	 */
	public boolean isAlt(RDFDataSource dataSource, RDFResource resource) {
		return dataSource.hasAssertion(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
				rdfService.getResource(RDFConstants.ALT_URI), true);
	}

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty bag in the specified data source.
	 */
	public RDFContainer makeBag(RDFDataSource dataSource, RDFResource resource) {
		if(!isBag(dataSource, resource)) {
			dataSource.doAssert(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
					rdfService.getResource(RDFConstants.BAG_URI), true);
		}
		return new RDFContainerImpl(rdfService, dataSource, resource);
	}

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty sequence in the specified data source.
	 */
	public RDFContainer makeSeq(RDFDataSource dataSource, RDFResource resource) {
		if(!isSeq(dataSource, resource)) {
			dataSource.doAssert(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
					rdfService.getResource(RDFConstants.SEQ_URI), true);
		}
		return new RDFContainerImpl(rdfService, dataSource, resource);
	}

	/**
	 * Decorates the specified resource appropriately to make it
	 * usable as an empty alternation in the specified data source.
	 */
	public RDFContainer makeAlt(RDFDataSource dataSource, RDFResource resource) {
		if(!isAlt(dataSource, resource)) {
			dataSource.doAssert(resource, rdfService.getResource(RDFConstants.RDF_TYPE),
					rdfService.getResource(RDFConstants.ALT_URI), true);
		}
		return new RDFContainerImpl(rdfService, dataSource, resource);
	}

	/**
	 * Retrieve the index of element in the container. Returns -1 if
	 * the element is not in the container.
	 */
	public long indexOf(RDFDataSource dataSource, RDFResource resource, RDFNode element) {
		RDFContainer container = new RDFContainerImpl(rdfService, dataSource, resource);
		return container.indexOf(element);
	}
}
