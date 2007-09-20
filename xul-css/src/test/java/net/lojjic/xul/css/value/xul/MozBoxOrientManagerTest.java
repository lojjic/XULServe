package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.value.css2.CSS2ValueManagerTestBase;

/**
 * Unit tests for {@link MozBindingManager}
 */
public class MozBoxOrientManagerTest extends XULValueManagerTestBase {

	private static String prop = "-moz-box-orient";

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