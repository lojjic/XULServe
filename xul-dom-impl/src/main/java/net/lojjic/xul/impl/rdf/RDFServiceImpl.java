package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.*;
import org.openrdf.sesame.Sesame;
import org.openrdf.sesame.config.ConfigurationException;
import org.openrdf.sesame.config.AccessDeniedException;
import org.openrdf.sesame.repository.local.LocalService;
import org.openrdf.sesame.repository.local.LocalRepository;
import org.openrdf.model.Graph;

/**
 * Implementation of {@link RDFService} that uses a Sesame repository/graph
 * to manage the RDF statement data.
 */
public class RDFServiceImpl implements RDFService {

	private LocalRepository localRepository;
	private Graph graph;

	public RDFServiceImpl() throws ConfigurationException, AccessDeniedException {
		LocalService service = Sesame.getService();
		localRepository = service.createRepository("myRep", false);
		graph = localRepository.getGraph();
	}


	public RDFResource getAnonymousResource() {
		return new RDFResourceImpl(graph);
	}

	public RDFBlob getBlobLiteral(byte[] value, int length) {
		
		return new RDFBlobImpl(graph, value);
	}

	public RDFDataSource getDataSource(String uri) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public RDFDataSource getDataSourceBlocking(String uri) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public RDFDate getDateLiteral(long value) {
		return new RDFDateImpl(graph, value);
	}

	public RDFInt getIntLiteral(int value) {
		return new RDFIntImpl(graph, value);
	}

	public RDFLiteral getLiteral(String value) {
		return new RDFLiteralImpl(graph, value);
	}

	public RDFResource getResource(String uri) {
		return new RDFResourceImpl(graph, uri);
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
