package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.value.css2.CSS2ValueManagerTestBase;

/**
 * Unit tests for {@link net.lojjic.xul.css.value.xul.MozBindingManager}
 */
public class MozBindingManagerTest extends XULValueManagerTestBase {

	private static String xml = "<root><test>content</test></root>";
	private static String xpath = "/root/test";
	private static String prop = "-moz-binding";

	public void testDefault() throws Exception {
		assertEquals("none", parseAndComputeStyle(xml, "test {}", xpath, prop).getStringValue());
	}

	public void testNone() throws Exception {
		assertEquals("none", parseAndComputeStyle(xml, "test {-moz-binding: none;}", xpath, prop).getStringValue());
	}

	public void testURI() throws Exception {
		String uri = "http://lojjic.net/test/foo#bar";
		assertEquals(uri, parseAndComputeStyle(xml, "test {-moz-binding: url(" + uri + ")}", xpath, prop).getStringValue());
	}

}
