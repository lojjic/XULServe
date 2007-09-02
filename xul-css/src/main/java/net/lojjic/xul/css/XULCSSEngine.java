package net.lojjic.xul.css;

import org.w3c.dom.Document;
import org.apache.batik.css.parser.ExtendedParser;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.apache.batik.css.engine.CSSContext;

import java.net.URL;

/**
 * A {@link org.apache.batik.css.engine.CSSEngine} implementation for managing
 * XUL-specific CSS properties and values.
 */
public class XULCSSEngine extends CSS2Engine {

	/**
	 * Creates a new CSSEngine.
	 *
	 * @param doc     The associated document.
	 * @param uri     The document URI.
	 * @param p       The CSS parser.
	 * @param vm      The property value managers.
	 * @param sm      The shorthand properties managers.
	 * @param pe      The pseudo-element names supported by the associated
	 *                XML dialect. Must be null if no support for pseudo-
	 *                elements is required.
	 * @param sns     The namespace URI of the style attribute.
	 * @param sln     The local name of the style attribute.
	 * @param cns     The namespace URI of the class attribute.
	 * @param cln     The local name of the class attribute.
	 * @param hints   Whether the CSS engine should support non CSS
	 *                presentational hints.
	 * @param hintsNS The hints namespace URI.
	 * @param ctx     The CSS context.
	 */
	protected XULCSSEngine(Document doc, URL uri, ExtendedParser p, ValueManager[] vm, ShorthandManager[] sm, String[] pe, String sns, String sln, String cns, String cln, boolean hints, String hintsNS, CSSContext ctx) {
		super(doc, uri, p, vm, sm, pe, sns, sln, cns, cln, hints, hintsNS, ctx);
	}

}
