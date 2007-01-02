package net.lojjic.xul.xbl.impl;

/**
 * XBL method
 */
public class XBLMethod {

	private String name;
	private String[] parameters;
	private Object body; //TODO make a Javascript function
	private String type;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
