package net.lojjic.xul.impl;

import net.lojjic.xul.XULLabelElement;
import net.lojjic.xul.XULElement;

/**
 * {@link net.lojjic.xul.XULLabelElement} implementation
 */
public class XULLabelElementImpl extends XULDescriptionElementImpl implements XULLabelElement {

	/**
	 * Constructor
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public XULLabelElementImpl(XULDocumentImpl ownerDocument, String qualifiedName) {
		super(ownerDocument, qualifiedName);
	}
	
	
	public String getAccessKey() {
		return getAttribute("accesskey");
	}
	
	public void setAccessKey(String accessKey) {
		setAttribute("accesskey", accessKey);
	}
	
	public String getControl() {
		return getAttribute("control");
	}
	
	public void setControl(String control) {
		setAttribute("control", control);
	}
	
	public boolean isCrop() {
		return Boolean.parseBoolean(getAttribute("crop"));
	}
	
	public void setCrop(boolean crop) {
		setAttribute("crop", String.valueOf(crop));
	}
	
	public boolean isDisabled() {
		return Boolean.parseBoolean(getAttribute("disabled"));
	}
	
	public void setDisabled(boolean disabled) {
		setAttribute("disabled", String.valueOf(disabled));
	}
	
	public String getValue() {
		return getAttribute("value");
	}
	
	public void setValue(String value) {
		setAttribute("value", value);
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULLabelElementImpl(ownerDocument, qualifiedName);
			}
		};
	}

}
