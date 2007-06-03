package net.lojjic.xul.templates.impl.conditions;

import junit.framework.TestCase;

/**
 * Unit tests for {@link WhereLess}
 */
public class WhereLessTest extends TestCase {

	public void testBasicSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num", "100", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "87");
		assertTrue(where.matches(result));

		result.setBinding("?num", "102");
		assertFalse(where.matches(result));
	}

	public void testBasicValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "100", "?num", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "102");
		assertTrue(where.matches(result));

		result.setBinding("?num", "87");
		assertFalse(where.matches(result));
	}

	public void testBasicBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num1", "?num2", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num1", "100");
		result.setBinding("?num2", "5890");
		assertTrue(where.matches(result));

		result.setBinding("?num2", "2");
		assertFalse(where.matches(result));
	}

	public void testNegatedSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num", "100", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "87");
		assertFalse(where.matches(result));

		result.setBinding("?num", "102");
		assertTrue(where.matches(result));
	}

	public void testNegatedValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "100", "?num", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "102");
		assertFalse(where.matches(result));

		result.setBinding("?num", "87");
		assertTrue(where.matches(result));
	}

	public void testNegatedBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num1", "?num2", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num1", "100");
		result.setBinding("?num2", "5890");
		assertFalse(where.matches(result));

		result.setBinding("?num2", "2");
		assertTrue(where.matches(result));
	}

	public void testMultipleSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num", "3,14,159", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "1");
		assertTrue(where.matches(result));

		result.setBinding("?num", "158");
		assertTrue(where.matches(result));

		result.setBinding("?num", "200");
		assertFalse(where.matches(result));
	}

	public void testMultipleValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "3,14,159", "?num", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num", "200");
		assertTrue(where.matches(result));

		result.setBinding("?num", "5");
		assertTrue(where.matches(result));

		result.setBinding("?num", "2");
		assertFalse(where.matches(result));
	}

	public void testMultipleBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("less", "?num1,?num2,?num3", "?num4", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?num1", "3");
		result.setBinding("?num2", "14");
		result.setBinding("?num3", "159");

		result.setBinding("?num4", "200");
		assertTrue(where.matches(result));

		result.setBinding("?num4", "5");
		assertTrue(where.matches(result));

		result.setBinding("?num4", "2");
		assertFalse(where.matches(result));
	}

}
