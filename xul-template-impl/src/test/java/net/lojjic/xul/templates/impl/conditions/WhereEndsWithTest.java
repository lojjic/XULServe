package net.lojjic.xul.templates.impl.conditions;

import junit.framework.TestCase;

/**
 * Unit tests for {@link WhereEndsWith}
 */
public class WhereEndsWithTest extends TestCase {

	public void testBasicSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name", "eddy", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertFalse(where.matches(result));
	}

	public void testBasicValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "Freddy", "?fragment", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "eddy");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertFalse(where.matches(result));
	}

	public void testBasicBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name", "?fragment", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		result.setBinding("?fragment", "eddy");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertFalse(where.matches(result));
	}

	public void testNegatedSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name", "eddy", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertFalse(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertTrue(where.matches(result));
	}

	public void testNegatedValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "Freddy", "?fragment", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "eddy");
		assertFalse(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertTrue(where.matches(result));
	}

	public void testNegatedBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name", "?fragment", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		result.setBinding("?fragment", "eddy");
		assertFalse(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertTrue(where.matches(result));
	}

	public void testIgnoreCaseSubjectVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("endswith", "?name", "eddy", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("endswith", "?name", "eddy", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "fREdDy");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseValueVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("endswith", "Freddy", "?fragment", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("endswith", "Freddy", "?fragment", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "eDdY");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseBothVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("endswith", "?name", "?fragment", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("endswith", "?name", "?fragment", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "fREdDy");
		result.setBinding("?fragment", "eDdY");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testMultipleSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name", "eddy,uel,eph", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Samuel");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Richard");
		assertFalse(where.matches(result));
	}

	public void testMultipleValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "Freddy,Samuel,Joseph", "?fragment", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?fragment", "eddy");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "ard");
		assertFalse(where.matches(result));
	}

	public void testMultipleBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("endswith", "?name1,?name2,?name3", "?fragment", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "Freddy");
		result.setBinding("?name2", "Samuel");
		result.setBinding("?name3", "Joseph");

		result.setBinding("?fragment", "eddy");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "eph");
		assertTrue(where.matches(result));

		result.setBinding("?fragment", "ard");
		assertFalse(where.matches(result));
	}

}
