package net.lojjic.xml.javascript;

import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.UserDataHandler}
 */
@JSClassName("UserDataHandler")
public class ScriptableUserDataHandler extends ScriptableDOMObject implements UserDataHandler {

	private UserDataHandler delegateUserDataHandler;

	public ScriptableUserDataHandler(Scriptable scope, UserDataHandler delegateUserDataHandler) {
		super(scope, delegateUserDataHandler);
		this.delegateUserDataHandler = delegateUserDataHandler;
	}
	
	// TODO JS Constructor

	@JSStatic @JSGetter("NODE_CLONED")
	public static short get_NODE_CLONED(ScriptableObject obj) {
		return UserDataHandler.NODE_CLONED;
	}

	@JSStatic @JSGetter("NODE_IMPORTED")
	public static short get_NODE_IMPORTED(ScriptableObject obj) {
		return UserDataHandler.NODE_IMPORTED;
	}

	@JSStatic @JSGetter("NODE_DELETED")
	public static short get_NODE_DELETED(ScriptableObject obj) {
		return UserDataHandler.NODE_DELETED;
	}

	@JSStatic @JSGetter("NODE_RENAMED")
	public static short get_NODE_RENAMED(ScriptableObject obj) {
		return UserDataHandler.NODE_RENAMED;
	}

	@JSStatic @JSGetter("NODE_ADOPTED")
	public static short get_NODE_ADOPTED(ScriptableObject obj) {
		return UserDataHandler.NODE_ADOPTED;
	}

	@JSFunction("handle")
	public void handle(short operation, String key, Object data, Node src, Node dst) {
		delegateUserDataHandler.handle(operation, key, data, src, dst);
	}
}