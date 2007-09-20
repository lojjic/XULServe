package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSEngine;
import net.lojjic.xul.css.value.ValueManagerTestBase;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.parser.ExtendedParser;
import org.w3c.dom.Document;

import java.net.URL;

/**
 * Base class for ValueManager unit tests.
 */
public abstract class XULValueManagerTestBase extends ValueManagerTestBase {

	/**
	 * Create the XUL css engine
	 */
	protected CSSEngine getCSSEngine(Document doc, URL uri, ExtendedParser parser, CSSContext context) {
		return new XULCSSEngine(doc, uri, parser, context);
	}

}