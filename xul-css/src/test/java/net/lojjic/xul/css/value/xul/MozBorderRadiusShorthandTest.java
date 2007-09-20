package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;

/**
 * Unit tests for -moz-border-radius shorthand property
 */
public class MozBorderRadiusShorthandTest extends XULValueManagerTestBase {

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

	public void testOneValue() throws Exception {
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

	public void testTwoValues() throws Exception {
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

	public void testThreeValues() throws Exception {
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

	public void testFourValues() throws Exception {
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

}
