package net.lojjic.xul.rdf.impl;

import org.openrdf.rio.RDFFormat;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.model.Statement;

import java.io.InputStream;
import java.util.Collection;

/**
 * Test cases for {@link net.lojjic.xul.rdf.impl.RDFMemoryDataSourceImpl}
 */
public class RDFMemoryDataSourceImplTest extends AbstractDataSourceImplTest<RDFMemoryDataSourceImpl> {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = new RDFMemoryDataSourceImpl(rdfService);
		addData();
	}

	/**
	 * Test basic creation of the data source
	 */
	public void testBasic() throws Exception {
		assertNotNull(dataSource);
	}

	/**
	 * Populate the {@link net.lojjic.xul.rdf.impl.RDFMemoryDataSourceImpl} with data
	 */
	private void addData() throws Exception {
		String xmlFile = "/net/lojjic/xul/rdf/impl/animals.rdf";
		InputStream inputStream = getClass().getResourceAsStream(xmlFile);
		Repository repository = new SailRepository(new MemoryStore());
		repository.initialize();
		RepositoryConnection conn = repository.getConnection();
		try {
			conn.add(inputStream, "", RDFFormat.RDFXML);
			Collection<Statement> statements = conn.getStatements(null, null, null, false).asCollection();
			for(Statement statement : statements) {
				dataSource.doAssert(
						SesameUtils.toRDFResource(rdfService, statement.getSubject()),
						SesameUtils.toRDFResource(rdfService, statement.getPredicate()),
						SesameUtils.toRDFNode(rdfService, statement.getObject()),
						true
				);
			}
		}
		finally {
			conn.close();
			inputStream.close();
		}
	}

}
