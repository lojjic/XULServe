package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.AbstractColorManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'background-color' property
 */
public class BackgroundColorManager extends AbstractColorManager {

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

	/**
	 * expose inherited color identifiers
	 */
	public static StringMap getValues() {
		return values;
	}
}
