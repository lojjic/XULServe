package net.lojjic.xul;

import org.w3c.dom.Node;

public interface XULTextBoxElement extends XULControlElement {

	/**
	 * inputField may be any type of editable field, such as an
	 * HTML <input type="text"> or <textarea>
	 */
	Node getInputField(); //readonly
	
	long getTextLength(); //readonly
	
	long getMaxLength();
	void setMaxLength(long maxLength);
	
	long getSelectionStart();
	void setSelectionStart(long selectionStart);
	
	long getSelectionEnd();
	void setSelectionEnd(long selectionEnd);
	
	String getValue();
	void setValue(String value);
	
	String getType();
	void setType(String type);
	
	void select();
	
	void setSelectionRange(long selectionStart, long selectionEnd);
	
}
