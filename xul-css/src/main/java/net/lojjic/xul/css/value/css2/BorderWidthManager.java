package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'border-*-width' properties
 */
public class BorderWidthManager extends LengthManager {

	public static final StringMap idents = new StringMap();
	static {
		idents.put(CSS2Constants.CSS_THIN_VALUE, CSS2ValueConstants.THIN_VALUE);
		idents.put(CSS2Constants.CSS_MEDIUM_VALUE, CSS2ValueConstants.MEDIUM_VALUE);
		idents.put(CSS2Constants.CSS_THICK_VALUE, CSS2ValueConstants.THICK_VALUE);
	}

	private String propertyName;

	public BorderWidthManager(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		short unitType = lu.getLexicalUnitType();
		switch(unitType) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				Value val = (Value)idents.get(lu.getStringValue().toLowerCase().intern());
				if(val == null) {
					throw createInvalidIdentifierDOMException(lu.getStringValue());
				}
				return val;

			// percentages not allowed:
			case LexicalUnit.SAC_PERCENTAGE:
				throw createInvalidLexicalUnitDOMException(unitType);

			default:
				return super.createValue(lu, engine);
		}
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return BOTH_ORIENTATION; //not used
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.MEDIUM_VALUE;
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
		return propertyName;
	}
}
