package net.lojjic.xul.xbl.impl;

/**
 * XBL property with getter/setter
 * <p>
 * The getter and setter are initially stored simply as Strings. It is expected
 * that the consumer of the getter/setter (i.e. scripting environment) will
 * compile those Strings into their executable form when first requested,
 * and then replace the Strings with the compiled form.
 */
public class XBLProperty {

	private String name;
	private boolean readonly;
	private Object getter;
	private Object setter;


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
