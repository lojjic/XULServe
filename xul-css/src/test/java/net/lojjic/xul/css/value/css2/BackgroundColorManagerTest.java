package net.lojjic.xul.css.value.css2;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.css2.BackgroundColorManager}
 */
public class BackgroundColorManagerTest extends CSS2ValueManagerTestBase {

	private static String prop = "background-color";

	public void testDefault() throws Exception {
		assertEquals("transparent", parseAndComputeStyle(prop, null).getStringValue());
	}

	// TODO test colors

}

