package net.lojjic.xul.rdf.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFContainerUtils;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;

/**
 * Unit tests for {@link net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl}
 */
public class RDFContainerUtilsImplTest extends TestCase {

	private RDFServiceImpl rdfService;
	private RDFContainerUtils rdfContainerUtils;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
		rdfContainerUtils = new RDFContainerUtilsImpl(rdfService);
	}

	public void testIsOrdinalProperty() throws Exception {
		RDFResource ordProp = rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_12345");
		RDFResource nonOrdProp = rdfService.getResource(RDFConstants.RDF_NAMESPACE + "type");
		assertTrue(rdfContainerUtils.isOrdinalProperty(ordProp));
		assertFalse(rdfContainerUtils.isOrdinalProperty(nonOrdProp));
	}

	public void testIndexToOrdinalResource() throws Exception {
		RDFResource control = rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_12345");
		RDFResource test = rdfContainerUtils.indexToOrdinalResource(12345);
		assertEquals(control, test);
	}

	public void testOrdinalResourceToIndex() throws Exception {
		assertEquals(1, rdfContainerUtils.ordinalResourceToIndex(rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_1")));
		assertEquals(10, rdfContainerUtils.ordinalResourceToIndex(rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_10")));
		assertEquals(999, rdfContainerUtils.ordinalResourceToIndex(rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_999")));
	}

	public void testIsContainer() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		RDFResource nonContainer = rdfService.getResource("http://lojjic.net/rdf/my-non-container");
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.SEQ_URI), true);
		ds.doAssert(nonContainer, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource("http://lojjic.net/rdf/my-type"), true);
		assertTrue(rdfContainerUtils.isContainer(ds, container));
		assertFalse(rdfContainerUtils.isContainer(ds, nonContainer));
	}

	public void testIsEmpty() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.SEQ_URI), true);
		assertTrue(rdfContainerUtils.isEmpty(ds, container));
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_NAMESPACE + "_1"), rdfService.getLiteral("value"), true);
		assertFalse(rdfContainerUtils.isEmpty(ds, container));
	}

	public void testIsBag() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isBag(ds, container));
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.BAG_URI), true);
		assertTrue(rdfContainerUtils.isBag(ds, container));
	}

	public void testIsSeq() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isSeq(ds, container));
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.SEQ_URI), true);
		assertTrue(rdfContainerUtils.isSeq(ds, container));
	}

	public void testIsAlt() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isAlt(ds, container));
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.ALT_URI), true);
		assertTrue(rdfContainerUtils.isAlt(ds, container));
	}

	public void testMakeBag() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isBag(ds, container));
		rdfContainerUtils.makeBag(ds, container);
		assertTrue(rdfContainerUtils.isBag(ds, container));
	}

	public void testMakeSeq() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isSeq(ds, container));
		rdfContainerUtils.makeSeq(ds, container);
		assertTrue(rdfContainerUtils.isSeq(ds, container));
	}

	public void testMakeAlt() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		assertFalse(rdfContainerUtils.isAlt(ds, container));
		rdfContainerUtils.makeAlt(ds, container);
		assertTrue(rdfContainerUtils.isAlt(ds, container));
	}

	public void testIndexOf() throws Exception {
		RDFDataSource ds = new RDFMemoryDataSourceImpl(rdfService);
		RDFResource container = rdfService.getResource("http://lojjic.net/rdf/my-container");
		RDFNode target1 = rdfService.getLiteral("Literal Value 1");
		RDFNode target2 = rdfService.getResource("http://lojjic.net/rdf/my-target-2");
		RDFNode target3 = rdfService.getIntLiteral(3);
		ds.doAssert(container, rdfService.getResource(RDFConstants.RDF_TYPE), rdfService.getResource(RDFConstants.SEQ_URI), true);
		ds.doAssert(container, rdfContainerUtils.indexToOrdinalResource(1), target1, true);
		ds.doAssert(container, rdfContainerUtils.indexToOrdinalResource(2), target2, true);
		ds.doAssert(container, rdfContainerUtils.indexToOrdinalResource(3), target3, true);
		assertEquals(1, rdfContainerUtils.indexOf(ds, container, target1));
		assertEquals(2, rdfContainerUtils.indexOf(ds, container, target2));
		assertEquals(3, rdfContainerUtils.indexOf(ds, container, target3));
	}
}
