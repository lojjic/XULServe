package net.lojjic.xul.xbl.impl;

/**
 * XBL property with getter/setter
 */
public class XBLProperty {

	private String name;
	private boolean readonly;
	private Object getter; // TODO make Javascript function
	private Object setter; // TODO make Javascript function


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public Object getGetter() {
		return getter;
	}

	public void setGetter(Object getter) {
		this.getter = getter;
	}

	public Object getSetter() {
		return setter;
	}

	public void setSetter(Object setter) {
		this.setter = setter;
	}
}
