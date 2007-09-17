package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;
import net.lojjic.xul.css.value.XULValueConstants;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.ValueConstants;
import org.apache.batik.css.engine.value.css2.DisplayManager;
import org.apache.batik.util.CSSConstants;

/**
 * Manager for the CSS 'display' property with XUL-specific values
 */
public class XULDisplayManager extends DisplayManager {

	/**
	 * The identifier values.
	 */
	protected final static StringMap values = new StringMap(DisplayManager.values);
	static {
		values.put(XULCSSConstants.CSS_MOZ_BOX_VALUE, XULValueConstants.MOZ_BOX_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_DECK_VALUE, XULValueConstants.MOZ_DECK_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_GRID_GROUP_VALUE, XULValueConstants.MOZ_GRID_GROUP_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_GRID_LINE_VALUE, XULValueConstants.MOZ_GRID_LINE_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_GRID_VALUE, XULValueConstants.MOZ_GRID_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_GROUPBOX_VALUE, XULValueConstants.MOZ_GROUPBOX_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_INLINE_BOX_VALUE, XULValueConstants.MOZ_INLINE_BOX_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_INLINE_GRID_VALUE, XULValueConstants.MOZ_INLINE_GRID_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_INLINE_STACK_VALUE, XULValueConstants.MOZ_INLINE_STACK_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_POPUP_VALUE, XULValueConstants.MOZ_POPUP_VALUE);
		values.put(XULCSSConstants.CSS_MOZ_STACK_VALUE, XULValueConstants.MOZ_STACK_VALUE);
	}

	public StringMap getIdentifiers() {
		return values;
	}

}
