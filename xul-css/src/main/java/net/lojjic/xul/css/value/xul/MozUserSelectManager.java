package net.lojjic.xul.css.value.xul;

import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.SVGTypes;
import net.lojjic.xul.css.XULCSSConstants;
import net.lojjic.xul.css.value.XULValueConstants;

/**
 * Manager for XUL '-moz-user-select' property
 */
public class MozUserSelectManager extends IdentifierManager {

	protected static final StringMap values = new StringMap();
	static {
		values.put(XULCSSConstants.CSS_NONE_VALUE, XULValueConstants.NONE_VALUE);
		values.put(XULCSSConstants.CSS_NORMAL_VALUE, XULValueConstants.NORMAL_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return XULValueConstants.NORMAL_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	public boolean isAnimatableProperty() {
		return false;
	}

	public boolean isAdditiveProperty() {
		return false;
	}

	public int getPropertyType() {
		return SVGTypes.TYPE_IDENT;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return XULCSSConstants.CSS_MOZ_USER_SELECT_PROPERTY;
	}

}
