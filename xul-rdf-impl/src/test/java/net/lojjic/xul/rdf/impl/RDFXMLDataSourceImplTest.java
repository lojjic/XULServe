package net.lojjic.xul.rdf.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl}
 */
public class RDFXMLDataSourceImplTest extends TestCase {

	private static final String RDFXML_DOC_URI = "classpath:/net/lojjic/xul/rdf/impl/animals.rdf";
	private RDFService rdfService;
	private RDFXMLDataSourceImpl dataSource;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
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

	/**
	 * Test for {@link RDFXMLDataSourceImpl#arcLabelsIn(RDFNode)}
	 */
	public void testArcLabelsIn() throws Exception {
		RDFNode target = rdfService.getLiteral("Hippopotamus amphibius");
		Iterator<RDFResource> resultsIter = dataSource.arcLabelsIn(target);

		assertTrue(resultsIter.hasNext());
		assertEquals("http://www.some-fictitious-zoo.com/rdf#species", resultsIter.next().getValue());
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#arcLabelsOut(RDFResource)}
	 */
	public void testArcLabelsOut() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		List<RDFResource> results = iteratorToList(dataSource.arcLabelsOut(source));

		assertEquals(3, results.size());
		assertTrue(results.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name")));
		assertTrue(results.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#species")));
		assertTrue(results.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#class")));
		assertFalse(results.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#not-a-property")));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#change(RDFResource, RDFResource, RDFNode, RDFNode)}
	 */
	public void testChange() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode oldTarget = rdfService.getLiteral("Lion");
		RDFNode newTarget = rdfService.getLiteral("Kitten");

		dataSource.change(source, arc, oldTarget, newTarget);
		assertTrue(dataSource.getTarget(source, arc, true).equalsNode(newTarget));

		// Undo changes
		dataSource.refresh(true);
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#doAssert(RDFResource, RDFResource, RDFNode, boolean)}
	 */
	public void testDoAssert() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/hippopotamus");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#ass-size");
		RDFNode target = rdfService.getLiteral("Huge");

		dataSource.doAssert(source, arc, target, true);
		assertTrue(dataSource.getTarget(source, arc, true).equalsNode(target));

		// Undo changes
		dataSource.refresh(true);
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#getAllResources()}
	 */
	public void testGetAllResources() throws Exception {
		List<RDFResource> resources = iteratorToList(dataSource.getAllResources());

		assertEquals(4, resources.size());
		assertTrue(resources.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion")));
		assertTrue(resources.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/hippopotamus")));
		assertTrue(resources.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/arachnids/tarantula")));
		assertTrue(resources.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/all-animals")));
		assertFalse(resources.contains(rdfService.getResource("http://www.some-fictitious-zoo.com/not-a-resource")));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#getSource(RDFResource, RDFNode, boolean)}
	 */
	public void testGetSource() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		assertEquals(source, dataSource.getSource(arc, target, true));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#getSources(RDFResource, RDFNode, boolean)}
	 */
	public void testGetSources() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		List<RDFResource> sources = iteratorToList(dataSource.getSources(arc, target, true));
		assertEquals(1, sources.size());
		assertEquals(source, sources.get(0));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#getTarget(RDFResource, RDFResource, boolean)}
	 */
	public void testGetTarget() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		assertEquals(target, dataSource.getTarget(source, arc, true));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#getTargets(RDFResource, RDFResource, boolean)}
	 */
	public void testGetTargets() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		List<RDFNode> targets = iteratorToList(dataSource.getTargets(source, arc, true));
		assertEquals(1, targets.size());
		assertEquals(target, targets.get(0));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#hasArcIn(RDFNode, RDFResource)}
	 */
	public void testHasArcIn() throws Exception {
		RDFNode target = rdfService.getLiteral("Tarantula");
		RDFResource goodArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFResource badArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#not-a-property");

		assertTrue(dataSource.hasArcIn(target, goodArc));
		assertFalse(dataSource.hasArcIn(target, badArc));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#hasArcOut(RDFResource, RDFResource)}
	 */
	public void testHasArcOut() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/hippopotamus");
		RDFResource goodArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFResource badArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#not-a-property");

		assertTrue(dataSource.hasArcOut(source, goodArc));
		assertFalse(dataSource.hasArcOut(source, badArc));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#hasAssertion(RDFResource, RDFResource, RDFNode, boolean)}
	 */
	public void testHasAssertion() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");
		RDFNode badTarget = rdfService.getLiteral("Kitten");

		assertTrue(dataSource.hasAssertion(source, arc, target, true));
		assertFalse(dataSource.hasAssertion(source, arc, badTarget, true));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#move(RDFResource, RDFResource, RDFResource, RDFNode)} 
	 */
	public void testMove() throws Exception {
		RDFResource oldSource = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource newSource = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/kitty");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		dataSource.move(oldSource, newSource, arc, target);
		assertTrue(dataSource.hasAssertion(newSource, arc, target, true));
		assertFalse(dataSource.hasAssertion(oldSource, arc, target, true));
	}

	/**
	 * Test for {@link RDFXMLDataSourceImpl#unassert(RDFResource, RDFResource, RDFNode)}
	 */
	public void testUnassert() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		dataSource.unassert(source, arc, target);
		assertFalse(dataSource.hasAssertion(source, arc, target, true));
	}

	/**
	 * Utility to populate a typed List from a typed Iterator
	 */
	private static <T> List<T> iteratorToList(Iterator<T> iterator) {
		ArrayList<T> list = new ArrayList<T>();
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}


}
