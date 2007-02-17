package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.DOMException;

import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSConstructor;

/**
 * Scriptable wrapper for {@link org.w3c.dom.DOMException}
 */
public class ScriptableDOMException extends ScriptableDOMObject {

	private DOMException delegateDOMException;

	public ScriptableDOMException(Scriptable scope, DOMException delegateDOMException) {
		super(scope, delegateDOMException);
		this.delegateDOMException = delegateDOMException;
	}

	@JSGetter("code")
	public short getCode() {
		return delegateDOMException.code;
	}

	@JSSetter("code")
	public void setCode(short code) {
		delegateDOMException.code = code;
	}

	@JSStatic @JSGetter("INDEX_SIZE_ERR")
	public static short get_INDEX_SIZE_ERROR(ScriptableObject obj) {
		return DOMException.INDEX_SIZE_ERR;
	}

	@JSStatic @JSGetter("DOMSTRING_SIZE_ERR")
	public static short get_DOMSTRING_SIZE_ERR(ScriptableObject obj) {
		return DOMException.DOMSTRING_SIZE_ERR;
	}

	@JSStatic @JSGetter("HIERARCHY_REQUEST_ERR")
	public static short get_HIERARCHY_REQUEST_ERR(ScriptableObject obj) {
		return DOMException.HIERARCHY_REQUEST_ERR;
	}

	@JSStatic @JSGetter("WRONG_DOCUMENT_ERR")
	public static short get_WRONG_DOCUMENT_ERR(ScriptableObject obj) {
		return DOMException.WRONG_DOCUMENT_ERR;
	}

	@JSStatic @JSGetter("INVALID_CHARACTER_ERR")
	public static short get_INVALID_CHARACTER_ERR(ScriptableObject obj) {
		return DOMException.INVALID_CHARACTER_ERR;
	}

	@JSStatic @JSGetter("NO_DATA_ALLOWED_ERR")
	public static short get_NO_DATA_ALLOWED_ERR(ScriptableObject obj) {
		return DOMException.NO_DATA_ALLOWED_ERR;
	}

	@JSStatic @JSGetter("NO_MODIFICATION_ALLOWED_ERR")
	public static short get_NO_MODIFICATION_ALLOWED_ERR(ScriptableObject obj) {
		return DOMException.NO_MODIFICATION_ALLOWED_ERR;
	}

	@JSStatic @JSGetter("NOT_FOUND_ERR")
	public static short get_NOT_FOUND_ERR(ScriptableObject obj) {
		return DOMException.NOT_FOUND_ERR;
	}

	@JSStatic @JSGetter("NOT_SUPPORTED_ERR")
	public static short get_NOT_SUPPORTED_ERR(ScriptableObject obj) {
		return DOMException.NOT_SUPPORTED_ERR;
	}

	@JSStatic @JSGetter("INUSE_ATTRIBUTE_ERR")
	public static short get_INUSE_ATTRIBUTE_ERR(ScriptableObject obj) {
		return DOMException.INUSE_ATTRIBUTE_ERR;
	}

	@JSStatic @JSGetter("INVALID_STATE_ERR")
	public static short get_INVALID_STATE_ERR(ScriptableObject obj) {
		return DOMException.INVALID_STATE_ERR;
	}

	@JSStatic @JSGetter("SYNTAX_ERR")
	public static short get_SYNTAX_ERR(ScriptableObject obj) {
		return DOMException.SYNTAX_ERR;
	}

	@JSStatic @JSGetter("INVALID_MODIFICATION_ERR")
	public static short get_INVALID_MODIFICATION_ERR(ScriptableObject obj) {
		return DOMException.INVALID_MODIFICATION_ERR;
	}

	@JSStatic @JSGetter("NAMESPACE_ERR")
	public static short get_NAMESPACE_ERR(ScriptableObject obj) {
		return DOMException.NAMESPACE_ERR;
	}

	@JSStatic @JSGetter("INVALID_ACCESS_ERR")
	public static short get_INVALID_ACCESS_ERR(ScriptableObject obj) {
		return DOMException.INVALID_ACCESS_ERR;
	}

	@JSStatic @JSGetter("VALIDATION_ERR")
	public static short get_VALIDATION_ERR(ScriptableObject obj) {
		return DOMException.VALIDATION_ERR;
	}

	@JSStatic @JSGetter("TYPE_MISMATCH_ERR")
	public static short get_TYPE_MISMATCH_ERR(ScriptableObject obj) {
		return DOMException.TYPE_MISMATCH_ERR;
	}
}
