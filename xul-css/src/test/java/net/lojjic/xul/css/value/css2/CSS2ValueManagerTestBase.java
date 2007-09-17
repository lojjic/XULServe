package net.lojjic.xul.css.value.css2;

import junit.framework.TestCase;
import net.lojjic.xul.css.CSS2Engine;
import net.lojjic.xul.css.value.CSSContextTestImpl;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleSheet;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.parser.ExtendedParserWrapper;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.net.URL;

import com.steadystate.css.parser.SACParserCSS21;

/**
 * Base class for ValueManager unit tests.
 */
public abstract class CSS2ValueManagerTestBase extends TestCase {

	/**
	 * Parse an XML document, apply CSS to it, and return a computed style result.
	 *
	 * @param xml - The XML to be parsed
	 * @param css - The CSS styles to be applied
	 * @param xpath - An XPath expression to get the XML node whose style to test
	 * @param prop - The CSS property name whose computed value to return
	 * @return Computed CSS value
	 */
	protected Value parseAndComputeStyle(String xml, String css, String xpath, String prop) throws Exception {
		URL uri = new URL("http://domain.com/test");

		Document doc = parseXML(xml);
		CSSStylableElement elt = (CSSStylableElement) XPathFactory.newInstance().newXPath().evaluate(xpath, doc, XPathConstants.NODE);

		CSSContextTestImpl context = new CSSContextTestImpl();
		CSS2Engine engine = new CSS2Engine(doc, uri, new ExtendedParserWrapper(new SACParserCSS21()), context);
		context.setEngine(engine);
		StyleSheet styleSheet = engine.parseStyleSheet(css, uri, "all");
		engine.setUserAgentStyleSheet(styleSheet);
		return engine.getComputedStyle(elt, null, engine.getPropertyIndex(prop));
	}

	protected Document parseXML(String xml) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setAttribute("http://apache.org/xml/properties/dom/document-class-name", "net.lojjic.xul.css.StylableDocumentImpl");
		DocumentBuilder builder = dbf.newDocumentBuilder();
		return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}

}
