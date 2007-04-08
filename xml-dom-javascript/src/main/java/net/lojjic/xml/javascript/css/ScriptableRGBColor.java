package net.lojjic.xml.javascript.css;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.css.RGBColor;

/**
 * Scriptable wrapper for {@link org.w3c.dom.css.RGBColor}
 */
@JSClassName("RGBColor")
public class ScriptableRGBColor<T extends RGBColor> extends ScriptableDOMObject<T> {

	public ScriptableRGBColor() {
	}

	public ScriptableRGBColor(Scriptable scope, T rgbColor) {
		super(scope, rgbColor);
	}

	@JSGetter("blue")
	public Object getBlue() {
		return convertReturnValue(unwrap().getBlue());
	}

	@JSGetter("green")
	public Object getGreen() {
		return convertReturnValue(unwrap().getGreen());
	}

	@JSGetter("red")
	public Object getRed() {
		return convertReturnValue(unwrap().getRed());
	}
}
