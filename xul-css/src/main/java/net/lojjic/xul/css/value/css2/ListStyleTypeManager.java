package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.SVGTypes;

/**
 * Manager for CSS2 'list-style-type' property
 */
public class ListStyleTypeManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_DISC_VALUE, CSS2ValueConstants.DISC_VALUE);
		values.put(CSS2Constants.CSS_CIRCLE_VALUE, CSS2ValueConstants.CIRCLE_VALUE);
		values.put(CSS2Constants.CSS_SQUARE_VALUE, CSS2ValueConstants.SQUARE_VALUE);
		values.put(CSS2Constants.CSS_DECIMAL_VALUE, CSS2ValueConstants.DECIMAL_VALUE);
		values.put(CSS2Constants.CSS_DECIMAL_LEADING_ZERO_VALUE, CSS2ValueConstants.DECIMAL_LEADING_ZERO_VALUE);
		values.put(CSS2Constants.CSS_LOWER_ROMAN_VALUE, CSS2ValueConstants.LOWER_ROMAN_VALUE);
		values.put(CSS2Constants.CSS_UPPER_ROMAN_VALUE, CSS2ValueConstants.UPPER_ROMAN_VALUE);
		values.put(CSS2Constants.CSS_LOWER_GREEK_VALUE, CSS2ValueConstants.LOWER_GREEK_VALUE);
		values.put(CSS2Constants.CSS_LOWER_ALPHA_VALUE, CSS2ValueConstants.LOWER_ALPHA_VALUE);
		values.put(CSS2Constants.CSS_LOWER_LATIN_VALUE, CSS2ValueConstants.LOWER_LATIN_VALUE);
		values.put(CSS2Constants.CSS_UPPER_ALPHA_VALUE, CSS2ValueConstants.UPPER_ALPHA_VALUE);
		values.put(CSS2Constants.CSS_UPPER_LATIN_VALUE, CSS2ValueConstants.UPPER_LATIN_VALUE);
		values.put(CSS2Constants.CSS_HEBREW_VALUE, CSS2ValueConstants.HEBREW_VALUE);
		values.put(CSS2Constants.CSS_ARMENIAN_VALUE, CSS2ValueConstants.ARMENIAN_VALUE);
		values.put(CSS2Constants.CSS_GEORGIAN_VALUE, CSS2ValueConstants.GEORGIAN_VALUE);
		values.put(CSS2Constants.CSS_CJK_IDEOGRAPHIC_VALUE, CSS2ValueConstants.CJK_IDEOGRAPHIC_VALUE);
		values.put(CSS2Constants.CSS_HIRAGANA_VALUE, CSS2ValueConstants.HIRAGANA_VALUE);
		values.put(CSS2Constants.CSS_KATAKANA_VALUE, CSS2ValueConstants.KATAKANA_VALUE);
		values.put(CSS2Constants.CSS_HIRAGANA_IROHA_VALUE, CSS2ValueConstants.HIRAGANA_IROHA_VALUE);
		values.put(CSS2Constants.CSS_KATAKANA_IROHA_VALUE, CSS2ValueConstants.KATAKANA_IROHA_VALUE);
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
		return CSS2ValueConstants.DISC_VALUE;
	}

	public boolean isInheritedProperty() {
		return true;
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
		return CSS2Constants.CSS_LIST_STYLE_TYPE_PROPERTY;
	}
}
