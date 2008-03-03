package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.*;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

import java.util.HashMap;

/**
 * Manager for CSS2 'background-position' property
 */
public class BackgroundPositionManager extends LengthManager {

	/**
	 * Default value -- background-position:0 0;
	 */
	public static final ListValue defaultValue = new ListValue(' ');
	static {
		defaultValue.append(CSS2ValueConstants.NUMBER_0);
		defaultValue.append(CSS2ValueConstants.NUMBER_0);
	}

	public static final StringMap idents = new StringMap();
	static {
		idents.put(CSS2Constants.CSS_TOP_VALUE, CSS2ValueConstants.TOP_VALUE);
		idents.put(CSS2Constants.CSS_LEFT_VALUE, CSS2ValueConstants.LEFT_VALUE);
		idents.put(CSS2Constants.CSS_CENTER_VALUE, CSS2ValueConstants.CENTER_VALUE);
		idents.put(CSS2Constants.CSS_RIGHT_VALUE, CSS2ValueConstants.RIGHT_VALUE);
		idents.put(CSS2Constants.CSS_BOTTOM_VALUE, CSS2ValueConstants.BOTTOM_VALUE);
	}

	public static final HashMap<Value,Value> horizontalValues = new HashMap<Value,Value>(3);
	static {
		horizontalValues.put(CSS2ValueConstants.LEFT_VALUE, CSS2ValueConstants.PERCENT_ZERO);
		horizontalValues.put(CSS2ValueConstants.CENTER_VALUE, CSS2ValueConstants.PERCENT_FIFTY);
		horizontalValues.put(CSS2ValueConstants.RIGHT_VALUE, CSS2ValueConstants.PERCENT_ONE_HUNDRED);
	}

	public static final HashMap<Value,Value> verticalValues = new HashMap<Value,Value>(3);
	static {
		verticalValues.put(CSS2ValueConstants.TOP_VALUE, CSS2ValueConstants.PERCENT_ZERO);
		verticalValues.put(CSS2ValueConstants.CENTER_VALUE, CSS2ValueConstants.PERCENT_FIFTY);
		verticalValues.put(CSS2ValueConstants.BOTTOM_VALUE, CSS2ValueConstants.PERCENT_ONE_HUNDRED);
	}

	/**
	 * Create the value. 
	 */
	public Value createValue(LexicalUnit lexicalUnit, CSSEngine cssEngine) throws DOMException {
		Value firstValue, secondValue;
		ListValue listValue;

		switch(lexicalUnit.getLexicalUnitType()) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			// top/right/bottom/left/center identifiers:
			case LexicalUnit.SAC_IDENT:
				firstValue = (Value)idents.get(lexicalUnit.getStringValue().toLowerCase().intern());
				if(firstValue == null) {
					throw createInvalidIdentifierDOMException(lexicalUnit.getStringValue());
				}
				listValue = new ListValue(' ');
				listValue.append(firstValue);

				lexicalUnit = lexicalUnit.getNextLexicalUnit();
				if(lexicalUnit != null) {
					if(lexicalUnit.getLexicalUnitType() != LexicalUnit.SAC_IDENT) {
						throw createInvalidLexicalUnitDOMException(lexicalUnit.getLexicalUnitType());
					}
					secondValue = (Value)idents.get(lexicalUnit.getStringValue().toLowerCase().intern());
					if(secondValue == null ||
							(horizontalValues.containsKey(firstValue) && !verticalValues.containsKey(secondValue)) ||
							(verticalValues.containsKey(firstValue) && !horizontalValues.containsKey(secondValue))
						) {
						throw createInvalidIdentifierDOMException(lexicalUnit.getStringValue());
					}
					listValue.append(secondValue);
				}
				return listValue;

			// lengths - reuse superclass to create individual values
			default:
				firstValue = super.createValue(lexicalUnit, cssEngine);
				listValue = new ListValue(' ');
				listValue.append(firstValue);

				lexicalUnit = lexicalUnit.getNextLexicalUnit();
				if(lexicalUnit != null) {
					secondValue = super.createValue(lexicalUnit, cssEngine);
					listValue.append(secondValue);
				}
				return listValue;
		}

	}

	/**
	 * Calculate the computed value. Handle identifiers and percentages ourselves,
	 * delegate other length types to the superclass.
	 */
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine,
	                          int idx, StyleMap sm, Value value) {
		if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
			ListValue listValue = (ListValue)value;
			ListValue result = new ListValue(' ');

			Value firstValue = listValue.item(0);
			Value secondValue = (listValue.getLength() > 1) ? listValue.item(1) : null;

			// translate identifiers to a pair of percentages:
			if(firstValue.getPrimitiveType() == CSSPrimitiveValue.CSS_IDENT) {
				// a nonexistent second value is the same as 'center':
				if(secondValue == null) {
					secondValue = CSS2ValueConstants.CENTER_VALUE;
				}

				// force horizontal ident into first spot:
				if(!horizontalValues.containsKey(firstValue)) {
					Value temp = firstValue;
					firstValue = secondValue;
					secondValue = temp;
				}

				// translate the idents to percentage values:
				firstValue = horizontalValues.get(firstValue);
				secondValue = verticalValues.get(secondValue);
			}
			// if only one length given, the vertical length becomes 50%:
			else if(secondValue == null) {
				secondValue = CSS2ValueConstants.PERCENT_FIFTY;
			}

			switch(firstValue.getPrimitiveType()) {
				case CSSPrimitiveValue.CSS_PERCENTAGE:
					float eltWidth = engine.getComputedStyle(elt, null, engine.getPropertyIndex("width")).getFloatValue();
					result.append(new FloatValue(CSSPrimitiveValue.CSS_NUMBER,
							firstValue.getFloatValue() * eltWidth / 100f));
					break;
				default:
					result.append(super.computeValue(elt, pseudo, engine, idx, sm, firstValue));
			}
			switch(secondValue.getPrimitiveType()) {
				case CSSPrimitiveValue.CSS_PERCENTAGE:
					float eltHeight = engine.getComputedStyle(elt, null, engine.getPropertyIndex("height")).getFloatValue();
					result.append(new FloatValue(CSSPrimitiveValue.CSS_NUMBER,
							secondValue.getFloatValue() * eltHeight / 100f));
					break;
				default:
					result.append(super.computeValue(elt, pseudo, engine, idx, sm, secondValue));
			}

			return result;
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 *
	 * Not actually used in this implementation.
	 */
	protected int getOrientation() {
		return BOTH_ORIENTATION;
	}


	public Value getDefaultValue() {
		return defaultValue;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	public boolean isAnimatableProperty() {
		return false;
	}

	public boolean isAdditiveProperty() {
		return false;
	}

	public int getPropertyType() {
		return SVGTypes.TYPE_LENGTH_LIST_OR_IDENT;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BACKGROUND_POSITION_PROPERTY;
	}
}
