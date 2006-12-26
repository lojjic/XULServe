package net.lojjic.xml.javascript.events;

import net.lojjic.xml.javascript.ScriptableNode;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.MutationEvent;

public class ScriptableMutationEvent extends ScriptableEvent {
	
	public static String JS_CLASS_NAME = "MutationEvent";
	
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
	
	
	public short jsGet_attrChange() {
		return delegateMutationEvent.getAttrChange();
	}
	
	public String jsGet_attrName() {
		return delegateMutationEvent.getAttrName();
	}
	
	public String jsGet_newValue() {
		return delegateMutationEvent.getNewValue();
	}
	
	public String jsGet_prevValue() {
		return delegateMutationEvent.getPrevValue();
	}
	
	public Object jsGet_relatedNode() {
		return wrap(delegateMutationEvent.getRelatedNode());
	}
	
	public void jsFunction_initMutationEvent(String type, boolean canBubble, boolean cancelable, 
			Object relatedNode, String prevValue, String newValue, String attrName, short attrChange) {
		if(relatedNode instanceof ScriptableNode) {
			relatedNode = ((ScriptableNode)relatedNode).getDelegateNode();
		}
		if(!(relatedNode instanceof Node)) {
			throw new DOMException(DOMException.TYPE_MISMATCH_ERR, 
					"Unrecognized value for 'relatedNode' argument; must of type Node.");
		}
		delegateMutationEvent.initMutationEvent(type, canBubble, cancelable, (Node)relatedNode, prevValue, newValue, attrName, attrChange);
	}
	
}
