package net.lojjic.xul.css.value;

import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;
import net.lojjic.xul.css.value.css2.CSS2ValueConstants;

/**
 * Implementation of CSSContext for unit tests
 */
public class CSSContextTestImpl implements CSSContext {

	private CSSEngine engine;
	public void setEngine(CSSEngine engine) {
		this.engine = engine;
	}

	/**
	 * Returns the Value corresponding to the given system color.
	 */
	public Value getSystemColor(String ident) {
		return CSS2ValueConstants.BLACK_RGB_VALUE;
	}

	/**
	 * Returns the value corresponding to the default font-family.
	 */
	public Value getDefaultFontFamily() {
		return CSS2ValueConstants.SANS_SERIF_VALUE;
	}

	/**
	 * Returns a lighter font-weight.
	 */
	public float getLighterFontWeight(float f) {
		return f - 1f;
	}

	/**
	 * Returns a bolder font-weight.
	 */
	public float getBolderFontWeight(float f) {
		return f + 1f;
	}

	/**
	 * Returns the size of a px CSS unit in millimeters.
	 */
	public float getPixelUnitToMillimeter() {
		return 0.1f;
	}

	/**
	 * Returns the size of a px CSS unit in millimeters.
	 * This will be removed after next release.
	 *
	 * @see #getPixelUnitToMillimeter()
	 */
	public float getPixelToMillimeter() {
		return getPixelUnitToMillimeter();
	}

	/**
	 * Returns the medium font size.
	 */
	public float getMediumFontSize() {
		return 5f;
	}

	/**
	 * Returns the width of the block which directly contains the
	 * given element.
	 */
	public float getBlockWidth(Element elt) {
		return 800f;
	}

	/**
	 * Returns the height of the block which directly contains the
	 * given element.
	 */
	public float getBlockHeight(Element elt) {
		return 600f;
	}

	/**
	 * This method should throw a SecurityException if the resource
	 * found at url and referenced from docURL should not be loaded.
	 *
	 * @param resourceURL url for the resource, as defined in
	 *                    the resource's xlink:href attribute. If that
	 *                    attribute was empty, then this parameter should
	 *                    be null
	 * @param docURL      url for the document into which the
	 *                    resource was found.
	 */
	public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
	}

	/**
	 * Returns true if the document is dynamic, false otherwise.
	 */
	public boolean isDynamic() {
		return false;
	}

	/**
	 * Returns true if the document is interactive, false otherwise.
	 */
	public boolean isInteractive() {
		return false;
	}

	/**
	 * Returns the CSS engine associated with given element.
	 */
	public CSSEngine getCSSEngineForElement(Element e) {
		return engine;
	}
}
