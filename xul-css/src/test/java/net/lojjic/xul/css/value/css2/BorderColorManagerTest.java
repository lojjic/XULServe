package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.Value;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.css2.BorderColorManager}
 */
public class BorderColorManagerTest extends CSS2ValueManagerTestBase {

	public void testTopDefault() throws Exception {
		// default value is that of the 'color' property
		assertEquals("rgb(0, 0, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_TOP_COLOR_PROPERTY, null).getCssText());
		assertEquals("rgb(0, 255, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_TOP_COLOR_PROPERTY, null, "color:#0F0").getCssText());
	}

	public void testRightDefault() throws Exception {
		// default value is that of the 'color' property
		assertEquals("rgb(0, 0, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_RIGHT_COLOR_PROPERTY, null).getCssText());
		assertEquals("rgb(0, 255, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_RIGHT_COLOR_PROPERTY, null, "color:#0F0").getCssText());
	}

	public void testBottomDefault() throws Exception {
		// default value is that of the 'color' property
		assertEquals("rgb(0, 0, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_BOTTOM_COLOR_PROPERTY, null).getCssText());
		assertEquals("rgb(0, 255, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_BOTTOM_COLOR_PROPERTY, null, "color:#0F0").getCssText());
	}

	public void testLeftDefault() throws Exception {
		// default value is that of the 'color' property
		assertEquals("rgb(0, 0, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_LEFT_COLOR_PROPERTY, null).getCssText());
		assertEquals("rgb(0, 255, 0)", parseAndComputeStyle(CSS2Constants.CSS_BORDER_LEFT_COLOR_PROPERTY, null, "color:#0F0").getCssText());
	}

	// TODO test colors

}
