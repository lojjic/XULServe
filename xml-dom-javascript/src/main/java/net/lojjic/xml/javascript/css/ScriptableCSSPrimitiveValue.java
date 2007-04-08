package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.CSSPrimitiveValue}
 */
@JSClassName("CSSPrimitiveValue")
public class ScriptableCSSPrimitiveValue<T extends CSSPrimitiveValue> extends ScriptableCSSValue<T> {

	public ScriptableCSSPrimitiveValue() {
	}

	public ScriptableCSSPrimitiveValue(Scriptable scope, T cssValue) {
		super(scope, cssValue);
	}

	@JSStatic @JSGetter("CSS_UNKNOWN")
	public static int get_CSS_UNKNOWN(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_UNKNOWN;
	}

	@JSStatic @JSGetter("CSS_NUMBER")
	public static int get_CSS_NUMBER(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_NUMBER;
	}

	@JSStatic @JSGetter("CSS_PERCENTAGE")
	public static int get_CSS_PERCENTAGE(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_PERCENTAGE;
	}

	@JSStatic @JSGetter("CSS_EMS")
	public static int get_CSS_EMS(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_EMS;
	}

	@JSStatic @JSGetter("CSS_EXS")
	public static int get_CSS_EXS(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_EXS;
	}

	@JSStatic @JSGetter("CSS_PX")
	public static int get_CSS_PX(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_PX;
	}

	@JSStatic @JSGetter("CSS_CM")
	public static int get_CSS_CM(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_CM;
	}

	@JSStatic @JSGetter("CSS_MM")
	public static int get_CSS_MM(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_MM;
	}

	@JSStatic @JSGetter("CSS_IN")
	public static int get_CSS_IN(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_IN;
	}

	@JSStatic @JSGetter("CSS_PT")
	public static int get_CSS_PT(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_PT;
	}

	@JSStatic @JSGetter("CSS_PC")
	public static int get_CSS_PC(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_PC;
	}

	@JSStatic @JSGetter("CSS_DEG")
	public static int get_CSS_DEG(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_DEG;
	}

	@JSStatic @JSGetter("CSS_RAD")
	public static int get_CSS_RAD(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_RAD;
	}

	@JSStatic @JSGetter("CSS_GRAD")
	public static int get_CSS_GRAD(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_GRAD;
	}

	@JSStatic @JSGetter("CSS_MS")
	public static int get_CSS_MS(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_MS;
	}

	@JSStatic @JSGetter("CSS_S")
	public static int get_CSS_S(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_S;
	}

	@JSStatic @JSGetter("CSS_HZ")
	public static int get_CSS_HZ(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_HZ;
	}

	@JSStatic @JSGetter("CSS_KHZ")
	public static int get_CSS_KHZ(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_KHZ;
	}

	@JSStatic @JSGetter("CSS_DIMENSION")
	public static int get_CSS_DIMENSION(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_DIMENSION;
	}

	@JSStatic @JSGetter("CSS_STRING")
	public static int get_CSS_STRING(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_STRING;
	}

	@JSStatic @JSGetter("CSS_URI")
	public static int get_CSS_URI(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_URI;
	}

	@JSStatic @JSGetter("CSS_IDENT")
	public static int get_CSS_IDENT(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_IDENT;
	}

	@JSStatic @JSGetter("CSS_ATTR")
	public static int get_CSS_ATTR(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_ATTR;
	}

	@JSStatic @JSGetter("CSS_COUNTER")
	public static int get_CSS_COUNTER(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_COUNTER;
	}

	@JSStatic @JSGetter("CSS_RECT")
	public static int get_CSS_RECT(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_RECT;
	}

	@JSStatic @JSGetter("CSS_RGBCOLOR")
	public static int get_CSS_RGBCOLOR(ScriptableObject obj) {
		return CSSPrimitiveValue.CSS_RGBCOLOR;
	}

	@JSFunction("getCounterValue")
	public Object getCounterValue() {
		return convertReturnValue(unwrap().getCounterValue());
	}

	@JSFunction("getFloatValue")
	public float getFloatValue(short unitType) {
		return unwrap().getFloatValue(unitType);
	}

	@JSGetter("primitiveType")
	public short getPrimitiveType() {
		return unwrap().getPrimitiveType();
	}

	@JSFunction("getRectValue")
	public Object getRectValue() {
		return convertReturnValue(unwrap().getRectValue());
	}

	@JSFunction("getRGBColorValue")
	public Object getRGBColorValue() {
		return convertReturnValue(unwrap().getRGBColorValue());
	}

	@JSFunction("getStringValue")
	public String getStringValue() {
		return unwrap().getStringValue();
	}

	@JSFunction("setFloatValue")
	public void setFloatValue(short unitType, float floatValue) {
		unwrap().setFloatValue(unitType, floatValue);
	}

	@JSFunction("setStringValue")
	public void setStringValue(short stringType, String stringValue) {
		unwrap().setStringValue(stringType, stringValue);
	}
}
