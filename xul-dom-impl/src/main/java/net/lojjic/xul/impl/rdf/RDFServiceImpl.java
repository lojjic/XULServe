package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.*;

/**
 * Implementation of {@link RDFService} that uses a Sesame repository/graph
 * to manage the RDF statement data.
 */
public class RDFServiceImpl implements RDFService {

	public RDFResource getAnonymousResource() {
		return new RDFResourceImpl();
	}

	public RDFBlob getBlobLiteral(byte[] value, int length) {
		
		return new RDFBlobImpl(value);
	}

	public RDFDataSource getDataSource(String uri) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public RDFDataSource getDataSourceBlocking(String uri) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
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
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void registerResource(RDFResource resource, boolean replace) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void unregisterDataSource(RDFDataSource dataSource) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void unregisterResource(RDFResource resource) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
