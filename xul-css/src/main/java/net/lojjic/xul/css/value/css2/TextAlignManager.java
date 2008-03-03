package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.StringValue;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
 * Manager for CSS2 'text-align' property
 */
public class TextAlignManager extends IdentifierManager {

	/**
	 * Special value instance that indicates the default value for text-align,
	 * which will evaluate to either 'left' or 'right' at computation time
	 * depending on the writing direction.
	 */
	private static final Value defaultValue = new StringValue(CSSPrimitiveValue.CSS_IDENT, "DEFAULT-TEXT-ALIGN");

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_LEFT_VALUE, CSS2ValueConstants.LEFT_VALUE);
		values.put(CSS2Constants.CSS_RIGHT_VALUE, CSS2ValueConstants.RIGHT_VALUE);
		values.put(CSS2Constants.CSS_CENTER_VALUE, CSS2ValueConstants.CENTER_VALUE);
		values.put(CSS2Constants.CSS_JUSTIFY_VALUE, CSS2ValueConstants.JUSTIFY_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return defaultValue;
	}

	public boolean isInheritedProperty() {
		return true;
	}

	public boolean isAnimatableProperty() {
		return false;
	}

	public boolean isAdditiveProperty() {
		return false;
	}

	public int getPropertyType() {
		return SVGTypes.TYPE_IDENT;
	}
	
	/**
	 * Override super to handle string values (applies to table cells only)
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		if(lu.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
			return new StringValue(CSSPrimitiveValue.CSS_STRING, lu.getStringValue());
		}
		return super.createValue(lu, engine);
	}

	/**
	 * Compute the value.  If a string value was specified on a non-table-cell element, revert
	 * to the default value.  If the given value is the 'magic' defaultValue, compute
	 * either 'left' or 'right' depending on the writing direction.
	 */
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
		if(value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE &&
				value.getPrimitiveType() == CSSPrimitiveValue.CSS_STRING) {
			int displayIdx = engine.getPropertyIndex(CSS2Constants.CSS_DISPLAY_PROPERTY);
			Value display = engine.getComputedStyle(elt, pseudo, displayIdx);
			if(display == CSS2ValueConstants.TABLE_CELL_VALUE) {
				return value;
			}
			else {
				value = defaultValue;
			}
		}
		if(value == defaultValue) {
			int directionIdx = engine.getPropertyIndex(CSS2Constants.CSS_DIRECTION_PROPERTY);
			Value direction = engine.getComputedStyle(elt, pseudo, directionIdx);
			return (direction == CSS2ValueConstants.RTL_VALUE ? CSS2ValueConstants.RIGHT_VALUE : CSS2ValueConstants.LEFT_VALUE);
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_TEXT_ALIGN_PROPERTY;
	}
}
