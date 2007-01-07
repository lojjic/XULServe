package net.lojjic.xul.rdf;

/**
 * <p>RDF service interface.</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFService.html</p>
 */
public interface RDFService {

	RDFResource GetAnonymousResource();

	RDFBlob getBlobLiteral(byte[] value, int length);

	RDFDataSource getDataSource(String URI);

	RDFDataSource getDataSourceBlocking(String URI);

	RDFDate getDateLiteral(long value);

	RDFInt getIntLiteral(int value);

	RDFLiteral getLiteral(String value);

	RDFResource getResource(String URI);

	RDFResource getUnicodeResource(String URI);

	boolean isAnonymousResource(RDFResource resource);

	void registerDataSource(RDFDataSource dataSource, boolean replace);

	void registerResource(RDFResource resource, boolean replace);

	void unregisterDataSource(RDFDataSource dataSource);

	void unregisterResource(RDFResource resource);

}
