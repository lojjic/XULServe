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

/**
 * Manager for CSS2 'width' property
 */
public class WidthManager extends LengthManager {

	public static final StringMap identValues = new StringMap();
	static {
		identValues.put(CSS2Constants.CSS_AUTO_VALUE, CSS2ValueConstants.AUTO_VALUE);
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return HORIZONTAL_ORIENTATION;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.AUTO_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		switch(lu.getLexicalUnitType()) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				Value value = (Value)identValues.get(lu.getStringValue());
				if(value == null) {
					throw createInvalidIdentifierDOMException(lu.getStringValue());
				}
				return value;

			default:
				return super.createValue(lu, engine);
		}
	}

	/**
	 * Calculate 'auto' width value
	 */
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
		if(value == CSS2ValueConstants.AUTO_VALUE) {
			float containerWidth = engine.getCSSContext().getBlockWidth(elt);
			float marginLeft   = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("margin-left")).getFloatValue();
			float marginRight  = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("margin-right")).getFloatValue();
			float borderLeft   = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("border-left-width")).getFloatValue();
			float borderRight  = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("border-right-width")).getFloatValue();
			float paddingLeft  = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("padding-left")).getFloatValue();
			float paddingRight = engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex("padding-right")).getFloatValue();
			return new FloatValue(CSSPrimitiveValue.CSS_NUMBER,
					containerWidth - marginLeft - marginRight - borderLeft - borderRight - paddingLeft - paddingRight);
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_WIDTH_PROPERTY;
	}
}

