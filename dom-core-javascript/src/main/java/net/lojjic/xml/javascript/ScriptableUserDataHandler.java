package net.lojjic.xml.javascript;

import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Node;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;

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

	@JSFunction("handle")
	public void handle(short operation, String key, Object data, Node src, Node dst) {
		delegateUserDataHandler.handle(operation, key, data, src, dst);
	}
}
