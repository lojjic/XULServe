package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

/**
 * Scriptable wrapper for {@link org.w3c.dom.UserDataHandler}
 */
@JSClassName("UserDataHandler")
public class ScriptableUserDataHandler<T extends UserDataHandler> extends ScriptableDOMObject<T> {

	public ScriptableUserDataHandler() {
		super();
	}

	public ScriptableUserDataHandler(Scriptable scope, T userDataHandler) {
		super(scope, userDataHandler);
	}
	
	// TODO JS Constructor

	@JSStatic @JSGetter("NODE_CLONED")
	public static int get_NODE_CLONED(ScriptableObject obj) {
		return UserDataHandler.NODE_CLONED;
	}

	@JSStatic @JSGetter("NODE_IMPORTED")
	public static int get_NODE_IMPORTED(ScriptableObject obj) {
		return UserDataHandler.NODE_IMPORTED;
	}

	@JSStatic @JSGetter("NODE_DELETED")
	public static int get_NODE_DELETED(ScriptableObject obj) {
		return UserDataHandler.NODE_DELETED;
	}

	@JSStatic @JSGetter("NODE_RENAMED")
	public static int get_NODE_RENAMED(ScriptableObject obj) {
		return UserDataHandler.NODE_RENAMED;
	}

	@JSStatic @JSGetter("NODE_ADOPTED")
	public static int get_NODE_ADOPTED(ScriptableObject obj) {
		return UserDataHandler.NODE_ADOPTED;
	}

	@JSFunction("handle")
	public void handle(int operation, String key, Object data, Object src, Object dst) {
		unwrap().handle((short)operation, key, data, convertArg(src, Node.class), convertArg(dst, Node.class));
	}
}
