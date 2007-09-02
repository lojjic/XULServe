package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for XUL '-moz-border-radius' shorthand property
 */
public class MozBorderRadiusShorthandManager extends AbstractValueFactory implements ShorthandManager {

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return XULCSSConstants.CSS_MOZ_BORDER_RADIUS_PROPERTY;
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
		LexicalUnit[] parts = new LexicalUnit[4];

		int count = 0;
		while(lu != null) {
			if (count == 4) {
				throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
			}
			parts[count++] = lu;
			lu = lu.getNextLexicalUnit();
		}
		
		switch (count) {
			case 1: parts[3] = parts[2] = parts[1] = parts[0]; break;
			case 2: parts[2] = parts[0];  parts[3] = parts[1]; break;
			case 3: parts[3] = parts[1]; break;
		}

		ph.property(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPLEFT_PROPERTY,     parts[0], imp);
		ph.property(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_TOPRIGHT_PROPERTY,    parts[1], imp);
		ph.property(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMRIGHT_PROPERTY, parts[2], imp);
		ph.property(XULCSSConstants.CSS_MOZ_BORDER_RADIUS_BOTTOMLEFT_PROPERTY,  parts[3], imp);
	}
}
