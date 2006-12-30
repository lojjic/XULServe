package net.lojjic.xul.impl;

import org.w3c.dom.Node;

import net.lojjic.xul.XULTextBoxElement;

/**
 * {@link net.lojjic.xul.XULTextBoxElement} implementation
 */
public class XULTextBoxElementImpl extends XULElementImpl implements XULTextBoxElement {

	private Node inputField;
	private long maxLength;
	private String value;
	private long selectionStart;
	private long selectionEnd;
	private String type;
	private boolean disabled;
	private long tabIndex;
	
	
	public XULTextBoxElementImpl(XULDocumentImpl ownerDocument, String qualifiedName) {
		super(ownerDocument, qualifiedName);
	}
	

	public Node getInputField() {
		return inputField;
	}

	public long getTextLength() {
		return value.length();
	}

	public long getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(long maxLength) {
		this.maxLength = maxLength;
	}

	public long getSelectionStart() {
		return selectionStart;
	}

	public void setSelectionStart(long selectionStart) {
		this.selectionStart = selectionStart;
	}

	public long getSelectionEnd() {
		return selectionEnd;
	}

	public void setSelectionEnd(long selectionEnd) {
		this.selectionEnd = selectionEnd;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void select() {
		// TODO
	}

	public void setSelectionRange(long selectionStart, long selectionEnd) {
		this.selectionStart = selectionStart;
		this.selectionEnd = selectionEnd;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public long getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(long tabIndex) {
		this.tabIndex = tabIndex;
	}

}
