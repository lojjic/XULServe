package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for -moz-border-radius shorthand property
 */
public class MozBorderRadiusShorthandManagerTest extends XULValueManagerTestBase {

	private static String xml = "<root><test>content</test></root>";
	private static String xpath = "/root/test";

	public void testDefault() throws Exception {
		String css = "test {}";
		assertEquals(0f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(0f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(0f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(0f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testOneValuePixels() throws Exception {
		String css = "test {-moz-border-radius:123px;}";
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testTwoValuesPixels() throws Exception {
		String css = "test {-moz-border-radius:123px 321px;}";
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(321f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(321f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testThreeValuesPixels() throws Exception {
		String css = "test {-moz-border-radius:123px 321px 456px;}";
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(321f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(456f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(321f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testFourValuesPixels() throws Exception {
		String css = "test {-moz-border-radius:123px 321px 456px 654px;}";
		assertEquals(123f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(321f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(456f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(654f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testOneValuePercentage() throws Exception {
		String css = "test {-moz-border-radius:15%;}";
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testTwoValuesPercentage() throws Exception {
		String css = "test {-moz-border-radius:15% 35%;}";
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(350f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(350f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testThreeValuesPercentage() throws Exception {
		String css = "test {-moz-border-radius:15% 35% 57%;}";
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(350f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(570f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(350f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

	public void testFourValuesPercentage() throws Exception {
		String css = "test {-moz-border-radius:15% 35% 57% 91%;}";
		assertEquals(150f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY).getFloatValue());
		assertEquals(350f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY).getFloatValue());
		assertEquals(570f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY).getFloatValue());
		assertEquals(910f, parseAndComputeStyle(xml, css, xpath,
				XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY).getFloatValue());
	}

}
