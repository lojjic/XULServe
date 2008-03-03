package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Engine;
import net.lojjic.xul.css.value.ValueManagerTestBase;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.parser.ExtendedParser;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Document;

import java.net.URL;

/**
 * Base class for ValueManager unit tests.
 */
public abstract class CSS2ValueManagerTestBase extends ValueManagerTestBase {

	protected CSSEngine getCSSEngine(Document doc, ParsedURL url, ExtendedParser parser, CSSContext context) {
		return new CSS2Engine(doc, url, parser, context);
	}

}
