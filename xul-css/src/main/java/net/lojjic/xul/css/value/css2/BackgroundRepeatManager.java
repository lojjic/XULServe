package net.lojjic.xul.css.value.css2;

import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import net.lojjic.xul.css.CSS2Constants;

/**
 * Manager for CSS2 'background-repeat' property
 */
public class BackgroundRepeatManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_REPEAT_VALUE, CSS2ValueConstants.REPEAT_VALUE);
		values.put(CSS2Constants.CSS_REPEAT_X_VALUE, CSS2ValueConstants.REPEAT_X_VALUE);
		values.put(CSS2Constants.CSS_REPEAT_Y_VALUE, CSS2ValueConstants.REPEAT_Y_VALUE);
		values.put(CSS2Constants.CSS_NO_REPEAT_VALUE, CSS2ValueConstants.NO_REPEAT_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.REPEAT_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BACKGROUND_REPEAT_PROPERTY;
	}
}

