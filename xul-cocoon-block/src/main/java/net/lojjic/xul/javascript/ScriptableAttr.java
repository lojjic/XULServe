package net.lojjic.xul.javascript;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

public class ScriptableAttr extends ScriptableNode {

	protected Attr delegateAttr;
	
	
	public ScriptableAttr(Attr attr) {
		super(attr);
		this.delegateAttr = attr;
	}
	
	
	@Override
	public String getClassName() {
		return "Attr";
	}

	

}
