package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Context;
import org.w3c.dom.Node;
import org.w3c.dom.events.MutationEvent;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.MutationEvent}
 */
@JSClassName("MutationEvent")
public class ScriptableMutationEvent<T extends MutationEvent> extends ScriptableEvent<T> {

	public ScriptableMutationEvent() {
		super();
	}

	public ScriptableMutationEvent(Scriptable scope, T event) {
		super(scope, event);
	}

	@JSStatic @JSGetter("ADDITION")
	public static int get_ADDITION(ScriptableObject obj) {
		return MutationEvent.ADDITION;
	}

	@JSStatic @JSGetter("MODIFICATION")
	public static int get_MODIFICATION(ScriptableObject obj) {
		return MutationEvent.MODIFICATION;
	}

	@JSStatic @JSGetter("REMOVAL")
	public static int get_REMOVAL(ScriptableObject obj) {
		return MutationEvent.REMOVAL;
	}

	@JSGetter("attrChange")
	public int getAttrChange() {
		return unwrap().getAttrChange();
	}
	
	@JSGetter("attrName")
	public String getAttrName() {
		return unwrap().getAttrName();
	}
	
	@JSGetter("newValue")
	public String getNewValue() {
		return unwrap().getNewValue();
	}
	
	@JSGetter("prevValue")
	public String getPrevValue() {
		return unwrap().getPrevValue();
	}
	
	@JSGetter("relatedNode")
	public Object getRelatedNode() {
		return Context.javaToJS(unwrap().getRelatedNode(), getParentScope());
	}

	@JSFunction("initMutationEvent")
	public void initMutationEvent(String type, boolean canBubble, boolean cancelable,
			Object relatedNode, String prevValue, String newValue, String attrName, int attrChange) {
		unwrap().initMutationEvent(type, canBubble, cancelable,
				convertArg(relatedNode, Node.class), prevValue, newValue, attrName, (short)attrChange);
	}
	
}
