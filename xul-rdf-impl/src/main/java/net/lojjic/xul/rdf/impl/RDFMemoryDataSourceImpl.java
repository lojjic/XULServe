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
	 * Default constructor; useful for configuration via bean setter injection.
	 * <p>
	 * Be sure to set the {@link net.lojjic.xul.rdf.RDFService}
	 * by calling {@link #setRdfService(net.lojjic.xul.rdf.RDFService)}.
	 */
	public RDFMemoryDataSourceImpl() {
		super();
		setRepository(createRepository());
	}

	/**
	 * Constructor passing in the {@link net.lojjic.xul.rdf.RDFService}
	 * @param rdfService
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
