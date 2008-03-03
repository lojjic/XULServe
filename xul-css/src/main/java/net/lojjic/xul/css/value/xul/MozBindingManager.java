package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.*;
import org.apache.batik.util.CSSConstants;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for XUL '-moz-binding' property
 */
public class MozBindingManager extends AbstractValueManager {

	protected static final StringMap values = new StringMap();
	static {
		values.put(CSSConstants.CSS_NONE_VALUE, ValueConstants.NONE_VALUE);
	}


	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		switch(lu.getLexicalUnitType()) {
			case LexicalUnit.SAC_URI:
				return new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue()));

			case LexicalUnit.SAC_IDENT:
				Value val = (Value)values.get(lu.getStringValue().toLowerCase().intern());
				if(val == null) {
					throw createInvalidIdentifierDOMException(lu.getStringValue());
				}
				return val;

			default:
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
		}
	}

	public Value getDefaultValue() {
		return ValueConstants.NONE_VALUE;
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
		return SVGTypes.TYPE_URI_OR_IDENT;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return XULCSSConstants.CSS_MOZ_BINDING_PROPERTY;
	}

}
