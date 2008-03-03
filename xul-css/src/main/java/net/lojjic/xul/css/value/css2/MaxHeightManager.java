package net.lojjic.xul.css.value.css2;

import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import net.lojjic.xul.css.CSS2Constants;

/**
 * Manager for CSS2 'max-height' property
 */
public class MaxHeightManager extends LengthManager {

	public static final StringMap identValues = new StringMap();
	static {
		identValues.put(CSS2Constants.CSS_NONE_VALUE, CSS2ValueConstants.NONE_VALUE);
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return VERTICAL_ORIENTATION;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NONE_VALUE;
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
		return SVGTypes.TYPE_LENGTH;
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		switch(lu.getLexicalUnitType()) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				Value value = (Value)identValues.get(lu.getStringValue().toLowerCase().intern());
				if(value == null) {
					throw createInvalidIdentifierDOMException(lu.getStringValue());
				}
				return value;

			default:
				return super.createValue(lu, engine);
		}
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_MAX_HEIGHT_PROPERTY;
	}
}

