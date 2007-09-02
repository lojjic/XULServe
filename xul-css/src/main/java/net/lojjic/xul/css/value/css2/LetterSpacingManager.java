package net.lojjic.xul.css.value.css2;

import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSEngine;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import net.lojjic.xul.css.CSS2Constants;

/**
 * Manager for CSS2 'letter-spacing' property
 */
public class LetterSpacingManager extends LengthManager {

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return BOTH_ORIENTATION; //not used
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		switch(lu.getLexicalUnitType()) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				if(CSS2Constants.CSS_NORMAL_VALUE.equals(lu.getStringValue())) {
					return CSS2ValueConstants.NORMAL_VALUE;
				}
				throw createInvalidIdentifierDOMException(lu.getStringValue());

			case LexicalUnit.SAC_PERCENTAGE:
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());

			default:
				return super.createValue(lu, engine);
		}
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NORMAL_VALUE;
	}

	public boolean isInheritedProperty() {
		return true;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_LETTER_SPACING_PROPERTY;
	}
}
