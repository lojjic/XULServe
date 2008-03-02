package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ReferenceMap;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.BeansException;

/**
 * <p>Implementation of {@link RDFService}.</p>
 * <p>This class implements {@link BeanFactoryAware} so it can lookup beans
 * registered in the Spring application context.  Custom built-in RDF datasources
 * can be registered by defining Spring-managed beans with the special name
 * prefix "@mozilla.org/rdf/datasource;1?name=".  For instance:</p>
 * <pre><code>&lt;bean name="@mozilla.org/rdf/datasource;1?name=my-datasource" /></code></pre>
 * <p>That bean can then be used by referring to it with the URI "rdf:my-datasource".</p>
 */
public class RDFServiceImpl implements RDFService, BeanFactoryAware {

	/**
	 * Prefix for URIs for custom built-in datasources. URIs starting with this
	 * prefix will be looked for in the Spring application context, others will
	 * be treated as URLs to RDF-XML files.
	 */
	public static final String CUSTOM_DATASOURCE_URI_PREFIX = "rdf:";

	/**
	 * The common prefix for bean names registered in the Spring application context.
	 */
	public static final String CONTRACT_ID_PREFIX = "@mozilla.org/rdf/datasource;1?name=";

	private Map<String, RDFDataSource> registeredDataSources = new HashMap<String, RDFDataSource>();

	@SuppressWarnings({"unchecked"})
	private Map<String, RDFResource> registeredResources = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);

	private BeanFactory beanFactory;


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
			dataSource = resolveDataSource(uri);
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

	/**
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	/**
	 * Resolve and return the RDF datasource for the given uri.
	 * @param uri - The uri of the datasource, e.g. "http://domain.com/blah.rdf" or "rdf:my-builtin-datasource".
	 * @return - The RDFDataSource instance for the given URI.
	 * @throws RDFException if the datasource cannot be resolved.
	 */
	private RDFDataSource resolveDataSource(String uri) throws RDFException {
		if(uri.startsWith(CUSTOM_DATASOURCE_URI_PREFIX)) {
			String beanName = CONTRACT_ID_PREFIX + uri.replaceFirst(CUSTOM_DATASOURCE_URI_PREFIX, "");
			if(!beanFactory.containsBean(beanName)) {
				throw new RDFException("Could not find bean with name " + beanName);
			}
			Object bean = beanFactory.getBean(beanName);
			if(!(bean instanceof RDFDataSource)) {
				throw new RDFException("Registered bean " + beanName + " is not of type RDFDataSource.");
			}
			return (RDFDataSource)bean;
		}
		return new RDFXMLDataSourceImpl(this, uri);
	}

}
