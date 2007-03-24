package net.lojjic.xml.javascript;

import org.w3c.dom.Entity;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.Entity}
 */
@JSClassName("Entity")
public class ScriptableEntity extends ScriptableNode {

	private Entity delegateEntity;

	public ScriptableEntity(Scriptable scope, Entity delegateEntity) {
		super(scope, delegateEntity);
		this.delegateEntity = delegateEntity;
	}

	@JSGetter("inputEncoding")
	public String getInputEncoding() {
		return delegateEntity.getInputEncoding();
	}

	@JSGetter("notationName")
	public String getNotationName() {
		return delegateEntity.getNotationName();
	}

	@JSGetter("publicId")
	public String getPublicId() {
		return delegateEntity.getPublicId();
	}

	@JSGetter("systemId")
	public String getSystemId() {
		return delegateEntity.getSystemId();
	}

	@JSGetter("xmlEncoding")
	public String getXmlEncoding() {
		return delegateEntity.getXmlEncoding();
	}

	@JSGetter("xmlVersion")
	public String getXmlVersion() {
		return delegateEntity.getXmlVersion();
	}
}
