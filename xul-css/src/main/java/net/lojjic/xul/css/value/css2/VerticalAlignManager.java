package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.FloatValue;
import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
 * Manager for CSS2 'vertical-align' property
 */
public class VerticalAlignManager extends LengthManager {

	public static final StringMap identValues = new StringMap();
	static {
		identValues.put(CSS2Constants.CSS_BASELINE_VALUE, CSS2ValueConstants.BASELINE_VALUE);
		identValues.put(CSS2Constants.CSS_MIDDLE_VALUE, CSS2ValueConstants.MIDDLE_VALUE);
		identValues.put(CSS2Constants.CSS_SUB_VALUE, CSS2ValueConstants.SUB_VALUE);
		identValues.put(CSS2Constants.CSS_SUPER_VALUE, CSS2ValueConstants.SUPER_VALUE);
		identValues.put(CSS2Constants.CSS_TEXT_TOP_VALUE, CSS2ValueConstants.TEXT_TOP_VALUE);
		identValues.put(CSS2Constants.CSS_TEXT_BOTTOM_VALUE, CSS2ValueConstants.TEXT_BOTTOM_VALUE);
		identValues.put(CSS2Constants.CSS_TOP_VALUE, CSS2ValueConstants.TOP_VALUE);
		identValues.put(CSS2Constants.CSS_BOTTOM_VALUE, CSS2ValueConstants.BOTTOM_VALUE);
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		switch(lu.getLexicalUnitType()) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				Value val = (Value)identValues.get(lu.getStringValue().toLowerCase().intern());
				if(val == null) {
					throw createInvalidIdentifierDOMException(lu.getStringValue());
				}
				return val;

			default:
				return super.createValue(lu, engine);
		}
	}

	/**
	 * Override computation of percentages to be relative to the line-height
	 */
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
		if(value.getCssValueType() != CSSValue.CSS_PRIMITIVE_VALUE &&
				value.getPrimitiveType() == CSSPrimitiveValue.CSS_PERCENTAGE) {
			int lineHeightIdx = engine.getPropertyIndex(CSS2Constants.CSS_LINE_HEIGHT_PROPERTY);
			float lineHeight = engine.getComputedStyle(elt, pseudo, lineHeightIdx).getFloatValue();
			return new FloatValue(CSSPrimitiveValue.CSS_NUMBER, lineHeight * value.getFloatValue() * 0.01f);
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return BOTH_ORIENTATION; //not used
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.BASELINE_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_VERTICAL_ALIGN_PROPERTY;
	}
}
