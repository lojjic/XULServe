package net.lojjic.xul.rdf.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Test cases for {@link net.lojjic.xul.rdf.impl.AbstractDataSourceImpl}.
 */
public abstract class AbstractDataSourceImplTest<T extends AbstractDataSourceImpl> extends TestCase {

	protected T dataSource;
	protected RDFService rdfService;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#arcLabelsIn(net.lojjic.xul.rdf.RDFNode)}
	 */
	public void testArcLabelsIn() throws Exception {
		RDFNode target = rdfService.getLiteral("Hippopotamus amphibius");
		Iterator<RDFResource> resultsIter = dataSource.arcLabelsIn(target);

		assertTrue(resultsIter.hasNext());
		assertEquals("http://www.some-fictitious-zoo.com/rdf#species", resultsIter.next().getValue());
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#arcLabelsOut(net.lojjic.xul.rdf.RDFResource)}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#change(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode,net.lojjic.xul.rdf.RDFNode)}
	 */
	public void testChange() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode oldTarget = rdfService.getLiteral("Lion");
		RDFNode newTarget = rdfService.getLiteral("Kitten");

		dataSource.change(source, arc, oldTarget, newTarget);
		assertTrue(dataSource.getTarget(source, arc, true).equalsNode(newTarget));

		// Undo changes
		dataSource.change(source, arc, newTarget, oldTarget);
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#doAssert(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode,boolean)}
	 */
	public void testDoAssert() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/hippopotamus");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#ass-size");
		RDFNode target = rdfService.getLiteral("Huge");

		dataSource.doAssert(source, arc, target, true);
		assertTrue(dataSource.getTarget(source, arc, true).equalsNode(target));

		// Undo changes
		dataSource.unassert(source, arc, target);
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#getAllResources()}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#getSource(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode,boolean)}
	 */
	public void testGetSource() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		assertEquals(source, dataSource.getSource(arc, target, true));
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#getSources(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode,boolean)}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#getTarget(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,boolean)}
	 */
	public void testGetTarget() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/lion");
		RDFResource arc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFNode target = rdfService.getLiteral("Lion");

		assertEquals(target, dataSource.getTarget(source, arc, true));
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#getTargets(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,boolean)}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#hasArcIn(net.lojjic.xul.rdf.RDFNode,net.lojjic.xul.rdf.RDFResource)}
	 */
	public void testHasArcIn() throws Exception {
		RDFNode target = rdfService.getLiteral("Tarantula");
		RDFResource goodArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFResource badArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#not-a-property");

		assertTrue(dataSource.hasArcIn(target, goodArc));
		assertFalse(dataSource.hasArcIn(target, badArc));
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#hasArcOut(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource)}
	 */
	public void testHasArcOut() throws Exception {
		RDFResource source = rdfService.getResource("http://www.some-fictitious-zoo.com/mammals/hippopotamus");
		RDFResource goodArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#name");
		RDFResource badArc = rdfService.getResource("http://www.some-fictitious-zoo.com/rdf#not-a-property");

		assertTrue(dataSource.hasArcOut(source, goodArc));
		assertFalse(dataSource.hasArcOut(source, badArc));
	}

	/**
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#hasAssertion(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode,boolean)}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#move(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode)}
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
	 * Test for {@link net.lojjic.xul.rdf.impl.RDFXMLDataSourceImpl#unassert(net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFResource,net.lojjic.xul.rdf.RDFNode)}
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
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
}
