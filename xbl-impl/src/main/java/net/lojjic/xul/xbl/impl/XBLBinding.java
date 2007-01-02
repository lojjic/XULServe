package net.lojjic.xul.xbl.impl;

import org.w3c.dom.DocumentFragment;
import org.apache.commons.lang.ArrayUtils;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class modeling an XBL binding.
 */
public class XBLBinding {

	private XBLBinding parentBinding;
	private Object constructor;
	private HashMap<String, XBLField> fields = new HashMap<String, XBLField>();
	private HashMap<String, XBLProperty> properties = new HashMap<String, XBLProperty>();
	private HashMap<String, XBLMethod> methods = new HashMap<String, XBLMethod>();
	private ArrayList<XBLHandler> handlers = new ArrayList<XBLHandler>();
	private DocumentFragment content;


	public XBLBinding getParentBinding() {
		return parentBinding;
	}

	public void setParentBinding(XBLBinding parentBinding) {
		this.parentBinding = parentBinding;
	}

	public Object getConstructor() {
		return constructor;
	}

	public void setConstructor(Object constructor) {
		this.constructor = constructor;
	}

	public void addField(XBLField field) {
		fields.put(field.getName(), field);
	}

	public XBLField getField(String name) {
		XBLField field = fields.get(name);
		if(field == null && parentBinding != null) {
			field = parentBinding.getField(name);
		}
		return field;
	}

	public void addProperty(XBLProperty property) {
		properties.put(property.getName(), property);
	}

	public XBLProperty getProperty(String name) {
		XBLProperty property = properties.get(name);
		if(property == null && parentBinding != null) {
			property = parentBinding.getProperty(name);
		}
		return property;
	}

	public void addMethod(XBLMethod method) {
		methods.put(method.getName(), method);
	}

	public XBLMethod getMethod(String name) {
		XBLMethod method = methods.get(name);
		if(method == null && parentBinding != null) {
			method = parentBinding.getMethod(name);
		}
		return method;
	}

	public void addHandler(XBLHandler handler) {
		handlers.add(handler);
	}

	public XBLHandler[] getHandlers() {
		XBLHandler[] parentHandlers = parentBinding.getHandlers();
		XBLHandler[] ourHandlers = handlers.toArray(new XBLHandler[handlers.size()]);
		return (XBLHandler[])ArrayUtils.addAll(parentHandlers, ourHandlers);
	}

	public DocumentFragment getContent() {
		return content;
	}

	public void setContent(DocumentFragment content) {
		this.content = content;
	}
}
