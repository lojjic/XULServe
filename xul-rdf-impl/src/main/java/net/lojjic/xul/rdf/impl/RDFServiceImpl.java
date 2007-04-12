package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ReferenceMap;

/**
 * Implementation of {@link RDFService} that uses a Sesame repository/graph
 * to manage the RDF statement data.
 */
public class RDFServiceImpl implements RDFService {

	private Map<String, RDFDataSource> registeredDataSources = new HashMap<String, RDFDataSource>();

	private Map<String, RDFResource> registeredResources = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);

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
			// TODO choose the correct datasource class to instantiate.
			dataSource = new RDFXMLDataSourceImpl(this, uri);
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
		RDFResource resource = registeredResources.get(uri);
		if(resource == null) {
			resource = new RDFResourceImpl(uri);
			registerResource(resource, true);
		}
		return resource;
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
		if(!replace && registeredResources.containsKey(resource.getValue())) {
			return;
		}
		registeredResources.put(resource.getValue(), resource);
	}

	public void unregisterResource(RDFResource resource) {
		registeredResources.remove(resource.getValue());
	}
}
