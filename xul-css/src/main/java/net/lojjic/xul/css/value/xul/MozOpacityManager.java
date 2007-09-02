package net.lojjic.xul.css.value.xul;

import net.lojjic.xul.css.XULCSSConstants;
import org.apache.batik.css.engine.value.svg.OpacityManager;

/**
 * Manager for XUL '-moz-opacity' property
 */
public class MozOpacityManager extends OpacityManager {

	/**
	 * Creates a new OpacityManager.
	 */
	public MozOpacityManager() {
		super(XULCSSConstants.CSS_MOZ_OPACITY_PROPERTY, false);
	}

}
