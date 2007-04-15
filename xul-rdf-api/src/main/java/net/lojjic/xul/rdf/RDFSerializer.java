package net.lojjic.xul.rdf;

import java.io.OutputStream;

/**
 * Interface used to serialize RDF.
 */
public interface RDFSerializer {
	/**
	 * Synchronously serialize the given datasource to the OutputStream.
	 *
	 * Implementations are not required to implement any buffering or
	 * other stream-based optimizations.
	 *
	 * @param dataSource The RDF data source to be serialized.
	 * @param out The output stream to use.
	 */
	void serialize(RDFDataSource dataSource, OutputStream out);
}
