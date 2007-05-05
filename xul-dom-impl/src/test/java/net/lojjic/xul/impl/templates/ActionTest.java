package net.lojjic.xul.impl.templates;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.impl.RDFServiceImpl;

import java.util.Map;
import java.util.HashMap;

/**
 * Unit tests for {@link net.lojjic.xul.impl.templates.Action}
 */
public class ActionTest extends TestCase {

	/**
	 * Test compilation and expansion of attribute expressions
	 */
	public void testExpressions() throws Exception {
		// Set up vars:
		RDFService rdfService = new RDFServiceImpl();
		Map<String, RDFNode> vars = new HashMap<String, RDFNode>();
		vars.put("?one", rdfService.getLiteral("foo"));
		vars.put("?two", rdfService.getLiteral("bar"));
		vars.put("?three", rdfService.getLiteral("baz"));
		vars.put("?four", rdfService.getResource("http://lojjic.net/rdf/resource"));

		// No vars whatsoever:
		Expression expression = new Expression("foo bar baz");
		assertEquals("foo bar baz", expression.evaluate(vars));

		// Vars separated by spaces:
		expression = new Expression("?one ?two ?three");
		assertEquals("foo bar baz", expression.evaluate(vars));

		// Vars with spaces after but no space before:
		expression = new Expression("foo?two ?three");
		assertEquals("foobar baz", expression.evaluate(vars));

		// Vars all together separated by carets:
		expression = new Expression("foo?one^?two^baz?three");
		assertEquals("foofoobarbazbaz", expression.evaluate(vars));

		// Escaped question marks:
		expression = new Expression("foo?? bar??baz?three");
		assertEquals("foo? bar?bazbaz", expression.evaluate(vars));

		// Escaped carets:
		expression = new Expression("?one^^?two^^baz");
		assertEquals("foo^bar^baz", expression.evaluate(vars));

		// Variables without matches:
		expression = new Expression("?one ?two ?thirty");
		assertEquals("foo bar ?thirty", expression.evaluate(vars));

		// Variables with RDF resource values:
		expression = new Expression("foo bar ?four baz");
		assertEquals("foo bar http://lojjic.net/rdf/resource baz", expression.evaluate(vars));
	}

}
