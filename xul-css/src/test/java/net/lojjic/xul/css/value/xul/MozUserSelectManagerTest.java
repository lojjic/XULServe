package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link MozUserSelectManager}
 */
public class MozUserSelectManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_USER_SELECT_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testInherit() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, "inherit").getStringValue());
	}

	public void testNormal() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, "normal").getStringValue());
	}

	public void testNone() throws Exception {
		assertEquals("none", parseAndComputeStyle(prop, "none").getStringValue());
	}

}