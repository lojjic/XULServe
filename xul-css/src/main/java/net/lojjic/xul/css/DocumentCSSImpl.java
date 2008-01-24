package net.lojjic.xul.css;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.css.DocumentCSS;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.stylesheets.StyleSheetList;
import org.w3c.dom.Element;

/**
 *
 */
public class DocumentCSSImpl extends DocumentImpl implements DocumentCSS {

	private CSSEngine cssEngine;

	public DocumentCSSImpl(CSSEngine cssEngine) {
		this.cssEngine = cssEngine;
	}

	public StyleSheetList getStyleSheets() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public CSSStyleDeclaration getOverrideStyle(Element elt, String pseudoElt) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
	
	public CSSEngine getCssEngine() {
		return cssEngine;
	}

}
