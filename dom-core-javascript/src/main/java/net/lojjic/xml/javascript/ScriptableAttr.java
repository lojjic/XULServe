package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

public class ScriptableAttr extends ScriptableNode {
	
	public static String JS_CLASS_NAME = "Node";

	protected Attr delegateAttr;
	
	
	public ScriptableAttr(Scriptable scope, Attr attr) {
		super(scope, attr);
		this.delegateAttr = attr;
	}

	

}
