package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFService;
import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFDataSource} that stores
 * its RDF graph in memory.  Uses a Sesame in-memory repository.
 */
public class RDFMemoryDataSourceImpl extends SesameDataSourceImpl {

	/**
	 * Construct the instance, passing in the in-memory Sesame repository.
	 */
	public RDFMemoryDataSourceImpl(RDFService rdfService) {
		super(rdfService, createRepository());
	}

	/**
	 * Create a new in-memory Sesame {@link org.openrdf.repository.Repository}
	 */
	private static Repository createRepository() {
		try {
			Repository repository = new SailRepository(new MemoryStore());
			repository.initialize();
			return repository;
		}
		catch (Exception e) {
			throw new RuntimeException("Exception creating local Sesame repository.", e);
		}
	}

}
