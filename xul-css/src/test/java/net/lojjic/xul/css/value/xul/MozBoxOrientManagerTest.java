package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.value.css2.CSS2ValueManagerTestBase;

/**
 * Unit tests for {@link MozBindingManager}
 */
public class MozBoxOrientManagerTest extends XULValueManagerTestBase {

	private static String xml = "<root><test>content</test></root>";
	private static String xpath = "/root/test";
	private static String prop = "-moz-box-orient";

	public void testDefault() throws Exception {
		assertEquals("horizontal", parseAndComputeStyle(xml, "test {}", xpath, prop).getStringValue());
	}

	public void testHorizontal() throws Exception {
		assertEquals("horizontal", parseAndComputeStyle(xml, "test {-moz-box-orient:horizontal;}", xpath, prop).getStringValue());
	}

	public void testVertical() throws Exception {
		assertEquals("vertical", parseAndComputeStyle(xml, "test {-moz-box-orient:vertical;}", xpath, prop).getStringValue());
	}

}