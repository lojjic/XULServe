package net.lojjic.xul.css.value.xul;

import org.apache.batik.css.engine.value.AbstractValueManager;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.FloatValue;
import org.apache.batik.css.engine.CSSEngine;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;
import net.lojjic.xul.css.XULCSSConstants;

/**
 * Manager for XUL '-moz-box-ordinal-group' property
 */
public class MozBoxOrdinalGroupManager extends AbstractValueManager {

	public Value createValue(LexicalUnit lexicalUnit, CSSEngine cssEngine) throws DOMException {
		short type = lexicalUnit.getLexicalUnitType();
		switch(type) {
			case LexicalUnit.SAC_INTEGER:
				return new FloatValue(type, lexicalUnit.getIntegerValue());
			case LexicalUnit.SAC_REAL:
				return new FloatValue(type, lexicalUnit.getFloatValue());
		}
		throw createInvalidLexicalUnitDOMException(lexicalUnit.getLexicalUnitType());
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.ValueManager#createFloatValue(short,float)}.
	 */
	public Value createFloatValue(short unitType, float floatValue) throws DOMException {
		if(unitType == CSSPrimitiveValue.CSS_NUMBER) {
			return new FloatValue(unitType, floatValue);
		}
		throw createInvalidFloatTypeDOMException(unitType);
	}

	public Value getDefaultValue() {
		// TODO is this right? can't find docs
		return new FloatValue(CSSPrimitiveValue.CSS_NUMBER, 1);
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return XULCSSConstants.CSS_MOZ_BOX_ORDINAL_GROUP_PROPERTY;
	}

}
