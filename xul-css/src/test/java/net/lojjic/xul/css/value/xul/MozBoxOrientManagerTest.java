package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBoxOrientManager}
 */
public class MozBoxOrientManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_BOX_ORIENT_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("horizontal", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testHorizontal() throws Exception {
		assertEquals("horizontal", parseAndComputeStyle(prop, "horizontal").getStringValue());
	}

	public void testVertical() throws Exception {
		assertEquals("vertical", parseAndComputeStyle(prop, "vertical").getStringValue());
	}

}