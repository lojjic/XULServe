package net.lojjic.xul.impl;

import net.lojjic.xul.XULButtonElement;
import net.lojjic.xul.XULElement;

/**
 * {@link net.lojjic.xul.XULButtonElement} implementation
 */
public class XULButtonElementImpl extends XULLabeledControlElementImpl implements XULButtonElement {

	/**
	 * Constructor.
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public XULButtonElementImpl(XULDocumentImpl ownerDocument, String qualifiedName) {
		super(ownerDocument, qualifiedName);
	}


	public boolean isAutoCheck() {
		return Boolean.parseBoolean(getAttribute("autoCheck"));
	}
	
	public void setAutoCheck(boolean autoCheck) {
		setAttribute("autoCheck", String.valueOf(autoCheck));
	}
	
	public boolean isChecked() {
		return Boolean.parseBoolean(getAttribute("checked"));
	}
	
	public void setChecked(boolean checked) {
		setAttribute("checked", String.valueOf(checked));
	}
	
	public short getCheckState() {
		return Short.parseShort(getAttribute("checkState"));
	}
	
	public void setCheckState(short checkState) {
		setAttribute("checkState", String.valueOf(checkState));
	}
	
	public String getDlgType() {
		return getAttribute("dlgType");
	}
	
	public void setDlgType(String dlgType) {
		setAttribute("dlgType", dlgType);
	}
	
	public String getGroup() {
		return getAttribute("group");
	}
	
	public void setGroup(String group) {
		setAttribute("group", group);
	}
	
	public boolean isOpen() {
		return Boolean.parseBoolean(getAttribute("open"));
	}
	
	public void setOpen(boolean open) {
		setAttribute("open", String.valueOf(open));
	}
	
	public String getType() {
		return getAttribute("type");
	}
	
	public void setType(String type) {
		setAttribute("type", type);
	}


	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULButtonElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
	
}
