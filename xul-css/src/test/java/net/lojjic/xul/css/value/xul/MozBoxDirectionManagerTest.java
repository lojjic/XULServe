package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link MozBoxDirectionManager}
 */
public class MozBoxDirectionManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_BOX_DIRECTION_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testNormal() throws Exception {
		assertEquals("normal", parseAndComputeStyle(prop, "normal").getStringValue());
	}

	public void testReverse() throws Exception {
		assertEquals("reverse", parseAndComputeStyle(prop, "reverse").getStringValue());
	}

}