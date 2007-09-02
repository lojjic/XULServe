package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'position' property
 */
public class PositionManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_STATIC_VALUE, CSS2ValueConstants.STATIC_VALUE);
		values.put(CSS2Constants.CSS_RELATIVE_VALUE, CSS2ValueConstants.RELATIVE_VALUE);
		values.put(CSS2Constants.CSS_ABSOLUTE_VALUE, CSS2ValueConstants.ABSOLUTE_VALUE);
		values.put(CSS2Constants.CSS_FIXED_VALUE, CSS2ValueConstants.FIXED_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.STATIC_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_POSITION_PROPERTY;
	}
}
