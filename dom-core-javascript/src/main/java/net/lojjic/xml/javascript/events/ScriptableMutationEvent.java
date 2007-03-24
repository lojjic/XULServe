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
public class ScriptableMutationEvent extends ScriptableEvent {
	
	protected MutationEvent delegateMutationEvent;

	public ScriptableMutationEvent(Scriptable scope, MutationEvent event) {
		super(scope, event);
		this.delegateMutationEvent = event;
	}

	@JSStatic @JSGetter("ADDITION")
	public static short get_ADDITION(ScriptableObject obj) {
		return MutationEvent.ADDITION;
	}

	@JSStatic @JSGetter("MODIFICATION")
	public static short get_MODIFICATION(ScriptableObject obj) {
		return MutationEvent.MODIFICATION;
	}

	@JSStatic @JSGetter("REMOVAL")
	public static short get_REMOVAL(ScriptableObject obj) {
		return MutationEvent.REMOVAL;
	}

	@JSGetter("attrChange")
	public short getAttrChange() {
		return delegateMutationEvent.getAttrChange();
	}
	
	@JSGetter("attrName")
	public String getAttrName() {
		return delegateMutationEvent.getAttrName();
	}
	
	@JSGetter("newValue")
	public String getNewValue() {
		return delegateMutationEvent.getNewValue();
	}
	
	@JSGetter("prevValue")
	public String getPrevValue() {
		return delegateMutationEvent.getPrevValue();
	}
	
	@JSGetter("relatedNode")
	public Object getRelatedNode() {
		return Context.javaToJS(delegateMutationEvent.getRelatedNode(), getParentScope());
	}

	@JSFunction("initMutationEvent")
	public void initMutationEvent(String type, boolean canBubble, boolean cancelable,
			Object relatedNode, String prevValue, String newValue, String attrName, short attrChange) {
		delegateMutationEvent.initMutationEvent(type, canBubble, cancelable,
				convertArg(relatedNode, Node.class), prevValue, newValue, attrName, attrChange);
	}
	
}
