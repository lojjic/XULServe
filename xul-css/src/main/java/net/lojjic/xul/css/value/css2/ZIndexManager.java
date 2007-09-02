package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueManager;
import org.apache.batik.css.engine.value.FloatValue;
import org.apache.batik.css.engine.value.Value;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * Manager for CSS2 'z-index' property
 */
public class ZIndexManager extends AbstractValueManager {
	
	public Value createValue(LexicalUnit lexicalUnit, CSSEngine cssEngine) throws DOMException {
		short unitType = lexicalUnit.getLexicalUnitType();
		switch(unitType) {
			case LexicalUnit.SAC_INHERIT:
				return CSS2ValueConstants.INHERIT_VALUE;

			case LexicalUnit.SAC_IDENT:
				if(CSS2Constants.CSS_AUTO_VALUE.equals(lexicalUnit.getStringValue())) {
					return CSS2ValueConstants.AUTO_VALUE;
				}
				throw createInvalidIdentifierDOMException(lexicalUnit.getStringValue());

			case LexicalUnit.SAC_INTEGER:
				return new FloatValue(CSSPrimitiveValue.CSS_NUMBER, lexicalUnit.getIntegerValue());

			default:
				throw createInvalidLexicalUnitDOMException(unitType);
		}
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.AUTO_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	public String getPropertyName() {
		return CSS2Constants.CSS_Z_INDEX_PROPERTY;
	}
}
