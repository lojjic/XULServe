package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for CSS2 'border-*-style' properties
 */
public class BorderStyleManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_NONE_VALUE, CSS2ValueConstants.NONE_VALUE);
		values.put(CSS2Constants.CSS_HIDDEN_VALUE, CSS2ValueConstants.HIDDEN_VALUE);
		values.put(CSS2Constants.CSS_DOTTED_VALUE, CSS2ValueConstants.DOTTED_VALUE);
		values.put(CSS2Constants.CSS_DASHED_VALUE, CSS2ValueConstants.DASHED_VALUE);
		values.put(CSS2Constants.CSS_SOLID_VALUE, CSS2ValueConstants.SOLID_VALUE);
		values.put(CSS2Constants.CSS_DOUBLE_VALUE, CSS2ValueConstants.DOUBLE_VALUE);
		values.put(CSS2Constants.CSS_GROOVE_VALUE, CSS2ValueConstants.GROOVE_VALUE);
		values.put(CSS2Constants.CSS_RIDGE_VALUE, CSS2ValueConstants.RIDGE_VALUE);
		values.put(CSS2Constants.CSS_INSET_VALUE, CSS2ValueConstants.INSET_VALUE);
		values.put(CSS2Constants.CSS_OUTSET_VALUE, CSS2ValueConstants.OUTSET_VALUE);
	}

	private String propertyName;

	public BorderStyleManager(String propertyName) {
		this.propertyName = propertyName;
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
		return propertyName;
	}
}
