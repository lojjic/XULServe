package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.AbstractValueFactory;
import org.apache.batik.css.engine.value.ShorthandManager;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;

/**
 * Manager for CSS2 'background' shorthand property
 */
public class BackgroundShorthandManager extends AbstractValueFactory implements ShorthandManager {

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BACKGROUND_PROPERTY;
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

		LexicalUnit bgColor = null;
		LexicalUnit bgImage = null;
		LexicalUnit bgRepeat = null;
		LexicalUnit bgAttach = null;
		LexicalUnit bgPosition = null;

		short unitType = lu.getLexicalUnitType();
		while(lu != null) {
			switch(unitType) {
				// color value --> background-color:
				case LexicalUnit.SAC_RGBCOLOR:
					if(bgColor != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					bgColor = lu;
					break;

				// url value --> background-image:
				case LexicalUnit.SAC_URI:
					if(bgImage != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					bgImage = lu;
					break;

				// length values --> background-position, can be one or two
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
				case LexicalUnit.SAC_PERCENTAGE:
					if(bgPosition != null) {
						throw createInvalidLexicalUnitDOMException(unitType);
					}
					bgPosition = new LexicalUnitProxy(lu);
					// check the next unit to see if it's also a length:
					LexicalUnit luNext = lu.getNextLexicalUnit();
					if(luNext != null && isLength(luNext)) {
						new LexicalUnitProxy(luNext, bgPosition);
					}

				// identifiers --> any one of the background properties
				case LexicalUnit.SAC_IDENT:
					String ident = lu.getStringValue();
					if(BackgroundAttachmentManager.values.get(ident) != null) {
						if(bgAttach != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						bgAttach = lu;
					}
					else if(BackgroundColorManager.getValues().get(ident) != null) {
						if(bgColor != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						bgColor = lu;
					}
					else if(BackgroundImageManager.values.get(ident) != null) {
						if(bgImage != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						bgImage = lu;
					}
					else if(BackgroundPositionManager.idents.get(ident) != null) {
						if(bgPosition != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						bgPosition = new LexicalUnitProxy(lu);
						// check the next unit to see if it's also a background-position ident:
						luNext = lu.getNextLexicalUnit();
						if(luNext != null && luNext.getLexicalUnitType() == LexicalUnit.SAC_IDENT &&
								BackgroundPositionManager.idents.get(luNext.getStringValue()) != null) {
							new LexicalUnitProxy(luNext, bgPosition);
							lu = luNext;
						}
					}
					else if(BackgroundRepeatManager.values.get(ident) != null) {
						if(bgRepeat != null) {
							throw createInvalidIdentifierDOMException(ident);
						}
						bgRepeat = lu;
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

		if(bgColor != null) {
			ph.property(CSS2Constants.CSS_BACKGROUND_COLOR_PROPERTY, bgColor, imp);
		}
		if(bgImage != null) {
			ph.property(CSS2Constants.CSS_BACKGROUND_IMAGE_PROPERTY, bgImage, imp);
		}
		if(bgRepeat != null) {
			ph.property(CSS2Constants.CSS_BACKGROUND_REPEAT_PROPERTY, bgRepeat, imp);
		}
		if(bgAttach != null) {
			ph.property(CSS2Constants.CSS_BACKGROUND_ATTACHMENT_PROPERTY, bgAttach, imp);
		}
		if(bgPosition != null) {
			ph.property(CSS2Constants.CSS_BACKGROUND_POSITION_PROPERTY, bgPosition, imp);
		}

	}

	private boolean isLength(LexicalUnit lu) {
		switch(lu.getLexicalUnitType()) {
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
			case LexicalUnit.SAC_PERCENTAGE:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Simple proxy for a LexicalUnit that delegates all methods to the wrapped
	 * LexicalUnit except for get(Previous|Next)LexicalUnit. This allows "changing"
	 * the chaining order of existing LexicalUnit objects.
	 */
	public static class LexicalUnitProxy implements LexicalUnit {
		private LexicalUnit delegate;
		private LexicalUnit previous;
		private LexicalUnit next;

		public LexicalUnitProxy(LexicalUnit delegate) {
			this(delegate, null);
		}

		public LexicalUnitProxy(LexicalUnit delegate, LexicalUnit previous) {
			this.delegate = delegate;
			this.previous = previous;
			((LexicalUnitProxy)previous).next = this;
		}

		public LexicalUnit getNextLexicalUnit() {
			return next;
		}

		public LexicalUnit getPreviousLexicalUnit() {
			return previous;
		}

		public int getIntegerValue() {
			return delegate.getIntegerValue();
		}

		public float getFloatValue() {
			return delegate.getFloatValue();
		}

		public String getDimensionUnitText() {
			return delegate.getDimensionUnitText();
		}

		public String getFunctionName() {
			return delegate.getFunctionName();
		}

		public LexicalUnit getParameters() {
			return delegate.getParameters();
		}

		public String getStringValue() {
			return delegate.getStringValue();
		}

		public LexicalUnit getSubValues() {
			return delegate.getSubValues();
		}

		public short getLexicalUnitType() {
			return delegate.getLexicalUnitType();
		}
	}
}
