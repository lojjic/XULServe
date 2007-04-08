package net.lojjic.xml.javascript;

import org.w3c.dom.Entity;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Entity}
 */
@JSClassName("Entity")
public class ScriptableEntity<T extends Entity> extends ScriptableNode<T> {

	public ScriptableEntity() {
		super();
	}

	public ScriptableEntity(Scriptable scope, T entity) {
		super(scope, entity);
	}

	@JSGetter("inputEncoding")
	public String getInputEncoding() {
		return unwrap().getInputEncoding();
	}

	@JSGetter("notationName")
	public String getNotationName() {
		return unwrap().getNotationName();
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return unwrap().getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return unwrap().getSystemId();
	}

	@JSGetter("xmlEncoding")
	public String getXmlEncoding() {
		return unwrap().getXmlEncoding();
	}

	@JSGetter("xmlVersion")
	public String getXmlVersion() {
		return unwrap().getXmlVersion();
	}
}
