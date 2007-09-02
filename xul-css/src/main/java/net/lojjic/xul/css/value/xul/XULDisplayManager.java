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
	protected final static StringMap values = new StringMap();
	static {
		// CSS2 display values:
		values.put(CSSConstants.CSS_BLOCK_VALUE, ValueConstants.BLOCK_VALUE);
		values.put(CSSConstants.CSS_COMPACT_VALUE, ValueConstants.COMPACT_VALUE);
		values.put(CSSConstants.CSS_INLINE_VALUE, ValueConstants.INLINE_VALUE);
		values.put(CSSConstants.CSS_INLINE_TABLE_VALUE, ValueConstants.INLINE_TABLE_VALUE);
		values.put(CSSConstants.CSS_LIST_ITEM_VALUE, ValueConstants.LIST_ITEM_VALUE);
		values.put(CSSConstants.CSS_MARKER_VALUE, ValueConstants.MARKER_VALUE);
		values.put(CSSConstants.CSS_NONE_VALUE, ValueConstants.NONE_VALUE);
		values.put(CSSConstants.CSS_RUN_IN_VALUE, ValueConstants.RUN_IN_VALUE);
		values.put(CSSConstants.CSS_TABLE_VALUE, ValueConstants.TABLE_VALUE);
		values.put(CSSConstants.CSS_TABLE_CAPTION_VALUE, ValueConstants.TABLE_CAPTION_VALUE);
		values.put(CSSConstants.CSS_TABLE_CELL_VALUE, ValueConstants.TABLE_CELL_VALUE);
		values.put(CSSConstants.CSS_TABLE_COLUMN_VALUE, ValueConstants.TABLE_COLUMN_VALUE);
		values.put(CSSConstants.CSS_TABLE_COLUMN_GROUP_VALUE, ValueConstants.TABLE_COLUMN_GROUP_VALUE);
		values.put(CSSConstants.CSS_TABLE_FOOTER_GROUP_VALUE, ValueConstants.TABLE_FOOTER_GROUP_VALUE);
		values.put(CSSConstants.CSS_TABLE_HEADER_GROUP_VALUE, ValueConstants.TABLE_HEADER_GROUP_VALUE);
		values.put(CSSConstants.CSS_TABLE_ROW_VALUE, ValueConstants.TABLE_ROW_VALUE);
		values.put(CSSConstants.CSS_TABLE_ROW_GROUP_VALUE, ValueConstants.TABLE_ROW_GROUP_VALUE);

		// XUL display values:
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
