package net.lojjic.xul.rdf.impl;

/**
 * Test cases for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl}
 */
public class RDFXMLDataSourceImplTest extends AbstractDataSourceImplTest<RDFXMLDataSourceImpl> {

	private static final String RDFXML_DOC_URI = "classpath:/net/lojjic/xul/rdf/impl/animals.rdf";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataSource = (RDFXMLDataSourceImpl)rdfService.getDataSource(RDFXML_DOC_URI);
	}

	/**
	 * Test basic creation of the data source
	 */
	public void testBasic() throws Exception {
		assertNotNull(dataSource);
		assertTrue(dataSource.isLoaded());
		assertEquals(RDFXML_DOC_URI, dataSource.getURI());
	}

}
