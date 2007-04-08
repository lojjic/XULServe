package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.CSSStyleDeclaration;

import java.util.HashMap;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSStyleDeclaration}
 */
@JSClassName("CSSStyleDeclaration")
public class ScriptableCSSStyleDeclaration<T extends CSSStyleDeclaration> extends ScriptableDOMObject<T> {

	public ScriptableCSSStyleDeclaration() {
	}

	public ScriptableCSSStyleDeclaration(Scriptable scope, T cssStyleDeclaration) {
		super(scope, cssStyleDeclaration);
	}

	@JSGetter("cssText")
	public String getCssText() {
		return unwrap().getCssText();
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSGetter("parentRule")
	public Object getParentRule() {
		return convertReturnValue(unwrap().getParentRule());
	}

	@JSFunction("getPropertyCSSValue")
	public Object getPropertyCSSValue(String propertyName) {
		return convertReturnValue(unwrap().getPropertyCSSValue(propertyName));
	}

	@JSFunction("getPropertyPriority")
	public String getPropertyPriority(String propertyName) {
		return unwrap().getPropertyPriority(propertyName);
	}

	@JSFunction("getPropertyValue")
	public String getPropertyValue(String propertyName) {
		return unwrap().getPropertyValue(propertyName);
	}

	@JSFunction("item")
	public String item(int index) {
		return unwrap().item(index);
	}

	@JSFunction("removeProperty")
	public String removeProperty(String propertyName) {
		return unwrap().removeProperty(propertyName);
	}

	@JSSetter("cssText")
	public void setCssText(String cssText) {
		unwrap().setCssText(cssText);
	}

	@JSFunction("setProperty")
	public void setProperty(String propertyName, String value, String priority) {
		unwrap().setProperty(propertyName, value, priority);
	}

	/**
	 * Indexed properties are a shortcut to {@link #item(int)}
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}


	/**
	 * Override named getter so that CSS2 properties return the property value.
	 * This sort of implements the getters from {@link org.w3c.dom.css.CSS2Properties}.
	 */
	@Override
	public Object get(String name, Scriptable start) {
		String cssPropertyName = css2Properties.get(name);
		if(cssPropertyName != null) {
			return getPropertyValue(cssPropertyName);
		}
		return super.get(name, start);
	}

	/**
	 * Override named getter so that CSS2 properties set the property value.
	 * This sort of implements the setters from {@link org.w3c.dom.css.CSS2Properties}.
	 */
	@Override
	public void put(String name, Scriptable start, Object value) {
		String cssPropertyName = css2Properties.get(name);
		if(cssPropertyName != null) {
			setProperty(cssPropertyName, convertArg(value, String.class), "");
			return;
		}
		super.put(name, start, value);
	}


	/**
	 * Mapping of CSS2 JS property names to CSS property names. This is used to
	 * implement the property getters/setters from {@link org.w3c.dom.css.CSS2Properties}.
	 */
	private static HashMap<String,String> css2Properties = new HashMap<String,String>();
	static {
		css2Properties.put("azimuth", "azimuth");
		css2Properties.put("background", "background");
		css2Properties.put("backgroundAttachment", "background-attachment");
		css2Properties.put("backgroundColor", "background-color");
		css2Properties.put("backgroundImage", "background-image");
		css2Properties.put("backgroundPosition", "background-position");
		css2Properties.put("backgroundRepeat", "background-repeat");
		css2Properties.put("border", "border");
		css2Properties.put("borderCollapse", "border-collapse");
		css2Properties.put("borderColor", "border-color");
		css2Properties.put("borderSpacing", "border-spacing");
		css2Properties.put("borderStyle", "border-style");
		css2Properties.put("borderTop", "border-top");
		css2Properties.put("borderRight", "border-right");
		css2Properties.put("borderBottom", "border-bottom");
		css2Properties.put("borderLeft", "border-left");
		css2Properties.put("borderTopColor", "border-top-color");
		css2Properties.put("borderRightColor", "border-right-color");
		css2Properties.put("borderBottomColor", "border-bottom-color");
		css2Properties.put("borderLeftColor", "border-left-color");
		css2Properties.put("borderTopStyle", "border-top-style");
		css2Properties.put("borderRightStyle", "border-right-style");
		css2Properties.put("borderBottomStyle", "border-bottom-style");
		css2Properties.put("borderLeftStyle", "border-left-style");
		css2Properties.put("borderTopWidth", "border-top-width");
		css2Properties.put("borderRightWidth", "border-right-width");
		css2Properties.put("borderBottomWidth", "border-bottom-width");
		css2Properties.put("borderLeftWidth", "border-left-width");
		css2Properties.put("borderWidth", "border-width");
		css2Properties.put("bottom", "bottom");
		css2Properties.put("captionSide", "caption-side");
		css2Properties.put("clear", "clear");
		css2Properties.put("clip", "clip");
		css2Properties.put("color", "color");
		css2Properties.put("content", "content");
		css2Properties.put("counterIncrement", "counter-increment");
		css2Properties.put("counterReset", "counter-reset");
		css2Properties.put("cue", "cue");
		css2Properties.put("cueAfter", "cue-after");
		css2Properties.put("cueBefore", "cur-before");
		css2Properties.put("cursor", "cursor");
		css2Properties.put("direction", "direction");
		css2Properties.put("display", "display");
		css2Properties.put("elevation", "elevation");
		css2Properties.put("emptyCells", "empty-cells");
		css2Properties.put("cssFloat", "float");
		css2Properties.put("font", "font");
		css2Properties.put("fontFamily", "font-family");
		css2Properties.put("fontSize", "font-size");
		css2Properties.put("fontSizeAdjust", "font-size-adjust");
		css2Properties.put("fontStretch", "font-stretch");
		css2Properties.put("fontStyle", "font-style");
		css2Properties.put("fontVariant", "font-variant");
		css2Properties.put("fontWeight", "font-weight");
		css2Properties.put("height", "height");
		css2Properties.put("left", "left");
		css2Properties.put("letterSpacing", "letter-spacing");
		css2Properties.put("lineHeight", "line-height");
		css2Properties.put("listStyle", "list-style");
		css2Properties.put("listStyleImage", "list-style-image");
		css2Properties.put("listStylePosition", "list-style-position");
		css2Properties.put("listStyleType", "list-style-type");
		css2Properties.put("margin", "margin");
		css2Properties.put("marginTop", "margin-top");
		css2Properties.put("marginRight", "margin-right");
		css2Properties.put("marginBottom", "margin-bottom");
		css2Properties.put("marginLeft", "margin-left");
		css2Properties.put("markerOffset", "marker-offset");
		css2Properties.put("marks", "marks");
		css2Properties.put("maxHeight", "max-height");
		css2Properties.put("maxWidth", "max-width");
		css2Properties.put("minHeight", "min-height");
		css2Properties.put("minWidth", "min-width");
		css2Properties.put("orphans", "orphans");
		css2Properties.put("outline", "outline");
		css2Properties.put("outlineColor", "outline-color");
		css2Properties.put("outlineStyle", "outline-style");
		css2Properties.put("outlineWidth", "outline-width");
		css2Properties.put("overflow", "overflow");
		css2Properties.put("padding", "padding");
		css2Properties.put("paddingTop", "padding-top");
		css2Properties.put("paddingRight", "padding-right");
		css2Properties.put("paddingBottom", "padding-bottom");
		css2Properties.put("paddingLeft", "padding-left");
		css2Properties.put("page", "page");
		css2Properties.put("pageBreakAfter", "page-break-after");
		css2Properties.put("pageBreakBefore", "page-break-before");
		css2Properties.put("pageBreakInside", "page-break-inside");
		css2Properties.put("pause", "pause");
		css2Properties.put("pauseAfter", "pause-after");
		css2Properties.put("pauseBefore", "pause-before");
		css2Properties.put("pitch", "pitch");
		css2Properties.put("pitchRange", "pitch-range");
		css2Properties.put("playDuring", "play-during");
		css2Properties.put("position", "position");
		css2Properties.put("quotes", "quotes");
		css2Properties.put("richness", "richness");
		css2Properties.put("right", "right");
		css2Properties.put("size", "size");
		css2Properties.put("speak", "speak");
		css2Properties.put("speakHeader", "speak-header");
		css2Properties.put("speakNumeral", "speak-numeral");
		css2Properties.put("speakPunctuation", "speak-punctuation");
		css2Properties.put("speechRate", "speech-rate");
		css2Properties.put("stress", "stress");
		css2Properties.put("tableLayout", "table-layout");
		css2Properties.put("textAlign", "text-align");
		css2Properties.put("textDecoration", "text-decoration");
		css2Properties.put("textIndent", "text-indent");
		css2Properties.put("textShadow", "text-shadow");
		css2Properties.put("textTransform", "text-transform");
		css2Properties.put("top", "top");
		css2Properties.put("unicodeBidi", "unicode-bidi");
		css2Properties.put("verticalAlign", "vertical-align");
		css2Properties.put("visibility", "visibility");
		css2Properties.put("voiceFamily", "voice-family");
		css2Properties.put("volume", "volume");
		css2Properties.put("whiteSpace", "white-space");
		css2Properties.put("widows", "widows");
		css2Properties.put("width", "width");
		css2Properties.put("wordSpacing", "word-spacing");
		css2Properties.put("zIndex", "z-index");
	}
}
