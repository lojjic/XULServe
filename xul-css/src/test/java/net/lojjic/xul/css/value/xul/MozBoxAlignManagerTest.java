package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBoxAlignManager}
 */
public class MozBoxAlignManagerTest extends XULValueManagerTestBase {

	private static String prop = XULCSSConstants.CSS_MOZ_BOX_ALIGN_PROPERTY;

	public void testDefault() throws Exception {
		assertEquals("stretch", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testBaseline() throws Exception {
		assertEquals("baseline", parseAndComputeStyle(prop, "baseline").getStringValue());
	}

	public void testCenter() throws Exception {
		assertEquals("center", parseAndComputeStyle(prop, "center").getStringValue());
	}

	public void testEnd() throws Exception {
		assertEquals("end", parseAndComputeStyle(prop, "end").getStringValue());
	}

	public void testStart() throws Exception {
		assertEquals("start", parseAndComputeStyle(prop, "start").getStringValue());
	}

	public void testStretch() throws Exception {
		assertEquals("stretch", parseAndComputeStyle(prop, "stretch").getStringValue());
	}

}