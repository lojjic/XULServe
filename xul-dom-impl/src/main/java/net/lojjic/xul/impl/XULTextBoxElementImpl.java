package net.lojjic.xul.impl;

import org.w3c.dom.Node;

import net.lojjic.xul.XULTextBoxElement;
import net.lojjic.xul.XULElement;

/**
 * {@link net.lojjic.xul.XULTextBoxElement} implementation
 */
public class XULTextBoxElementImpl extends XULControlElementImpl implements XULTextBoxElement {

	private Node inputField;
	private long selectionStart;
	private long selectionEnd;

	/**
	 * Constructor.
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public XULTextBoxElementImpl(XULDocumentImpl ownerDocument, String qualifiedName) {
		super(ownerDocument, qualifiedName);
	}
	

	public Node getInputField() {
		return inputField;
	}

	public long getTextLength() {
		String value = getValue();
		return (value == null) ? 0 : value.length();
	}

	public long getMaxLength() {
		return Long.parseLong(getAttribute("maxlength"));
	}

	public void setMaxLength(long maxLength) {
		setAttribute("maxlength", String.valueOf(maxLength));
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
		return getAttribute("value");
	}

	public void setValue(String value) {
		setAttribute("value", value);
	}

	public String getType() {
		return getAttribute("type");
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

	public void select() {
		// TODO
	}

	public void setSelectionRange(long selectionStart, long selectionEnd) {
		this.selectionStart = selectionStart;
		this.selectionEnd = selectionEnd;
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULTextBoxElementImpl(ownerDocument, qualifiedName);
			}
		};
	}

}
