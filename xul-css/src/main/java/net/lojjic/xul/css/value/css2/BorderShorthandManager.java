package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'border' shorthand property
 */
public class BorderShorthandManager extends AbstractValueFactory implements ShorthandManager {

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

		LexicalUnit borderWidth = null;
		LexicalUnit borderStyle = null;
		LexicalUnit borderColor = null;

		while(lu != null) {
			short unitType = lu.getLexicalUnitType();
			switch(unitType) {
				case LexicalUnit.SAC_INHERIT:
					if(borderWidth != null || borderStyle != null || borderColor != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					borderWidth = borderStyle = borderColor = lu;
					break;

				case LexicalUnit.SAC_EM:
				case LexicalUnit.SAC_EX:
				case LexicalUnit.SAC_PIXEL:
				case LexicalUnit.SAC_CENTIMETER:
				case LexicalUnit.SAC_MILLIMETER:
				case LexicalUnit.SAC_INCH:
				case LexicalUnit.SAC_POINT:
				case LexicalUnit.SAC_PICA:
				case LexicalUnit.SAC_INTEGER:
				case LexicalUnit.SAC_REAL:
					if(borderWidth != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					borderWidth = lu;
					break;

				case LexicalUnit.SAC_RGBCOLOR:
					if(borderColor != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					borderColor = lu;
					break;

				case LexicalUnit.SAC_IDENT:
					String ident = lu.getStringValue().toLowerCase().intern();
					if(BorderWidthManager.idents.get(ident) != null) {
						if(borderWidth != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						borderWidth = lu;
					}
					else if(BorderStyleManager.values.get(ident) != null) {
						if(borderStyle != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						borderStyle = lu;
					}
					else if(BorderColorManager.values.get(ident) != null) {
						if(borderColor != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						borderColor = lu;
					}
					else {
						throw createInvalidIdentifierDOMException(ident);
					}
					break;

				default:
					throw createInvalidLexicalUnitDOMException(unitType);
			}

			lu = lu.getNextLexicalUnit();
		}

		if(borderWidth != null) {
			ph.property(CSS2Constants.CSS_BORDER_WIDTH_PROPERTY, borderWidth, imp);
		}
		if(borderStyle != null) {
			ph.property(CSS2Constants.CSS_BORDER_STYLE_PROPERTY, borderStyle, imp);
		}
		if(borderColor != null) {
			ph.property(CSS2Constants.CSS_BORDER_COLOR_PROPERTY, borderColor, imp);
		}
	}

}
