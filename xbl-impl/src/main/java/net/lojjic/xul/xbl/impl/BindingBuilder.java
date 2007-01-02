package net.lojjic.xul.xbl.impl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Builder class for XBL bindings
 */
public class BindingBuilder {

	public static final String XBL_NAMESPACE = "http://www.mozilla.org/xbl";
	private BindingManager bindingManager;


	/**
	 * Constructor
	 * @param bindingManager
	 */
	public BindingBuilder(BindingManager bindingManager) {
		this.bindingManager = bindingManager;
	}


	/**
	 * Build a {@link XBLBinding} object from the given xbl:binding DOM Element.
	 *
	 * @param bindingElement The source xbl:binding {@link Element} describing the binding
	 * @return The complete XBLBinding object.
	 */
	public XBLBinding build(Element bindingElement) throws XBLException {
		XBLBinding binding = new XBLBinding();

		// @extends:
		if(bindingElement.hasAttribute("extends")) {
			binding.setParentBinding(bindingManager.getBindingForURL(bindingElement.getAttribute("extends")));
		}

		// Constructor:
		buildConstructor(binding, bindingElement);

		// Destructor:
		buildDestructor(binding, bindingElement);

		// Fields:
		buildFields(binding, bindingElement);

		// Properties:
		buildProperties(binding, bindingElement);

		// Methods:
		buildMethods(binding, bindingElement);

		// Event handlers:
		buildHandlers(binding, bindingElement);

		// Anonymous content:
		buildContent(binding, bindingElement);

		return binding;
	}


	/**
	 * Build the binding's constructor from a &lt;xbl:constructor/&gt; element
	 * @param binding
	 * @param bindingElement
	 */
	private void buildConstructor(XBLBinding binding, Element bindingElement) {
		NodeList constructorElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "constructor");
		if(constructorElements.getLength() > 0) {
			XBLConstructor constructor = new XBLConstructor();
			constructor.setBody(constructorElements.item(0).getTextContent());
			binding.setConstructor(constructor);
		}
	}

	/**
	 * Build the binding's destructor from a &lt;xbl:destructor/&gt; element
	 * @param binding
	 * @param bindingElement
	 */
	private void buildDestructor(XBLBinding binding, Element bindingElement) {
		NodeList destructorElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "destructor");
		if(destructorElements.getLength() > 0) {
			XBLDestructor destructor = new XBLDestructor();
			destructor.setBody(destructorElements.item(0).getTextContent());
			binding.setDestructor(destructor);
		}
	}

	/**
	 * Build the binding's fields from &lt;xbl:field/&gt; elements
	 * @param binding
	 * @param bindingElement
	 */
	private void buildFields(XBLBinding binding, Element bindingElement) {
		NodeList fieldElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "field");
		for(int i = 0; i < fieldElements.getLength(); i++) {
			Element element = (Element)fieldElements.item(i);

			XBLField field = new XBLField();
			field.setName(element.getAttribute("name"));
			field.setInitializer(element.getTextContent());

			binding.addField(field);
		}
	}

	/**
	 * Build the binding's properties from &lt;xbl:property/&gt; elements
	 * @param binding
	 * @param bindingElement
	 */
	private void buildProperties(XBLBinding binding, Element bindingElement) {
		NodeList propertyElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "property");
		for(int i = 0; i < propertyElements.getLength(); i++) {
			Element element = (Element)propertyElements.item(i);

			XBLProperty property = new XBLProperty();
			property.setName(element.getAttribute("name"));
			property.setReadonly(Boolean.valueOf(element.getAttribute("readonly")));

			String getter = element.getAttribute("onget");
			if(getter == null) {
				NodeList getterNodeList = element.getElementsByTagNameNS(XBL_NAMESPACE, "getter");
				if(getterNodeList.getLength() > 0) {
					getter = getterNodeList.item(0).getTextContent();
				}
			}
			property.setGetter(getter);

			String setter = element.getAttribute("onset");
			if(setter == null) {
				NodeList setterNodeList = element.getElementsByTagNameNS(XBL_NAMESPACE, "setter");
				if(setterNodeList.getLength() > 0) {
					setter = setterNodeList.item(0).getTextContent();
				}
			}
			property.setSetter(setter);

			binding.addProperty(property);
		}
	}

	/**
	 * Build the binding's methods from &lt;xbl:method/&gt; elements
	 * @param binding
	 * @param bindingElement
	 */
	private void buildMethods(XBLBinding binding, Element bindingElement) {
		NodeList methodElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "method");
		for(int i = 0; i < methodElements.getLength(); i++) {
			Element element = (Element)methodElements.item(i);

			XBLMethod method = new XBLMethod();
			method.setName(element.getAttribute("name"));
			method.setType(element.getAttribute("type"));

			NodeList parameterElements = element.getElementsByTagNameNS(XBL_NAMESPACE, "parameters");
			String[] parameters = new String[parameterElements.getLength()];
			for(int j = 0; j < parameterElements.getLength(); j++) {
				parameters[j] = ((Element)parameterElements.item(j)).getAttribute("name");
			}
			method.setParameters(parameters);

			String body = element.getElementsByTagNameNS(XBL_NAMESPACE, "body").item(0).getTextContent();
			method.setBody(body);

			binding.addMethod(method);
		}
	}

	/**
	 * Build the binding's event handlers from &lt;xbl:handler/&gt; elements
	 * @param binding
	 * @param bindingElement
	 */
	private void buildHandlers(XBLBinding binding, Element bindingElement) {
		NodeList handlerElements = bindingElement.getElementsByTagNameNS(XBL_NAMESPACE, "handler");
		for(int i = 0; i < handlerElements.getLength(); i++) {
			Element element = (Element)handlerElements.item(i);
			XBLHandler handler = new XBLHandler();
			handler.setEventType(element.getAttribute("event"));
			handler.setPhase(element.getAttribute("phase"));
			handler.setAttachTo(element.getAttribute("attachto"));
			handler.setButton(Integer.valueOf(element.getAttribute("button")));
			handler.setModifiers(element.getAttribute("modifiers"));
			handler.setKeyCode(element.getAttribute("keycode"));
			handler.setCharCode(element.getAttribute("charcode"));
			handler.setType(element.getAttribute("type"));

			String action = element.getAttribute("action");
			if(action == null) {
				action = element.getTextContent();
			}
			handler.setAction(action);

			binding.addHandler(handler);
		}
	}

	/**
	 * Build the binding's anonymous content tree from the &lt;xbl:content/&gt; element
	 * @param binding
	 * @param bindingElement
	 */
	private void buildContent(XBLBinding binding, Element bindingElement) {
		// TODO
	}


}
