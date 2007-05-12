package net.lojjic.xul.impl.templates;

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
 * Unit tests for {@link net.lojjic.xul.impl.templates.IsEmptyCondition}
 */
public class IsEmptyConditionTest extends TestCase {

	private RDFService rdfService;
	private RDFDataSource dataSource;
	private RDFResource emptyContainer;
	private RDFResource notEmptyContainer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rdfService = new RDFServiceImpl();
		dataSource = new RDFMemoryDataSourceImpl(rdfService);

		emptyContainer = rdfService.getResource("http://lojjic.net/rdf/empty-container");
		notEmptyContainer = rdfService.getResource("http://lojjic.net/rdf/not-empty-container");

		RDFContainerUtils containerUtils = new RDFContainerUtilsImpl(rdfService);
		containerUtils.makeSeq(dataSource, emptyContainer);
		containerUtils.makeSeq(dataSource, notEmptyContainer);
		RDFResource ordinal = containerUtils.indexToOrdinalResource(1);
		dataSource.doAssert(notEmptyContainer, ordinal, rdfService.getLiteral("value"), true);
	}

	public void testEmpty() throws Exception {
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		varsMap.put("?container", emptyContainer);
		varsList.add(varsMap);

		Condition condition = new IsEmptyCondition(rdfService, "?container", true);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(1, varsList.size());

		varsMap.put("?container", notEmptyContainer);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(0, varsList.size());
	}

	public void testNotEmpty() throws Exception {
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		varsMap.put("?container", notEmptyContainer);
		varsList.add(varsMap);

		Condition condition = new IsEmptyCondition(rdfService, "?container", false);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(1, varsList.size());

		varsMap.put("?container", emptyContainer);
		condition.applyToVariablesList(dataSource, varsList, null);
		assertEquals(0, varsList.size());
	}

}
