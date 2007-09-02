package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'float' property
 */
public class FloatManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_LEFT_VALUE, CSS2ValueConstants.LEFT_VALUE);
		values.put(CSS2Constants.CSS_RIGHT_VALUE, CSS2ValueConstants.RIGHT_VALUE);
		values.put(CSS2Constants.CSS_NONE_VALUE, CSS2ValueConstants.NONE_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NONE_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_FLOAT_PROPERTY;
	}
}
