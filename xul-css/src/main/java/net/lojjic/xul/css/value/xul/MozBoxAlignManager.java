package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;
import net.lojjic.xul.css.value.XULValueConstants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;

/**
 * Manager for XUL '-moz-box-align' property
 */
public class MozBoxAlignManager extends IdentifierManager {

	protected static final StringMap values = new StringMap();
	static {
		values.put(XULCSSConstants.CSS_BASELINE_VALUE, XULValueConstants.BASELINE_VALUE);
		values.put(XULCSSConstants.CSS_CENTER_VALUE, XULValueConstants.CENTER_VALUE);
		values.put(XULCSSConstants.CSS_END_VALUE, XULValueConstants.END_VALUE);
		values.put(XULCSSConstants.CSS_START_VALUE, XULValueConstants.START_VALUE);
		values.put(XULCSSConstants.CSS_STRETCH_VALUE, XULValueConstants.STRETCH_VALUE);
	}

	/**
	 * Returns the map that contains the name/value mappings for each
	 * possible identifiers.
	 */
	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return XULValueConstants.STRETCH_VALUE;
	}

	public boolean isInheritedProperty() {
		return false;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return XULCSSConstants.CSS_MOZ_BOX_ALIGN_PROPERTY;
	}
}
