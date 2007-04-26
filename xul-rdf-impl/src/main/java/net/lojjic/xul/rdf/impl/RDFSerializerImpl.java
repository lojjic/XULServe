package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFSerializer;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.sail.memory.MemoryStore;

import java.io.OutputStream;
import java.util.Iterator;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFSerializer} for serializing RDFXML
 */
public class RDFSerializerImpl implements RDFSerializer {

	/**
	 * Synchronously serialize the given datasource to the OutputStream.
	 *
	 * Implementations are not required to implement any buffering or
	 * other stream-based optimizations.
	 *
	 * @param dataSource The RDF data source to be serialized.
	 * @param out The output stream to use.
	 */
	public void serialize(RDFDataSource dataSource, final OutputStream out) {
		Repository repository;

		// Optimization for RDFMemoryDataSourceImpl implementation; use
		// the underlying Sesame repository directly to avoid translations:
		if(dataSource instanceof SesameDataSourceImpl) {
			repository = ((SesameDataSourceImpl)dataSource).getSesameRepository();
		}
		// For other implementations, build an in-memory Sesame repository
		// and copy each statement into it:
		else {
			try {
				repository = new SailRepository(new MemoryStore());
				repository.initialize();
				copyRDFDataSourceToSesameRepository(dataSource, repository);
			}
			catch (Exception e) {
				throw new RDFException(e);
			}
		}

		SesameUtils.execute(
			repository,
			new SesameConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					RDFXMLWriter rdfxmlWriter = new RDFXMLWriter(out);
					conn.export(rdfxmlWriter);
					return null;
				}
			}
		);
	}


	/**
	 * Copy all the statements from the given {@link net.lojjic.xul.rdf.RDFDataSource}
	 * into the given Sesame {@link org.openrdf.repository.Repository}.
	 */
	private void copyRDFDataSourceToSesameRepository(final RDFDataSource rdfDataSource, final Repository repository) {
		SesameUtils.execute(
			repository,
			new SesameConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					Iterator<RDFResource> subjects = rdfDataSource.getAllResources();
					while(subjects.hasNext()) {
						RDFResource subj = subjects.next();
						Iterator<RDFResource> arcs = rdfDataSource.arcLabelsOut(subj);
						while(arcs.hasNext()) {
							RDFResource arc = arcs.next();
							Iterator<RDFNode> targets = rdfDataSource.getTargets(subj, arc, true);
							while(targets.hasNext()) {
								conn.add(
										SesameUtils.toResource(repository, subj),
										SesameUtils.toURI(repository, arc),
										SesameUtils.toValue(repository, targets.next())
								);
							}
						}
					}
					return null;
				}
			}
		);
	}
}
