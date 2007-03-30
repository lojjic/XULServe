package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.xul.XULImageElement;
import org.mozilla.javascript.Scriptable;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULImageElement}
 * }
 */
public class ScriptableXULImageElement extends ScriptableXULElement {

	private XULImageElement delegateXULImageElement;

	public ScriptableXULImageElement() {
		super();
	}

	public ScriptableXULImageElement(Scriptable scope, XULImageElement delegateXULImageElement) {
		super(scope, delegateXULImageElement);
		this.delegateXULImageElement = delegateXULImageElement;
	}

	@JSGetter("src")
	public String getSrc() {
		return delegateXULImageElement.getSrc();
	}

	@JSSetter("src")
	public void setSrc(String src) {
		delegateXULImageElement.setSrc(src);
	}
}
