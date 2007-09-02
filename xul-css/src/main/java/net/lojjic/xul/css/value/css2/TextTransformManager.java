package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'text-transform' property
 */
public class TextTransformManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_CAPITALIZE_VALUE, CSS2ValueConstants.CAPITALIZE_VALUE);
		values.put(CSS2Constants.CSS_UPPERCASE_VALUE, CSS2ValueConstants.UPPERCASE_VALUE);
		values.put(CSS2Constants.CSS_LOWERCASE_VALUE, CSS2ValueConstants.LOWERCASE_VALUE);
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
		return true;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_TEXT_TRANSFORM_PROPERTY;
	}
}
