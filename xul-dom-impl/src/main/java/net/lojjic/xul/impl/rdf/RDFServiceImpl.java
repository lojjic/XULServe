package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.*;

import java.util.HashMap;

/**
 * Implementation of {@link RDFService} that uses a Sesame repository/graph
 * to manage the RDF statement data.
 */
public class RDFServiceImpl implements RDFService {

	private HashMap<String, RDFDataSource> registeredDataSources = new HashMap<String, RDFDataSource>();

	public RDFResource getAnonymousResource() {
		return new RDFResourceImpl();
	}

	public RDFBlob getBlobLiteral(byte[] value, int length) {
		
		return new RDFBlobImpl(value);
	}

	public RDFDataSource getDataSource(String uri) {
		// Use the blocking call, since we need to ensure the data is available
		// when the template gets built.
		return getDataSourceBlocking(uri);
	}

	public RDFDataSource getDataSourceBlocking(String uri) {
		RDFDataSource dataSource = registeredDataSources.get(uri);
		if(dataSource == null) {
			// TODO instantiate the correct datasource class
		}
		return dataSource;
	}

	public RDFDate getDateLiteral(long value) {
		return new RDFDateImpl(value);
	}

	public RDFInt getIntLiteral(int value) {
		return new RDFIntImpl(value);
	}

	public RDFLiteral getLiteral(String value) {
		return new RDFLiteralImpl(value);
	}

	public RDFResource getResource(String uri) {
		return new RDFResourceImpl(uri);
	}

	public RDFResource getUnicodeResource(String uri) {
		return getResource(uri);
	}

	public boolean isAnonymousResource(RDFResource resource) {
		// XXX currently depends on getValue() returning null for blank nodes
		return resource.getValue() == null;
	}

	public void registerDataSource(RDFDataSource dataSource, boolean replace) {
		if(replace || !registeredDataSources.containsKey(dataSource.getURI())) {
			registeredDataSources.put(dataSource.getURI(), dataSource);
		}
	}

	public void unregisterDataSource(RDFDataSource dataSource) {
		registeredDataSources.remove(dataSource.getURI());
	}

	public void registerResource(RDFResource resource, boolean replace) {
		// Unimplemented because in our architecture RDFResource instances
		// do not maintain references to any low-level RDF data source
		// resources. We therefore don't care if the same RDFResource
		// instance is always returned or not.
	}

	public void unregisterResource(RDFResource resource) {
		// Unimplemented because in our architecture RDFResource instances
		// do not maintain references to any low-level RDF data source
		// resources. We therefore don't care if the same RDFResource
		// instance is always returned or not.
	}
}
