package net.lojjic.xul.css;

import org.w3c.dom.Document;
import org.apache.batik.css.parser.ExtendedParser;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.util.ParsedURL;

import java.net.URL;

import net.lojjic.xul.css.value.xul.*;

/**
 * A {@link org.apache.batik.css.engine.CSSEngine} implementation for managing
 * XUL-specific CSS properties and values.
 */
public class XULCSSEngine extends CSS2Engine {

	protected XULCSSEngine(Document doc, ParsedURL uri, ExtendedParser p, ValueManager[] vm, ShorthandManager[] sm,
	                        String[] pe, String sns, String sln, String cns, String cln, boolean hints,
	                        String hintsNS, CSSContext ctx) {
		super(doc, uri, p, mergeArrays(xulValueManagers, vm), mergeArrays(xulShorthandManagers, sm),
				mergeArrays(xulPseudoElements, pe), sns, sln, cns, cln, hints, hintsNS, ctx);
	}

	public XULCSSEngine(Document doc, ParsedURL uri, ExtendedParser p, CSSContext ctx) {
		super(doc, uri, p, xulValueManagers, xulShorthandManagers, xulPseudoElements,
				null, "style", null, "class", false, null, ctx);
	}

	private static final ValueManager[] xulValueManagers = {
			new MozBindingManager(),
			new MozBorderRadiusLengthManager(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY),
			new MozBorderRadiusLengthManager(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY),
			new MozBorderRadiusLengthManager(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY),
			new MozBorderRadiusLengthManager(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY),
			new MozBoxAlignManager(),
			new MozBoxDirectionManager(),
			new MozBoxFlexManager(),
			new MozBoxOrdinalGroupManager(),
			new MozBoxOrientManager(),
			new MozBoxPackManager(),
			new MozOpacityManager(),
			new MozUserFocusManager(),
			new MozUserInputManager(),
			new MozUserSelectManager(),
			new XULDisplayManager()
	};

	private static final ShorthandManager[] xulShorthandManagers = {
			new MozBorderRadiusShorthandManager()
	};

	private static final String[] xulPseudoElements = {

	};

}
