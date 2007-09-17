package net.lojjic.xul.css;

import net.lojjic.xul.css.value.css2.*;
import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.css.engine.value.css2.*;
import org.apache.batik.css.engine.value.svg.ColorManager;
import org.apache.batik.css.engine.value.svg12.LineHeightManager;
import org.apache.batik.css.engine.value.svg12.MarginLengthManager;
import org.apache.batik.css.engine.value.svg12.MarginShorthandManager;
import org.apache.batik.css.parser.ExtendedParser;
import org.apache.batik.util.SVG12CSSConstants;
import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.net.URL;

/**
 * CSSEngine implementation for CSS Level 2
 */
public class CSS2Engine extends CSSEngine {

	protected CSS2Engine(Document doc, URL uri, ExtendedParser p, ValueManager[] vm, ShorthandManager[] sm,
	                        String[] pe, String sns, String sln, String cns, String cln, boolean hints,
	                        String hintsNS, CSSContext ctx) {
		super(doc, uri, p, mergeArrays(css2ValueManagers, vm), mergeArrays(css2ShorthandManagers, sm),
				mergeArrays(css2PseudoElements, pe), sns, sln, cns, cln, hints, hintsNS, ctx);
	}

	public CSS2Engine(Document doc, URL uri, ExtendedParser p, CSSContext ctx) {
		super(doc, uri, p, css2ValueManagers, css2ShorthandManagers, css2PseudoElements,
				null, "style", null, "class", false, null, ctx);
	}


	/**
	 * Merge two typed arrays into a single array of the same type.
	 */
	protected static <T> T[] mergeArrays(T[] arr1, T[] arr2) {
		T[] merged = (T[])Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);
		System.arraycopy(arr1, 0, merged, 0, arr1.length);
		System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
		return merged;
	}


	private static final ValueManager[] css2ValueManagers = {
			// Borrowed from Batik css package:
			new ClipManager(),
			new CursorManager(),
			new DirectionManager(),
			new DisplayManager(),
			new FontFamilyManager(),
			new FontSizeManager(),
			new FontStretchManager(),
			new FontStyleManager(),
			new FontVariantManager(),
			new FontWeightManager(),
			new OverflowManager(),
			new SrcManager(),
			new TextDecorationManager(),
			new UnicodeBidiManager(),
			new VisibilityManager(),

			// Borrowed from Batik svg package:
			new ColorManager(),

			// Borrowed from Batik svg12 package:
			new LineHeightManager(),
			new MarginLengthManager(SVG12CSSConstants.CSS_MARGIN_BOTTOM_PROPERTY),
			new MarginLengthManager(SVG12CSSConstants.CSS_MARGIN_LEFT_PROPERTY),
			new MarginLengthManager(SVG12CSSConstants.CSS_MARGIN_RIGHT_PROPERTY),
			new MarginLengthManager(SVG12CSSConstants.CSS_MARGIN_TOP_PROPERTY),

			// Defined in local css2 package:
			new BackgroundAttachmentManager(),
			new BackgroundColorManager(),
			new BackgroundImageManager(),
			new BackgroundPositionManager(),
			new BackgroundRepeatManager(),
			new BorderCollapseManager(),
			new BorderColorManager(CSS2Constants.CSS_BORDER_TOP_COLOR_PROPERTY),
			new BorderColorManager(CSS2Constants.CSS_BORDER_RIGHT_COLOR_PROPERTY),
			new BorderColorManager(CSS2Constants.CSS_BORDER_BOTTOM_COLOR_PROPERTY),
			new BorderColorManager(CSS2Constants.CSS_BORDER_LEFT_COLOR_PROPERTY),
			new BorderSpacingManager(),
			new BorderStyleManager(CSS2Constants.CSS_BORDER_TOP_STYLE_PROPERTY),
			new BorderStyleManager(CSS2Constants.CSS_BORDER_RIGHT_STYLE_PROPERTY),
			new BorderStyleManager(CSS2Constants.CSS_BORDER_BOTTOM_STYLE_PROPERTY),
			new BorderStyleManager(CSS2Constants.CSS_BORDER_LEFT_STYLE_PROPERTY),
			new BorderWidthManager(CSS2Constants.CSS_BORDER_TOP_WIDTH_PROPERTY),
			new BorderWidthManager(CSS2Constants.CSS_BORDER_RIGHT_WIDTH_PROPERTY),
			new BorderWidthManager(CSS2Constants.CSS_BORDER_BOTTOM_WIDTH_PROPERTY),
			new BorderWidthManager(CSS2Constants.CSS_BORDER_LEFT_WIDTH_PROPERTY),
			new BottomManager(),
			new ClearManager(),
			new FloatManager(),
			new HeightManager(),
			new LeftManager(),
			new LetterSpacingManager(),
			new ListStyleImageManager(),
			new ListStylePositionManager(),
			new ListStyleTypeManager(),
			new MaxHeightManager(),
			new MaxWidthManager(),
			new MinHeightManager(),
			new MinWidthManager(),
			new PaddingLengthManager(CSS2Constants.CSS_PADDING_TOP_PROPERTY),
			new PaddingLengthManager(CSS2Constants.CSS_PADDING_RIGHT_PROPERTY),
			new PaddingLengthManager(CSS2Constants.CSS_PADDING_BOTTOM_PROPERTY),
			new PaddingLengthManager(CSS2Constants.CSS_PADDING_LEFT_PROPERTY),
			new PositionManager(),
			new RightManager(),
			new TextAlignManager(),
			new TextIndentManager(),
			new TextTransformManager(),
			new TopManager(),
			new VerticalAlignManager(),
			new WhiteSpaceManager(),
			new WidthManager(),
			new WordSpacingManager(),
			new ZIndexManager()
	};

	private static final ShorthandManager[] css2ShorthandManagers = {
			// Borrowed from Batik css package:
			new FontShorthandManager(),

			// Borrowed from Batik svg12 package:
			new MarginShorthandManager(),

			// Defined in local css2 package:
			new BackgroundShorthandManager(),
			new BorderColorShorthandManager(),
			new BorderStyleShorthandManager(),
			new BorderWidthShorthandManager(),
			new ListStyleShorthandManager(),
			new PaddingShorthandManager()
	};

	private static final String[] css2PseudoElements = {

	};
}
