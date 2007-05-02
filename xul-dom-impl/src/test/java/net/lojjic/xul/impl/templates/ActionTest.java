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
		vars.put("?four^four", rdfService.getLiteral("boff"));
		vars.put("?five", rdfService.getResource("http://lojjic.net/rdf/resource"));

		// No vars whatsoever:
		Action.Expression expression = new Action.Expression("foo bar baz");
		assertEquals("foo bar baz", expression.expand(vars));

		// Vars separated by spaces:
		expression = new Action.Expression("?one ?two ?three");
		assertEquals("foo bar baz", expression.expand(vars));

		// Vars with spaces after but no space before:
		expression = new Action.Expression("foo?two ?three");
		assertEquals("foobar baz", expression.expand(vars));

		// Vars all together separated by carets:
		expression = new Action.Expression("foo?one^?two^baz?three");
		assertEquals("foofoobarbazbaz", expression.expand(vars));

		// Escaped question marks:
		expression = new Action.Expression("foo?? bar??baz?three");
		assertEquals("foo? bar?bazbaz", expression.expand(vars));

		// Escaped carets:
		expression = new Action.Expression("?one ?two^?three ?four^^four");
		assertEquals("foo barbaz boff", expression.expand(vars));

		// Variables without matches:
		expression = new Action.Expression("?one ?two ?thirty");
		assertEquals("foo bar ?thirty", expression.expand(vars));

		// Variables with RDF resource values:
		expression = new Action.Expression("foo bar ?five baz");
		assertEquals("foo bar http://lojjic.net/rdf/resource baz", expression.expand(vars));
	}

}
