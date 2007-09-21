package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozOpacityManager}
 */
public class MozOpacityManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_OPACITY_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals(1f, parseAndComputeStyle(prop, null).getFloatValue());
	}

	public void testInherit() throws Exception {
		assertEquals(1f, parseAndComputeStyle(prop, "inherit").getFloatValue());
	}

	public void testZero() throws Exception {
		assertEquals(0f, parseAndComputeStyle(prop, "0").getFloatValue());
	}

	public void testOne() throws Exception {
		assertEquals(1f, parseAndComputeStyle(prop, "1").getFloatValue());
	}

	public void testFraction() throws Exception {
		assertEquals(0.579f, parseAndComputeStyle(prop, "0.579").getFloatValue());
	}

}