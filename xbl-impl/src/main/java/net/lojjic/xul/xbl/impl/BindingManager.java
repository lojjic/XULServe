package net.lojjic.xul.xbl.impl;

import org.w3c.dom.*;
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

	private Map<String, XBLBinding> bindings = new HashMap<String, XBLBinding>();
	private Map<String, Document> bindingDocuments = new HashMap<String, Document>();

	private String documentImplClassName = DocumentXBLImpl.class.getName();

	private BindingBuilder bindingBuilder = new BindingBuilder(this);


	/**
	 * Set the {@link Document} implementation class to use.
	 */
	public void setDocumentImplClassName(String name) {
		this.documentImplClassName = name;
	}

	/**
	 * Get the loaded binding documents as a NamedNodeMap
	 */
	public NamedNodeMap getBindingDocuments() {
		return new NamedNodeMap() {
			private DOMException modificationException = new DOMException(
						DOMException.NO_MODIFICATION_ALLOWED_ERR, "NamedNodeMap cannot be modified.");
			public int getLength() {
				return bindingDocuments.size();
			}
			public Node getNamedItem(String name) {
				return bindingDocuments.get(name);
			}
			public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
				// TODO unsure what this should do
				return getNamedItem(localName);
			}
			public Node item(int index) {
				// TODO ensure that this will return the same item over multiple calls with the same argument
				return bindingDocuments.values().toArray(new Node[getLength()])[index];
			}
			public Node removeNamedItem(String name) throws DOMException {
				throw modificationException;
			}
			public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
				throw modificationException;
			}
			public Node setNamedItem(Node arg) throws DOMException {
				throw modificationException;
			}
			public Node setNamedItemNS(Node arg) throws DOMException {
				throw modificationException;
			}
		};
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
		NodeList bindingElements = document.getElementsByTagNameNS(BindingBuilder.XBL_NAMESPACE, "binding");
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

		return bindingBuilder.build(bindingElement);
	}


}
