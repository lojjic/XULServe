package net.lojjic.xul.rdf;

/**
 * <p>A datasource that may load asynchronously.</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFRemoteDataSource.html</p>
 */
public interface RDFRemoteDataSource {

	/**
	 * This value is true when the datasource has fully loaded itself.
	 */
	boolean isLoaded();

	/**
	 * Request that a data source write it's contents out to permanent storage, if applicable.
	 */
	void flush();

	/**
	 * @param uri
	 */
	void flushTo(String uri);

	/**
	 * Specify the URI for the data source: this is the prefix that will be used to register
	 * the data source in the data source registry.
	 *
	 * @param uri: the URI to load
	 */
	void init(String uri);

	/**
	 * Refresh the remote datasource, re-loading its contents from the URI.
	 *
	 * @param blocking: If true, the call will block until the datasource has completely reloaded.
	 */
	void refresh(boolean blocking);

}
