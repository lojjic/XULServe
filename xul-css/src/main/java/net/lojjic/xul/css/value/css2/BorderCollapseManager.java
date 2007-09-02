package net.lojjic.xul.css.value.css2;

import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import net.lojjic.xul.css.CSS2Constants;

/**
 * Manager for CSS2 'border-collapse' property
 */
public class BorderCollapseManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_COLLAPSE_VALUE, CSS2ValueConstants.COLLAPSE_VALUE);
		values.put(CSS2Constants.CSS_SEPARATE_VALUE, CSS2ValueConstants.SEPARATE_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return CSS2ValueConstants.COLLAPSE_VALUE;
	}

	public boolean isInheritedProperty() {
		return true;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return CSS2Constants.CSS_BORDER_COLLAPSE_PROPERTY;
	}
}
