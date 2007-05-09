package net.lojjic.xul.impl;

import net.lojjic.xul.XULBuilderListener;
import net.lojjic.xul.XULConstants;
import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.impl.templates.Template;
import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFCompositeDataSourceImpl;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link XULTemplateBuilder}
 */
public class XULTemplateBuilderImpl implements XULTemplateBuilder, RDFObserver {

	protected List<XULBuilderListener> listeners = new ArrayList<XULBuilderListener>();
	protected Element rootElement;
	protected Template template;
	protected RDFCompositeDataSource database;
	protected RDFService rdfService;
	private boolean datasourceChanged = false;
	private boolean datasourceInBatchUpdate = false;

	/**
	 * Constructor
	 * @param rdfService The {@link RDFService} to use
	 * @param rootElement The root element to which the template builder is attached.
	 */
	protected XULTemplateBuilderImpl(RDFService rdfService, Element rootElement) {
		this.rdfService = rdfService;
		init(rootElement);
	}

	/**
	 * Called to initialize a XUL content builder on a particular root element. This element presumably
	 * has a "datasources" attribute, which the builder will parse to set up the template builder's datasources.
	 */
	public void init(Element element) {
		// Set the root element, adding a mutation event listener to trigger
		// a rebuild when the template DOM changes:
		rootElement = element;

		// Create the template:
		this.template = createTemplate();

		// Create the composite datasource:
		this.database = new RDFCompositeDataSourceImpl(rdfService);

		// Parse the 'datasources' attribute and load its parts into the composite datasource
		String datasources = rootElement.getAttribute("datasources");
		if(datasources == null) {
			throw new IllegalStateException("Template root element missing 'datasources' attribute.");
		}
		for(String uri : datasources.split("[\\s]+")) {
			this.database.addDataSource(rdfService.getDataSource(uri));
		}

		// Add observer to rebuild when datasource changes:
		this.database.addObserver(this);
	}

	private Template createTemplate() {
		Element templateElement;

		// If root element has a @template attribute, look for a <template/> element with that id:
		String templateId = rootElement.getAttribute("template");
		if(templateId != null) {
			templateElement = rootElement.getOwnerDocument().getElementById(templateId);
			if(templateElement == null || (XULConstants.XUL_NAMESPACE.equals(templateElement.getNamespaceURI())
					&& "template".equals(templateElement.getLocalName()))) {
				throw new RuntimeException("No <template/> element exists with id '" + templateId + "'.");
			}
		}
		// Otherwise look for a child <template/> element:
		else {
			templateElement = (Element)rootElement.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "template").item(0);
			if(templateElement == null || templateElement.getParentNode() != rootElement) {
				throw new RuntimeException("No child <template/> element was found.");
			}
		}

		// Look for an already-compiled Template instance:
		final String userDataKey = "XULTemplateBuilder#Template";
		Template template = (Template)templateElement.getUserData(userDataKey);
		if(template == null) {
			// Add a mutation event listener to trigger a rebuild when the template DOM changes:
			EventListener listener = new EventListener() {
				public void handleEvent(Event evt) {
					rebuild();
				}
			};
			((EventTarget)templateElement).addEventListener("DOMSubtreeModified", listener, false);

			// Create the Template and cache it:
			template = new Template(rdfService, templateElement);
			templateElement.setUserData(userDataKey, template, null);
		}

		return template;
	}

	/**
	 * Invoked lazily by a XUL element that needs its child content built.
	 */
	public void createContents(Element element) {
		// TODO
	}

	/**
	 * Add a listener to this template builder. The template builder holds a strong reference to the listener.
	 */
	public void addListener(XULBuilderListener listener) {
		listeners.add(listener);
	}

	/**
	 * The composite datasource that the template builder observes and uses to create content
	 */
	public RDFCompositeDataSource getDatabase() {
		return database;
	}

	/**
	 * The "root" node in the DOM to which this builder is attached
	 */
	public Element getRoot() {
		return rootElement;
	}

	/**
	 * Force the template builder to rebuild its content.
	 */
	public void rebuild() {
		notifyWillRebuild();
		doRebuild();
		notifyDidRebuild();
	}

	/**
	 * Reload any of our RDF datasources that support RDFRemoteDatasource.
	 */
	public void refresh() {
		Iterator<RDFDataSource> dataSources = database.getDataSources();
		while(dataSources.hasNext()) {
			RDFDataSource source = dataSources.next();
			if(source instanceof RDFRemoteDataSource) {
				((RDFRemoteDataSource)source).refresh(true);
			}
		}
	}

	/**
	 * Remove a listener from this template builder.
	 */
	public void removeListener(XULBuilderListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Notify listeners that rebuild is about to start
	 */
	private void notifyWillRebuild() {
		for(XULBuilderListener listener : listeners) {
			listener.willRebuild(this);
		}
	}

	/**
	 * Notify listeners that rebuild just finished
	 */
	private void notifyDidRebuild() {
		for(XULBuilderListener listener : listeners) {
			listener.didRebuild(this);
		}
	}

	/**
	 * Perform the rebuild.
	 */
	protected void doRebuild() {
		// TODO
	}


	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onBeginUpdateBatch(net.lojjic.xul.rdf.RDFDataSource)
	 */
	public void onBeginUpdateBatch(RDFDataSource dataSource) {
		datasourceInBatchUpdate = true;
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onEndUpdateBatch(net.lojjic.xul.rdf.RDFDataSource)
	 */
	public void onEndUpdateBatch(RDFDataSource dataSource) {
		if(datasourceChanged) {
			rebuild();
		}
		datasourceChanged = false;
		datasourceInBatchUpdate = false;
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onAssert(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onChange(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onMove(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onUnassert(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)  
	 */
	public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

}
