package net.lojjic.xul.css.value.css2;

import net.lojjic.xul.css.CSS2Constants;
import org.apache.batik.css.engine.value.IdentifierManager;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.ValueConstants;
import org.apache.batik.util.SVGTypes;

/**
 * Manager for CSS2 'background-attachment' property
 */
public class BackgroundAttachmentManager extends IdentifierManager {

	public static final StringMap values = new StringMap();
	static {
		values.put(CSS2Constants.CSS_SCROLL_VALUE, CSS2ValueConstants.SCROLL_VALUE);
		values.put(CSS2Constants.CSS_FIXED_VALUE, CSS2ValueConstants.FIXED_VALUE);
	}

	public StringMap getIdentifiers() {
		return values;
	}

	public Value getDefaultValue() {
		return ValueConstants.SCROLL_VALUE;
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

	public String getPropertyName() {
		return CSS2Constants.CSS_BACKGROUND_ATTACHMENT_PROPERTY;
	}

}
