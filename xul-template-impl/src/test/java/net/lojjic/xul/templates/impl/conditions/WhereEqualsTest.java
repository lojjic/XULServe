package net.lojjic.xul.templates.impl.conditions;

import junit.framework.TestCase;

/**
 * Unit tests for {@link WhereEquals}
 */
public class WhereEqualsTest extends TestCase {

	public void testBasicSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name", "Freddy", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertFalse(where.matches(result));
	}

	public void testBasicValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "Freddy", "?name", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertFalse(where.matches(result));
	}

	public void testBasicBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name1", "?name2", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "Freddy");
		result.setBinding("?name2", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name2", "Samuel");
		assertFalse(where.matches(result));
	}

	public void testNegatedSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name", "Freddy", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertFalse(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertTrue(where.matches(result));
	}

	public void testNegatedValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "Freddy", "?name", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertFalse(where.matches(result));

		result.setBinding("?name", "Samuel");
		assertTrue(where.matches(result));
	}

	public void testNegatedBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name1", "?name2", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "Freddy");
		result.setBinding("?name2", "Freddy");
		assertFalse(where.matches(result));

		result.setBinding("?name2", "Samuel");
		assertTrue(where.matches(result));
	}

	public void testIgnoreCaseSubjectVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("equals", "?name", "Freddy", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("equals", "?name", "Freddy", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "fREdDy");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseValueVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("equals", "Freddy", "?name", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("equals", "Freddy", "?name", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "FrEdDY");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testIgnoreCaseBothVar() throws Exception {
		Where whereSensitive = Where.newInstance(new MockWhereElement("equals", "?name1", "?name2", false, false, false));
		Where whereInsensitive = Where.newInstance(new MockWhereElement("equals", "?name1", "?name2", false, false, true));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "fREdDy");
		result.setBinding("?name2", "FrEdDY");

		assertFalse(whereSensitive.matches(result));
		assertTrue(whereInsensitive.matches(result));
	}

	public void testMultipleSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name", "Freddy,Samuel,Joseph", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Samuel");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Richard");
		assertFalse(where.matches(result));
	}

	public void testMultipleValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "Freddy,Samuel,Joseph", "?name", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Joseph");
		assertTrue(where.matches(result));

		result.setBinding("?name", "Richard");
		assertFalse(where.matches(result));
	}

	public void testMultipleBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("equals", "?name1,?name2,?name3", "?name4", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?name1", "Freddy");
		result.setBinding("?name2", "Samuel");
		result.setBinding("?name3", "Joseph");

		result.setBinding("?name4", "Freddy");
		assertTrue(where.matches(result));

		result.setBinding("?name4", "Joseph");
		assertTrue(where.matches(result));

		result.setBinding("?name4", "Richard");
		assertFalse(where.matches(result));
	}

}
