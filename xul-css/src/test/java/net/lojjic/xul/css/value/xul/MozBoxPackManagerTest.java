package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBoxPackManager}
 */
public class MozBoxPackManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_BOX_PACK_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("start", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testStart() throws Exception {
		assertEquals("start", parseAndComputeStyle(prop, "start").getStringValue());
	}

	public void testCenter() throws Exception {
		assertEquals("center", parseAndComputeStyle(prop, "center").getStringValue());
	}

	public void testEnd() throws Exception {
		assertEquals("end", parseAndComputeStyle(prop, "end").getStringValue());
	}

}