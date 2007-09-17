package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.value.css2.CSS2ValueManagerTestBase;

/**
 * Unit test for {@link ClearManager}
 */
public class ClearManagerTest extends CSS2ValueManagerTestBase {

	private static String xml = "<root><test>content</test></root>";
	private static String xpath = "/root/test";
	private static String prop = "clear";

	public void testDefault() throws Exception {
		assertEquals("none", parseAndComputeStyle(xml, "test {}", xpath, prop).getStringValue());
	}

	public void testLeft() throws Exception {
		assertEquals("left", parseAndComputeStyle(xml, "test {clear:left;}", xpath, prop).getStringValue());
	}

	public void testRight() throws Exception {
		assertEquals("right", parseAndComputeStyle(xml, "test {clear:right;}", xpath, prop).getStringValue());
	}

	public void testBoth() throws Exception {
		assertEquals("both", parseAndComputeStyle(xml, "test {clear:both;}", xpath, prop).getStringValue());
	}

	public void testNone() throws Exception {
		assertEquals("none", parseAndComputeStyle(xml, "test {clear:none;}", xpath, prop).getStringValue());
	}

}
