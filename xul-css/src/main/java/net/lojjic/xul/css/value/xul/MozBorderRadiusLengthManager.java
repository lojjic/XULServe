package net.lojjic.xul.css.value.xul;

import org.apache.batik.css.engine.value.LengthManager;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.ValueConstants;
import org.apache.batik.util.SVGTypes;

/**
 * Manager for XUL '-moz-border-radius-*' properties
 */
public class MozBorderRadiusLengthManager extends LengthManager {

	private String propertyName;

	public MozBorderRadiusLengthManager(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Indicates the orientation of the property associated with
	 * this manager.
	 */
	protected int getOrientation() {
		// percentages should always be relative to horizontal width
		return HORIZONTAL_ORIENTATION;
	}

	public Value getDefaultValue() {
		return ValueConstants.NUMBER_0;
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
		return SVGTypes.TYPE_LENGTH;
	}

	/**
	 * Returns the name of the property handled.
	 */
	public String getPropertyName() {
		return propertyName;
	}
}
