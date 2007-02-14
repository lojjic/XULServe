package net.lojjic.xml.javascript.events;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Node;
import org.w3c.dom.events.MutationEvent;

/**
 * Scriptable wrapper for {@link org.w3c.dom.events.MutationEvent}
 */
@JSClassName("MutationEvent")
public class ScriptableMutationEvent extends ScriptableEvent implements MutationEvent {
	
	protected MutationEvent delegateMutationEvent;

	public ScriptableMutationEvent(Scriptable scope, MutationEvent event) {
		super(scope, event);
		this.delegateMutationEvent = event;
	}
	
	/* TODO static properties:
	static short 	ADDITION
    The Attr was just added.
    static short 	MODIFICATION
    The Attr was modified in place.
    static short 	REMOVAL
    The Attr was just removed.
    */
	
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
	public Node getRelatedNode() {
		return delegateMutationEvent.getRelatedNode();
	}

	@JSFunction("initMutationEvent")
	public void initMutationEvent(String type, boolean canBubble, boolean cancelable,
			Node relatedNode, String prevValue, String newValue, String attrName, short attrChange) {
		delegateMutationEvent.initMutationEvent(type, canBubble, cancelable,
				relatedNode, prevValue, newValue, attrName, attrChange);
	}
	
}
