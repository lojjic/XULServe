package net.lojjic.xul.xbl.impl;

/**
 * Representation of a XBL method
 * <p>
 * The method body is initially stored simply as a String. It is expected
 * that the consumer of the method (i.e. scripting environment) will
 * compile that String into its executable form when first requested,
 * and then replace the String with that compiled form.
 */
public class XBLMethod {

	private String name;
	private String[] parameters;
	private Object body;
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
