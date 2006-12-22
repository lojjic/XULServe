package net.lojjic.xul.impl;

import net.lojjic.xul.XULLabelElement;

public class XULLabelElementImpl extends XULElementImpl implements XULLabelElement {

	private String accessKey;
	private String control;
	private boolean disabled;
	private boolean crop;
	private String value;
	
	
	public XULLabelElementImpl(XULDocumentImpl ownerDocument, String localName) {
		super(ownerDocument, localName);
	}
	
	
	public String getAccessKey() {
		return accessKey;
	}
	
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public String getControl() {
		return control;
	}
	
	public void setControl(String control) {
		this.control = control;
	}
	
	public boolean isCrop() {
		return crop;
	}
	
	public void setCrop(boolean crop) {
		this.crop = crop;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}	

}
