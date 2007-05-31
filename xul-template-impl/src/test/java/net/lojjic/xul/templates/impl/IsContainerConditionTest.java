package net.lojjic.xul.templates.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFServiceImpl;
import net.lojjic.xul.rdf.impl.RDFMemoryDataSourceImpl;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for {@link net.lojjic.xul.impl.templates.IsContainerCondition}
 */
public class IsContainerConditionTest extends TestCase {

	private RDFService rdfService;
	private RDFDataSource dataSource;
	private RDFResource container;
	private RDFResource nonContainer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
		dataSource = new RDFMemoryDataSourceImpl(rdfService);

		container = rdfService.getResource("http://lojjic.net/rdf/container");
		nonContainer = rdfService.getResource("http://lojjic.net/rdf/non-container");

		RDFContainerUtils containerUtils = new RDFContainerUtilsImpl(rdfService);
		containerUtils.makeSeq(dataSource, container);
	}

	public void testIsContainer() throws Exception {
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		varsMap.put("?start", container);
		varsList.add(varsMap);

		Condition condition = new IsContainerCondition(rdfService, "?start", true);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(1, varsList.size());

		varsMap.put("?start", nonContainer);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(0, varsList.size());
	}

	public void testIsNotContainer() throws Exception {
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		varsMap.put("?start", nonContainer);
		varsList.add(varsMap);

		Condition condition = new IsContainerCondition(rdfService, "?start", false);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(1, varsList.size());

		varsMap.put("?start", container);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(0, varsList.size());
	}

}
