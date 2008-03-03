package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'text-indent' property
 */
public class TextIndentManager extends LengthManager {

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		return HORIZONTAL_ORIENTATION; //always refers to width of containing block
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NUMBER_0;
	}

	public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
		if(lu.getLexicalUnitType() == LexicalUnit.SAC_INHERIT) {
			return CSS2ValueConstants.INHERIT_VALUE;
		}
		return super.createValue(lu, engine);
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
		return SVGTypes.TYPE_LENGTH;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_TEXT_INDENT_PROPERTY;
	}
}
