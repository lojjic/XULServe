package net.lojjic.xul.impl.templates;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.impl.RDFServiceImpl;

import java.util.Map;
import java.util.HashMap;

/**
 * Unit tests for {@link net.lojjic.xul.impl.templates.Expression}
 */
public class ExpressionTest extends TestCase {

	private Map<String, RDFNode> vars;

	/**
	 * Sets up the fixture, for example, open a network connection.
	 * This method is called before a test is executed.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		RDFService rdfService = new RDFServiceImpl();

		// Set up vars:
		vars = new HashMap<String, RDFNode>();
		vars.put("?one", rdfService.getLiteral("foo"));
		vars.put("?two", rdfService.getLiteral("bar"));
		vars.put("?three", rdfService.getLiteral("baz"));
		vars.put("?four", rdfService.getResource("http://lojjic.net/rdf/resource"));
	}

	/**
	 * No vars whatsoever
	 */
	public void testNoVars() throws Exception {
		Expression expression = new Expression("foo bar baz");
		assertEquals("foo bar baz", expression.evaluate(vars));
	}

	/**
	 * Vars separated by spaces:
	 */
	public void testSpacesAroundVar() throws Exception {
		Expression expression = new Expression("?one ?two ?three");
		assertEquals("foo bar baz", expression.evaluate(vars));
	}

	/**
	 * Vars with spaces after but no space before:
	 */
	public void testSpaceAfterVar() throws Exception {
		Expression expression = new Expression("foo?two ?three");
		assertEquals("foobar baz", expression.evaluate(vars));
	}

	/**
	 * Vars all together separated by carets:
	 */
	public void testCaretBetweenVars() throws Exception {
		Expression expression = new Expression("foo?one^?two^baz?three");
		assertEquals("foofoobarbazbaz", expression.evaluate(vars));
	}

	/**
	 * Escaped question marks:
	 */
	public void testEscapedQuestionMarks() throws Exception {
		Expression expression = new Expression("foo?? bar??baz?three");
		assertEquals("foo? bar?bazbaz", expression.evaluate(vars));
	}

	/**
	 * Escaped carets:
	 */
	public void testEscapedCarets() throws Exception {
		Expression expression = new Expression("?one^^?two^^baz");
		assertEquals("foo^bar^baz", expression.evaluate(vars));
	}

	/**
	 * Variables without matches:
	 */
	public void testUnknownVars() throws Exception {
		Expression expression = new Expression("?one ?two ?thirty ?three");
		assertEquals("foo bar  baz", expression.evaluate(vars));
	}

	/**
	 * Variables with RDF resource values:
	 */
	public void testResourceVars() throws Exception {
		Expression expression = new Expression("foo bar ?four baz");
		assertEquals("foo bar http://lojjic.net/rdf/resource baz", expression.evaluate(vars));
	}

}
