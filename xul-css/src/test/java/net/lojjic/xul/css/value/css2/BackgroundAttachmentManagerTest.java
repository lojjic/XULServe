package net.lojjic.xul.css.value.css2;

/**
 * Unit tests for {@link BackgroundAttachmentManager}
 */
public class BackgroundAttachmentManagerTest extends CSS2ValueManagerTestBase {

	private static String prop = "background-attachment";

	public void testDefault() throws Exception {
		assertEquals("scroll", parseAndComputeStyle(prop, null).getStringValue());
	}

	public void testScroll() throws Exception {
		assertEquals("scroll", parseAndComputeStyle(prop, "scroll").getStringValue());
	}

	public void testFixed() throws Exception {
		assertEquals("fixed", parseAndComputeStyle(prop, "fixed").getStringValue());
	}

}
