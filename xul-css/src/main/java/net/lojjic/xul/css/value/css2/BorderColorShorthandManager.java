package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'border-color' shorthand property
 */
public class BorderColorShorthandManager extends AbstractValueFactory implements ShorthandManager {

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BORDER_PROPERTY;
	}

	public boolean isAnimatableProperty() {
		return false;
	}

	public boolean isAdditiveProperty() {
		return false;
	}

	/**
	 * Sets the properties which are affected by this shorthand
	 * property.
	 *
	 * @param eng The current CSSEngine.
	 * @param ph  The property handler to use.
	 * @param lu  The SAC lexical unit used to create the value.
	 * @param imp The property priority.
	 */
	public void setValues(CSSEngine eng, PropertyHandler ph, LexicalUnit lu, boolean imp) throws DOMException {
		LexicalUnit[] units = new LexicalUnit[4];

		int count=0;
		while(lu != null) {
			if(count == 4) {
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
			}
			units[count++] = lu;
			lu = lu.getNextLexicalUnit();
		}

		switch(count) {
			case 1: units[3] = units[2] = units[1] = units[0]; break;
			case 2: units[3] = units[1]; units[2] = units[0]; break;
			case 3: units[3] = units[1]; break;
		}

		ph.property(CSS2Constants.CSS_BORDER_TOP_COLOR_PROPERTY,    units[0], imp);
		ph.property(CSS2Constants.CSS_BORDER_RIGHT_COLOR_PROPERTY,  units[1], imp);
		ph.property(CSS2Constants.CSS_BORDER_BOTTOM_COLOR_PROPERTY, units[2], imp);
		ph.property(CSS2Constants.CSS_BORDER_LEFT_COLOR_PROPERTY,   units[3], imp);
	}
}
