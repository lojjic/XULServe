package net.lojjic.xul.templates.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.impl.RDFServiceImpl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for {@link net.lojjic.xul.templates.impl.Binding}
 */
public class BindingTest extends TestCase {

	/**
	 * Test forward arc triple where both subject and object are variables
	 */
	public void testForwardArcBothVariable() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource("classpath:net/lojjic/xul/templates/impl/photos.rdf");

		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		RDFResource start = rdfService.getResource("http://www.xulplanet.com/ndeakin/images/t/palace.jpg");
		varsMap.put("?start", start);
		varsList.add(varsMap);

		Binding binding = new Binding(rdfService, "?start", "http://purl.org/dc/elements/1.1/title", "?title");
		binding.applyToVariablesList(dataSource, varsList, start);

		assertEquals(1, varsList.size());
		assertEquals(2, varsList.get(0).size());
		assertEquals(start, varsList.get(0).get("?start"));
		assertEquals(rdfService.getLiteral("Palace from Above"), varsList.get(0).get("?title"));
	}

	/**
	 * Test forward arc triple where the subject is a variable but the object is a literal value
	 */
	public void testForwardArcObjectLiteral() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource("classpath:net/lojjic/xul/templates/impl/photos.rdf");

		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		RDFResource start = rdfService.getResource("http://www.xulplanet.com/ndeakin/images/t/palace.jpg");
		varsMap.put("?start", start);
		varsList.add(varsMap);

		Binding binding = new Binding(rdfService, "?start", "http://purl.org/dc/elements/1.1/title", "Palace from Above");
		binding.applyToVariablesList(dataSource, varsList, start);

		assertEquals(1, varsList.size());
		assertEquals(1, varsList.get(0).size());
		assertEquals(start, varsList.get(0).get("?start"));

		// negative test - verify row is not removed:
		start = rdfService.getResource("http://www.xulplanet.com/ndeakin/images/t/obelisk.jpg");
		varsMap.put("?start", start);
		binding.applyToVariablesList(dataSource, varsList, start);
		assertEquals(1, varsList.size());
	}

	/**
	 * Test backward arc triple where both subject and object are variables
	 */
	public void testBackwardArcBothVariable() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource("classpath:net/lojjic/xul/templates/impl/photos4.rdf");

		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		RDFResource country = rdfService.getResource("http://www.daml.org/2001/09/countries/iso#NL");
		varsMap.put("?country", country);
		varsList.add(varsMap);

		Binding binding = new Binding(rdfService, "?photo", "http://www.xulplanet.com/rdf/country", "?country");
		binding.applyToVariablesList(dataSource, varsList, country);

		assertEquals(1, varsList.size());
		assertEquals(2, varsList.get(0).size());
		assertEquals(country, varsList.get(0).get("?country"));
		assertEquals(rdfService.getResource("http://www.xulplanet.com/ndeakin/images/t/canal.jpg"), varsList.get(0).get("?photo"));
	}

	/**
	 * Test backward arc triple where the object is a variable but the subject is a uri
	 */
	public void testBackwardArcSubjectURI() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource("classpath:net/lojjic/xul/templates/impl/photos4.rdf");

		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		Map<String, RDFNode> varsMap = new HashMap<String, RDFNode>();
		RDFResource country = rdfService.getResource("http://www.daml.org/2001/09/countries/iso#IT");
		varsMap.put("?country", country);
		varsList.add(varsMap);

		Binding binding = new Binding(rdfService, "http://www.xulplanet.com/ndeakin/images/t/obelisk.jpg", "http://www.xulplanet.com/rdf/country", "?country");
		binding.applyToVariablesList(dataSource, varsList, country);

		assertEquals(1, varsList.size());
		assertEquals(1, varsList.get(0).size());
		assertEquals(country, varsList.get(0).get("?country"));

		// negative test - verify row is not removed:
		country = rdfService.getResource("http://www.daml.org/2001/09/countries/iso#NL");
		varsMap.put("?country", country);
		binding.applyToVariablesList(dataSource, varsList, country);
		assertEquals(1, varsList.size());
	}


}
