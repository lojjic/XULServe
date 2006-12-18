package net.lojjic.xul;

import org.w3c.dom.Element;

public interface XULTreeElement extends XULElement {

	TreeColumns getColumns(); //readonly
	
	TreeView getView();
	void setView(TreeView view);
	
	Element getBody(); //readonly
	
	boolean isEditable();
	void setEditable(boolean editable);
	
	// For editable trees only
	XULTextBoxElement getInputField(); //readonly
	
}
