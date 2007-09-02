package net.lojjic.xul.css.value;

import net.lojjic.xul.css.XULCSSConstants;
import net.lojjic.xul.css.value.css2.CSS2ValueConstants;
import org.apache.batik.css.engine.value.StringValue;
import org.apache.batik.css.engine.value.Value;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * Constant CSS Value instances for XUL values
 */
public interface XULValueConstants extends CSS2ValueConstants {

	Value MOZ_BOX_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_BOX_VALUE);

	Value MOZ_DECK_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_DECK_VALUE);

	Value MOZ_GRID_GROUP_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_GRID_GROUP_VALUE);

	Value MOZ_GRID_LINE_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_GRID_LINE_VALUE);

	Value MOZ_GRID_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_GRID_VALUE);

	Value MOZ_GROUPBOX_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_GROUPBOX_VALUE);

	Value MOZ_INLINE_BOX_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_INLINE_BOX_VALUE);

	Value MOZ_INLINE_GRID_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_INLINE_GRID_VALUE);

	Value MOZ_INLINE_STACK_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_INLINE_STACK_VALUE);

	Value MOZ_POPUP_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_POPUP_VALUE);

	Value MOZ_STACK_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_MOZ_STACK_VALUE);

	Value DISABLED_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_DISABLED_VALUE);

	Value ENABLED_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_ENABLED_VALUE);

	Value HORIZONTAL_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_HORIZONTAL_VALUE);

	Value IGNORE_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_IGNORE_VALUE);

	Value REVERSE_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_REVERSE_VALUE);

	Value STRETCH_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_STRETCH_VALUE);

	Value VERTICAL_VALUE = new StringValue(CSSPrimitiveValue.CSS_IDENT, XULCSSConstants.CSS_VERTICAL_VALUE);
}
