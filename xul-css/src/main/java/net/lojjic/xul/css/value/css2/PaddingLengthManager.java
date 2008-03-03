package net.lojjic.xul.css.value.css2;

import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.util.SVGTypes;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'padding-*' properties
 */
public class PaddingLengthManager extends LengthManager {

	private String propertyName;

	public PaddingLengthManager(String propertyName) {
		this.propertyName = propertyName;
	}

	protected int getOrientation() {
		return HORIZONTAL_ORIENTATION; //always refers to width of container
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

	public String getPropertyName() {
		return propertyName;
	}
}
