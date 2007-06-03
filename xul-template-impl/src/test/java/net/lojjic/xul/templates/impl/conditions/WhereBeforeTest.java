package net.lojjic.xul.templates.impl.conditions;

import junit.framework.TestCase;

/**
 * Unit tests for {@link WhereBefore}
 */
public class WhereBeforeTest extends TestCase {

	public void testBasicSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str", "js", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "css");
		assertTrue(where.matches(result));

		result.setBinding("?str", "xul");
		assertFalse(where.matches(result));
	}

	public void testBasicValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "js", "?str", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "xul");
		assertTrue(where.matches(result));

		result.setBinding("?str", "css");
		assertFalse(where.matches(result));
	}

	public void testBasicBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str1", "?str2", false, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str1", "js");
		result.setBinding("?str2", "xul");
		assertTrue(where.matches(result));

		result.setBinding("?str2", "css");
		assertFalse(where.matches(result));
	}

	public void testNegatedSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str", "js", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "css");
		assertFalse(where.matches(result));

		result.setBinding("?str", "xul");
		assertTrue(where.matches(result));
	}

	public void testNegatedValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "js", "?str", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "xul");
		assertFalse(where.matches(result));

		result.setBinding("?str", "css");
		assertTrue(where.matches(result));
	}

	public void testNegatedBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str1", "?str2", true, false, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str1", "js");
		result.setBinding("?str2", "xul");
		assertFalse(where.matches(result));

		result.setBinding("?str2", "css");
		assertTrue(where.matches(result));
	}

	public void testMultipleSubjectVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str", "html,js,xhtml", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "css");
		assertTrue(where.matches(result));

		result.setBinding("?str", "svg");
		assertTrue(where.matches(result));

		result.setBinding("?str", "xul");
		assertFalse(where.matches(result));
	}

	public void testMultipleValueVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "html,js,xhtml", "?str", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str", "xul");
		assertTrue(where.matches(result));

		result.setBinding("?str", "svg");
		assertTrue(where.matches(result));

		result.setBinding("?str", "css");
		assertFalse(where.matches(result));
	}

	public void testMultipleBothVar() throws Exception {
		Where where = Where.newInstance(new MockWhereElement("before", "?str1,?str2,?str3", "?str4", false, true, false));

		MockXULTemplateResult result = new MockXULTemplateResult();
		result.setBinding("?str1", "html");
		result.setBinding("?str2", "js");
		result.setBinding("?str3", "xhtml");

		result.setBinding("?str4", "xul");
		assertTrue(where.matches(result));

		result.setBinding("?str4", "svg");
		assertTrue(where.matches(result));

		result.setBinding("?str4", "css");
		assertFalse(where.matches(result));
	}

}
