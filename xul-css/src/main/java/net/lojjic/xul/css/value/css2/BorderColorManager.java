package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.AbstractColorManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.StringValue;
import org.apache.batik.css.engine.value.Value;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * Manager for CSS2 'border-*-color' properties
 */
public class BorderColorManager extends AbstractColorManager {

	private static final Value defaultValue = new StringValue(CSSPrimitiveValue.CSS_UNKNOWN, "");

	public static final StringMap values = new StringMap(AbstractColorManager.values);
	static {
		values.put(CSS2Constants.CSS_TRANSPARENT_VALUE, CSS2ValueConstants.TRANSPARENT_VALUE);
	}

	private String propertyName;

	public BorderColorManager(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Border color defaults to the computed color of the 'color' property,
	 * but we aren't able to compute that yet.  Return a magic value instead
	 * which we can detect later in computeValue
	 */
	public Value getDefaultValue() {
		return defaultValue;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Compute the value.  If it is the 'magic' defaultValue, use the computed
	 * value of the 'color' property.
	 */
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine,
	                          int idx, StyleMap sm, Value value) {
		if(value == defaultValue) {
			return engine.getComputedStyle(elt, pseudo, engine.getPropertyIndex(CSS2Constants.CSS_COLOR_PROPERTY));
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Implements {@link org.apache.batik.css.engine.value.IdentifierManager#getIdentifiers()}.
	 */
	public StringMap getIdentifiers() {
	    return values;
	}

}
