package net.lojjic.xul;

import org.w3c.dom.Element;

public interface XULTreeElement extends XULElement {

	//TODO TreeColumns getColumns(); //readonly
	
	//TODO TreeView getView();
	//TODO void setView(TreeView view);
	
	Element getBody(); //readonly
	
	boolean isEditable();
	void setEditable(boolean editable);
	
	// For editable trees only
	XULTextBoxElement getInputField(); //readonly
	
}
