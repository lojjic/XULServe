package net.lojjic.xul.impl;

import net.lojjic.xul.XULButtonElement;

public class XULButtonElementImpl extends XULElementImpl implements XULButtonElement {

	private String type;
	private String dlgType;
	private boolean open;
	private boolean checked;
	private short checkState;
	private boolean autoCheck;
	private String group;
	private String crop;
	private String image;
	private String label;
	private String accessKey;
	private String command;
	private boolean disabled;
	private long tabIndex;
	
	
	public XULButtonElementImpl(XULDocumentImpl ownerDocument, String localName) {
		super(ownerDocument, localName);
	}

	
	public String getAccessKey() {
		return accessKey;
	}
	
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public boolean isAutoCheck() {
		return autoCheck;
	}
	
	public void setAutoCheck(boolean autoCheck) {
		this.autoCheck = autoCheck;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public short getCheckState() {
		return checkState;
	}
	
	public void setCheckState(short checkState) {
		this.checkState = checkState;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCrop() {
		return crop;
	}
	
	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public String getDlgType() {
		return dlgType;
	}
	
	public void setDlgType(String dlgType) {
		this.dlgType = dlgType;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public long getTabIndex() {
		return tabIndex;
	}
	
	public void setTabIndex(long tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
