package net.lojjic.xul.css.value.css2;

/**
 * Unit tests for {@link BackgroundAttachmentManager}
 */
public class BackgroundAttachmentManagerTest extends CSS2ValueManagerTestBase {

	private static String xml = "<root><test>content</test></root>";
	private static String xpath = "/root/test";
	private static String prop = "background-attachment";

	public void testDefault() throws Exception {
		assertEquals("scroll", parseAndComputeStyle(xml, "test {}", xpath, prop).getStringValue());
	}

	public void testScroll() throws Exception {
		assertEquals("scroll", parseAndComputeStyle(xml, "test {background-attachment: scroll;}", xpath, prop).getStringValue());
	}

	public void testFixed() throws Exception {
		assertEquals("fixed", parseAndComputeStyle(xml, "test {background-attachment: fixed;}", xpath, prop).getStringValue());
	}

}
