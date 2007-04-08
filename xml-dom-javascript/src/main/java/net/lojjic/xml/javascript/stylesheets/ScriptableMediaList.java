package net.lojjic.xml.javascript.stylesheets;

import org.w3c.dom.stylesheets.MediaList;
import org.w3c.dom.DOMException;
import org.mozilla.javascript.Scriptable;
import net.lojjic.xml.javascript.ScriptableDOMObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.stylesheets.MediaList}
 */
@JSClassName("MediaList")
public class ScriptableMediaList<T extends MediaList> extends ScriptableDOMObject<T> {

	public ScriptableMediaList() {
	}

	public ScriptableMediaList(Scriptable scope, T wrappedObject) {
		super(scope, wrappedObject);
	}

	@JSFunction("appendMedium")
	public void appendMedium(String newMedium) throws DOMException {
		unwrap().appendMedium(newMedium);
	}

	@JSFunction("deleteMedium")
	public void deleteMedium(String oldMedium) throws DOMException {
		unwrap().deleteMedium(oldMedium);
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSGetter("mediaText")
	public String getMediaText() {
		return unwrap().getMediaText();
	}

	@JSFunction("item")
	public String item(int index) {
		return unwrap().item(index);
	}

	@JSSetter("mediaText")
	public void setMediaText(String mediaText) throws DOMException {
		unwrap().setMediaText(mediaText);
	}

	/**
	 * Indexed properties are shortcut to {@link #item(int)}.
	 */
	@Override
	public Object get(int index, Scriptable start) {
		return item(index);
	}
}
