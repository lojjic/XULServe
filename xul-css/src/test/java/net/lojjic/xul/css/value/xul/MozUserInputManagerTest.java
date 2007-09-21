package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link MozUserInputManager}
 */
public class MozUserInputManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_USER_INPUT_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("disabled", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testInherit() throws Exception {
		assertEquals("disabled", parseAndComputeStyle(prop, "inherit").getStringValue());
	}

	public void testDisabled() throws Exception {
		assertEquals("disabled", parseAndComputeStyle(prop, "disabled").getStringValue());
	}

	public void testEnabled() throws Exception {
		assertEquals("enabled", parseAndComputeStyle(prop, "enabled").getStringValue());
	}

}