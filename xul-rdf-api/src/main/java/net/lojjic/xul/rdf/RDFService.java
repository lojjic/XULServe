package net.lojjic.xul.rdf;

/**
 * <p>The RDF service interface. This is a singleton object which should be
 * obtained from the container.</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFService.html</p>
 */
public interface RDFService {

	/**
	 * Construct an anonymous RDF resource.
	 */
	RDFResource getAnonymousResource();

	/**
	 * Construct an RDF literal from a data blob
	 */
	RDFBlob getBlobLiteral(byte[] value, int length);

	/**
	 * Get the <i>named data source</i> corresponding to the URI. If a data
	 * source has been registered via <code>tegisterDataSource()</code>, that
	 * data source will be returned.
	 */
	RDFDataSource getDataSource(String uri);

	/**
	 * Same as getDataSource, but if a remote/XML data source needs to be
	 * constructed, then this method will issue a <b>blocking</b> Refresh
	 * call on that data source.
	 */
	RDFDataSource getDataSourceBlocking(String uri);

	/**
	 * Construct an RDF literal from a time.
	 */
	RDFDate getDateLiteral(long value);

	/**
	 * Construct an RDF literal from an int.
	 */
	RDFInt getIntLiteral(int value);

	/**
	 * Construct an RDF literal from a Unicode string.
	 */
	RDFLiteral getLiteral(String value);

	/**
	 * Construct an RDF resource from a single-byte URI. <code>RDFService</code>
	 * caches resources that are in-use, so multiple calls to <code>getResource()</code>
	 * for the same <code>uri</code> will return identical pointers. FindResource
	 * is used to find out whether there already exists a resource corresponding to that url.
	 */
	RDFResource getResource(String uri);

	/**
	 * Construct an RDF resource from a Unicode URI. This is provided
	 * as a convenience method, allowing automatic, in-line C++
	 * conversion from <code>nsString</code> objects. The <code>uri</code> will
	 * be converted to a single-byte representation internally.
	 */
	RDFResource getUnicodeResource(String uri);

	/**
	 * Determine whether or not the given RDFResource represents a blank node.
	 */
	boolean isAnonymousResource(RDFResource resource);

	/**
	 * Register a <i>named data source</i>. The RDF service will call
	 * <code>RDFDataSource#getURI()</code> to determine the URI under
	 * which to register the data source.
	 */
	void registerDataSource(RDFDataSource dataSource, boolean replace);

	/**
	 * Registers a resource with the RDF system, making it unique w.r.t.
	 * getResource().
	 * <p/>
	 * An implementation of RDFResource should call this in its
	 * Init() method if it wishes the resource to be globally unique
	 * (which is usually the case).
	 */
	void registerResource(RDFResource resource, boolean replace);

	/**
	 * Unregister a <i>named data source</i>. The RDF service will call
	 * <code>nsIRDFDataSource::GetURI()</code> to determine the URI under which the
	 * data source was registered.
	 */
	void unregisterDataSource(RDFDataSource dataSource);

	/**
	 * Called to notify the resource manager that a resource is no
	 * longer in use. This method should only be called from the
	 * destructor of a "custom" resource implementation to notify the
	 * RDF service that the last reference to the resource has been
	 * released, so the resource is no longer valid.
	 */
	void unregisterResource(RDFResource resource);

}
