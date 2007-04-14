package net.lojjic.xul.rdf.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.*;

import java.util.List;

import info.aduna.collections.iterators.Iterators;

/**
 * Unit test cases for {@link net.lojjic.xul.rdf.impl.RDFContainerImpl}
 */
public class RDFContainerImplTest extends TestCase {

	private static final String RDFXML_DOC_URI = "classpath:/net/lojjic/xul/rdf/impl/animals.rdf";
	private static final String LION_URI = "http://www.some-fictitious-zoo.com/mammals/lion";
	private static final String KITTEN_URI = "http://www.some-fictitious-zoo.com/mammals/kitten";
	private static final String TARANTULA_URI = "http://www.some-fictitious-zoo.com/arachnids/tarantula";
	private static final String HIPPO_URI = "http://www.some-fictitious-zoo.com/mammals/hippopotamus";

	private RDFService rdfService;
	private RDFXMLDataSourceImpl dataSource;
	private RDFResource resource;
	private RDFContainer container;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
		dataSource = (RDFXMLDataSourceImpl)rdfService.getDataSource(RDFXML_DOC_URI);
		resource = rdfService.getResource("http://www.some-fictitious-zoo.com/all-animals");
		container = new RDFContainerImpl(rdfService, dataSource, resource);
	}


	public void testGetCount() throws Exception {
		assertEquals(3, container.getCount());
	}

	public void testGetElements() throws Exception {
		List<RDFNode> elements = Iterators.asList(container.getElements());
		assertEquals(3, elements.size());
		assertTrue(elements.contains(rdfService.getResource(LION_URI)));
		assertFalse(elements.contains(rdfService.getResource(KITTEN_URI)));
	}

	public void testAppendElement() throws Exception {
		RDFNode newMember = rdfService.getResource(KITTEN_URI);
		container.appendElement(newMember);
		assertEquals(4, container.getCount());
		assertEquals(4, container.indexOf(newMember));
		dataSource.refresh(true);
	}

	public void testRemoveElement() throws Exception {
		RDFNode element = rdfService.getResource(TARANTULA_URI);
		container.removeElement(element, true);
		assertEquals(2, container.getCount());
		assertEquals(2, container.indexOf(rdfService.getResource(HIPPO_URI)));
		dataSource.refresh(true);
	}

	public void testInsertElementAt() throws Exception {
		RDFNode newMember = rdfService.getResource(KITTEN_URI);
		container.insertElementAt(newMember, 2, true);
		assertEquals(4, container.getCount());
		assertEquals(2, container.indexOf(newMember));
		assertEquals(3, container.indexOf(rdfService.getResource(TARANTULA_URI)));
		dataSource.refresh(true);
	}

	public void testRemoveElementAt() throws Exception {
		RDFNode removedElement = container.removeElementAt(2, true);
		assertEquals(2, container.getCount());
		assertEquals(rdfService.getResource(TARANTULA_URI), removedElement);
		dataSource.refresh(true);
	}

	public void testIndexOf() throws Exception {
		assertEquals(2, container.indexOf(rdfService.getResource(TARANTULA_URI)));
		assertEquals(-1, container.indexOf(rdfService.getResource(KITTEN_URI)));
	}

}
