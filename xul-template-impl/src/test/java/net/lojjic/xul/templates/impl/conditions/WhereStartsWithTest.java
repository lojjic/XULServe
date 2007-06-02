package net.lojjic.xul.templates.impl.conditions;

import junit.framework.TestCase;

/**
 * Unit tests for {@link WhereStartsWith}
 */
public class WhereStartsWithTest extends TestCase {

	public void testBasicSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name", "Fred", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertFalse(where.matches(result));
	}

	public void testBasicValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "Freddy", "?fragment", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "Fred");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Sam");
		assertFalse(where.matches(result));
	}

	public void testBasicBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name", "?fragment", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		result.setBinding("?fragment", "Fred");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Sam");
		assertFalse(where.matches(result));
	}

	public void testNegatedSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name", "Fred", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertFalse(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertTrue(where.matches(result));
	}

	public void testNegatedValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "Freddy", "?fragment", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "Fred");
		assertFalse(where.matches(result));

		result.setBinding("?fragment", "Sam");
		assertTrue(where.matches(result));
	}

	public void testNegatedBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name", "?fragment", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		result.setBinding("?fragment", "Fred");
		assertFalse(where.matches(result));

		result.setBinding("?fragment", "Sam");
		assertTrue(where.matches(result));
	}

	public void testIgnoreCaseSubjectVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("startswith", "?name", "Fred", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("startswith", "?name", "Fred", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "fREdDy");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseValueVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("startswith", "Freddy", "?fragment", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("startswith", "Freddy", "?fragment", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "fReD");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseBothVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("startswith", "?name", "?fragment", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("startswith", "?name", "?fragment", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "fREdDy");
		result.setBinding("?fragment", "FRed");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testMultipleSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name", "Fred,Sam,Jo", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Samuel");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Richard");
		assertFalse(where.matches(result));
	}

	public void testMultipleValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "Freddy,Samuel,Joseph", "?fragment", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "Fred");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Jo");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Rich");
		assertFalse(where.matches(result));
	}

	public void testMultipleBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("startswith", "?name1,?name2,?name3", "?fragment", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "Freddy");
		result.setBinding("?name2", "Samuel");
		result.setBinding("?name3", "Joseph");

		result.setBinding("?fragment", "Fred");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Jo");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "Rich");
		assertFalse(where.matches(result));
	}

}
