package net.lojjic.xul.xbl.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing XBL bindings.
 */
public class BindingManager {

	public static final String XBL_NAMESPACE = "http://www.mozilla.org/xbl";

	private Map<String, XBLBinding> bindings = new HashMap<String, XBLBinding>();
	private Map<String, Document> bindingDocuments = new HashMap<String, Document>();

	private String documentImplClassName = DocumentXBLImpl.class.getName();

	public void setDocumentImplClassName(String name) {
		this.documentImplClassName = name;
	}

	public XBLBinding getBindingForURL(String url) throws XBLException {
		XBLBinding binding = bindings.get(url);
		if(binding == null) {
			binding = createBinding(url);
			bindings.put(url, binding);
		}
		return binding;
	}

	public Document loadBindingDocument(String url) throws XBLException {
		final String docURL = removeFragmentId(url);
		Document document = bindingDocuments.get(docURL);
		if(document == null) {
			try {
				InputStream input = null; //TODO
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setAttribute("http://apache.org/xml/properties/dom/document-class-name", documentImplClassName);
				DocumentBuilder builder = factory.newDocumentBuilder();
				document = builder.parse(input);
			}
			catch (IOException e) {
				throw new XBLException("IOException loading the XBL binding document.", e);
			}
			catch (ParserConfigurationException e) {
				throw new XBLException("ParserConfigurationException loading the XBL binding document.", e);
			}
			catch (SAXException e) {
				throw new XBLException("SAXException loading the XBL binding document.", e);
			}

			// Add an event listener to remove the cached XBLBinding object when the
			// underlying XBL Document is modified:
			if(document.getImplementation().hasFeature("MutationEvents", null)) {
				EventListener listener = new EventListener() {
					public void handleEvent(Event evt) {
						bindings.remove(docURL);
					}
				};
				((EventTarget)document).addEventListener("DOMSubtreeModified", listener, false);
			}

			bindingDocuments.put(docURL, document);
		}

		return document;
	}


	private String getFragmentId(String fullURL) {
		int hashIndex = fullURL.indexOf("#");
		if(hashIndex == -1) {
			return null;
		}
		return fullURL.substring(hashIndex + 1);
	}


	private String removeFragmentId(String fullURL) {
		int hashIndex = fullURL.indexOf("#");
		if(hashIndex == -1) {
			return fullURL;
		}
		return fullURL.substring(0, hashIndex);
	}

	private XBLBinding createBinding(String url) throws XBLException {
		Document document = loadBindingDocument(url);
		String fragmentId = getFragmentId(url);

		Element bindingElement = null;
		NodeList bindingElements = document.getElementsByTagNameNS(XBL_NAMESPACE, "binding");
		for(int i = 0; i < bindingElements.getLength(); i++) {
			Element currentElement = (Element)bindingElements.item(i);
			if(fragmentId.equals(currentElement.getAttribute("id"))) {
				bindingElement = currentElement;
				break;
			}
		}
		if(bindingElement == null) {
			throw new XBLException("Could not find binding element with ID " + fragmentId);
		}

		XBLBinding binding = new XBLBinding();

		// @extends:
		if(bindingElement.hasAttribute("extends")) {
			binding.setParentBinding(getBindingForURL(bindingElement.getAttribute("extends")));
		}

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


	private void buildContent(XBLBinding binding, Element bindingElement) {
		// TODO
	}

}
