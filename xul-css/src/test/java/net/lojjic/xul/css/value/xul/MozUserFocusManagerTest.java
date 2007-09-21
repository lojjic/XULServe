package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozUserFocusManager}
 */
public class MozUserFocusManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_USER_FOCUS_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testInherit() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, "inherit").getStringValue());
	}

	public void testNormal() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, "normal").getStringValue());
	}

	public void testIgnore() throws Exception {
		assertEquals("ignore", parseAndComputeStyle(prop, "ignore").getStringValue());
	}

}