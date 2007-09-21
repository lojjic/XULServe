package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBorderRadiusLengthManager}
 */
public class MozBorderRadiusLengthManagerTest extends XULValueManagerTestBase {

	public void testTopLeftDefault() throws Exception {
		assertEquals(0f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY, null).getFloatValue());
	}

	public void testTopRightDefault() throws Exception {
		assertEquals(0f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY, null).getFloatValue());
	}

	public void testBottomRightDefault() throws Exception {
		assertEquals(0f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY, null).getFloatValue());
	}

	public void testBottomLeftDefault() throws Exception {
		assertEquals(0f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY, null).getFloatValue());
	}

	public void testTopLeftPixels() throws Exception {
		assertEquals(150f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY, "150px").getFloatValue());
	}

	public void testTopRightPixels() throws Exception {
		assertEquals(350f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY, "350px").getFloatValue());
	}

	public void testBottomRightPixels() throws Exception {
		assertEquals(570f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY, "570px").getFloatValue());
	}

	public void testBottomLeftPixels() throws Exception {
		assertEquals(910f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY, "910px").getFloatValue());
	}

	public void testTopLeftPercentage() throws Exception {
		assertEquals(150f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY, "15%").getFloatValue());
	}

	public void testTopRightPercentage() throws Exception {
		assertEquals(350f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY, "35%").getFloatValue());
	}

	public void testBottomRightPercentage() throws Exception {
		assertEquals(570f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY, "57%").getFloatValue());
	}

	public void testBottomLeftPercentage() throws Exception {
		assertEquals(910f, parseAndComputeStyle(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY, "91%").getFloatValue());
	}

}
