package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'white-space' property
 */
public class WhiteSpaceManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_NORMAL_VALUE, CSS2ValueConstants.NORMAL_VALUE);
		values.put(CSS2Constants.CSS_PRE_VALUE, CSS2ValueConstants.PRE_VALUE);
		values.put(CSS2Constants.CSS_NOWRAP_VALUE, CSS2ValueConstants.NOWRAP_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.NORMAL_VALUE;
	}

	public boolean isInheritedProperty() {
		return true;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_WHITE_SPACE_PROPERTY;
	}
}
