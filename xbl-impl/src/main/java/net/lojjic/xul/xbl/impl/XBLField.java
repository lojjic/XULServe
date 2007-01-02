package net.lojjic.xul.xbl.impl;

/**
 * XBL field
 */
public class XBLField {

	private String name;
	private Object initializer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getInitializer() {
		return initializer;
	}

	public void setInitializer(Object initializer) {
		this.initializer = initializer;
	}
}
