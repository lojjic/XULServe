package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBoxFlexManager}
 */
public class MozBoxFlexManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_BOX_FLEX_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals(1f, parseAndComputeStyle(prop, null).getFloatValue());
	}

	public void testInteger() throws Exception {
		assertEquals(5f, parseAndComputeStyle(prop, "5").getFloatValue());
	}

	public void testDecimal() throws Exception {
		assertEquals(6.7f, parseAndComputeStyle(prop, "6.7").getFloatValue());
	}

}