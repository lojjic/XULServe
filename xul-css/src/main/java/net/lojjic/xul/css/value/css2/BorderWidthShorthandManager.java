package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'border-width' shorthand property
 */
public class BorderWidthShorthandManager extends AbstractValueFactory implements ShorthandManager {

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BORDER_WIDTH_PROPERTY;
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
		if (lu.getLexicalUnitType() == LexicalUnit.SAC_INHERIT) {
			return;
		}

		LexicalUnit[] lus  = new LexicalUnit[4];
		int cnt=0;
		while (lu != null) {
			if (cnt == 4) {
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
			}
			lus[cnt++] = lu;
			lu = lu.getNextLexicalUnit();
		}
		switch (cnt) {
			case 1: lus[3] = lus[2] = lus[1] = lus[0]; break;
			case 2: lus[2] = lus[0];  lus[3] = lus[1]; break;
			case 3: lus[3] = lus[1]; break;
		}

		ph.property(CSS2Constants.CSS_BORDER_TOP_WIDTH_PROPERTY,    lus[0], imp);
		ph.property(CSS2Constants.CSS_BORDER_RIGHT_WIDTH_PROPERTY,  lus[1], imp);
		ph.property(CSS2Constants.CSS_BORDER_BOTTOM_WIDTH_PROPERTY, lus[2], imp);
		ph.property(CSS2Constants.CSS_BORDER_LEFT_WIDTH_PROPERTY,   lus[3], imp);
	}
}
