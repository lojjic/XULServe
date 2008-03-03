package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.ListValue;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'border-spacing' property
 */
public class BorderSpacingManager extends LengthManager {

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createValue(
			org.w3c.css.sac.LexicalUnit,org.apache.batik.css.engine.CSSEngine)}.
	 */
	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		short unitType = lu.getLexicalUnitType();
		if(unitType == LexicalUnit.SAC_INHERIT) {
			return CSS2ValueConstants.INHERIT_VALUE;
		}

		ListValue listValue = new ListValue(' ');
		for(int count=1; lu != null; count++) {
			if(count > 2) {
				throw createInvalidLexicalUnitDOMException(unitType);
			}
			if(unitType == LexicalUnit.SAC_PERCENTAGE) {
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
			}
			listValue.append(super.createValue(lu, engine));
			lu = lu.getNextLexicalUnit();
		}
		return listValue;
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return BOTH_ORIENTATION; //Not used
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NUMBER_0;
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
		return SVGTypes.TYPE_LENGTH_LIST;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BORDER_SPACING_PROPERTY;
	}
}
