package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.value.css2.CSS2ValueManagerTestBase;

/**
 * Unit test for {@link ClearManager}
 */
public class ClearManagerTest extends CSS2ValueManagerTestBase {

	private static String prop = "clear";

	public void testDefault() throws Exception {
		assertEquals("none", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testLeft() throws Exception {
		assertEquals("left", parseAndComputeStyle(prop, "left").getStringValue());
	}

	public void testRight() throws Exception {
		assertEquals("right", parseAndComputeStyle(prop, "right").getStringValue());
	}

	public void testBoth() throws Exception {
		assertEquals("both", parseAndComputeStyle(prop, "both").getStringValue());
	}

	public void testNone() throws Exception {
		assertEquals("none", parseAndComputeStyle(prop, "none").getStringValue());
	}

}
