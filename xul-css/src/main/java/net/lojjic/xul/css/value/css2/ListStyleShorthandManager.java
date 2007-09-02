package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'list-style' shorthand property
 */
public class ListStyleShorthandManager extends AbstractValueFactory implements ShorthandManager {

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_LIST_STYLE_PROPERTY;
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

		short unitType = lu.getLexicalUnitType();

		// First position: can either be 'inherit', 'none', or <list-style-type>
		switch(unitType) {
			case LexicalUnit.SAC_INHERIT:
				return;

			default:
				LexicalUnit lsImage = null;
				LexicalUnit lsType = null;
				LexicalUnit lsPosition = null;

				// walk through the units, mapping each to either list-style-type,
				// list-style-position, or list-style-image:
				while(lu != null) {
					switch(unitType) {
						case LexicalUnit.SAC_IDENT:
							String ident = lu.getStringValue();
							if(ListStyleTypeManager.values.get(ident) != null) {
								if(lsType != null) {
									throw createInvalidIdentifierDOMException(ident);
								}
								lsType = lu;
							}
							else if(ListStylePositionManager.values.get(ident) != null) {
								if(lsPosition != null) {
									throw createInvalidIdentifierDOMException(ident);
								}
								lsPosition = lu;
							}
							else {
								throw createInvalidIdentifierDOMException(ident);
							}
							break;

						case LexicalUnit.SAC_URI:
							if(lsImage != null) {
								throw createInvalidLexicalUnitDOMException(unitType);
							}
							lsImage = lu;
							break;
					}
					lu = lu.getNextLexicalUnit();
				}

				if(lsType != null) {
					ph.property(CSS2Constants.CSS_LIST_STYLE_TYPE_PROPERTY, lsType, imp);
					// If only 'none' was specified, also set the list-style-image to none:
					if(lsPosition == null && lsImage == null &&
							lsType.getStringValue().equals(CSS2Constants.CSS_NONE_VALUE)) {
						lsImage = lsType;
					}
				}
				if(lsPosition != null) {
					ph.property(CSS2Constants.CSS_LIST_STYLE_POSITION_PROPERTY, lsPosition, imp);
				}
				if(lsImage != null) {
					ph.property(CSS2Constants.CSS_LIST_STYLE_IMAGE_PROPERTY, lsImage, imp);
				}
		}

	}

}
