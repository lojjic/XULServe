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


	/**
	 * Set the {@link Document} implementation class to use.
	 */
	public void setDocumentImplClassName(String name) {
		this.documentImplClassName = name;
	}


	/**
	 * Get a {@link XBLBinding} object for the XBL binding at the given URL. If the
	 * binding for that URL has already been built, a cached instance is returned.
	 *
	 * @param url The URL of the binding to build; must include a fragment
	 *            identifier pointing to the ID of the particular binding element.
	 * @return The XBLBinding object
	 * @throws XBLException
	 */
	public XBLBinding getBindingForURL(String url) throws XBLException {
		XBLBinding binding = bindings.get(url);
		if(binding == null) {
			binding = buildBinding(url);
			bindings.put(url, binding);
		}
		return binding;
	}


	/**
	 * Load and parse the XBL Document at the given URL. It is cached for later
	 * use. The Document can also be modified by the application via DOM code,
	 * and subsequent binding applications will use the updated Document.
	 *
	 * @param url The URL of the binding or binding document; it may or may not
	 *            include the fragment identifier of the particular binding.
	 * @return The constructed DOM Document
	 * @throws XBLException
	 */
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


	/**
	 * Utility to get the fragment identifier (the part after '#') from the given URL.
	 *
	 * @param fullURL The full URL containing the fragment identifier
	 * @return The fragment identifier, or null if none found
	 */
	private String getFragmentId(String fullURL) {
		int hashIndex = fullURL.indexOf("#");
		if(hashIndex == -1) {
			return null;
		}
		return fullURL.substring(hashIndex + 1);
	}


	/**
	 * Utility to remove the fragment identifier (the part after '#') from the given URL.
	 *
	 * @param fullURL The full URL containing the fragment identifier
	 * @return The URL with the fragment identifier removed
	 */
	private String removeFragmentId(String fullURL) {
		int hashIndex = fullURL.indexOf("#");
		if(hashIndex == -1) {
			return fullURL;
		}
		return fullURL.substring(0, hashIndex);
	}


	/**
	 * Build a {@link XBLBinding} from the given URL.
	 *
	 * @param url
	 * @return
	 * @throws XBLException
	 */
	private XBLBinding buildBinding(String url) throws XBLException {
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
