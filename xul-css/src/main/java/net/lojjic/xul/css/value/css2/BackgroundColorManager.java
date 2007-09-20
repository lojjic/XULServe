package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.AbstractColorManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.StyleMap;

/**
 * Manager for CSS2 'background-color' property
 */
public class BackgroundColorManager extends AbstractColorManager {

	protected static final StringMap values = new StringMap(AbstractColorManager.values);
	static {
		values.put(CSS2Constants.CSS_TRANSPARENT_VALUE, CSS2ValueConstants.TRANSPARENT_VALUE);
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.TRANSPARENT_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BACKGROUND_COLOR_PROPERTY;
	}

	public StringMap getIdentifiers() {
	    return values;
	}

	/**
	 * Override computeValue to handle 'transparent' ident
	 */
	@Override
	public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
		if(value == CSS2ValueConstants.TRANSPARENT_VALUE) {
			return value;
		}
		return super.computeValue(elt, pseudo, engine, idx, sm, value);
	}
}
