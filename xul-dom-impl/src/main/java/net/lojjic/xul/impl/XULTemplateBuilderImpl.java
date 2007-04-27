package net.lojjic.xul.impl;

import net.lojjic.xul.XULBuilderListener;
import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFCompositeDataSourceImpl;
import org.w3c.dom.DocumentFragment;
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
	protected Element builtElement;
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

		// Set the root element:
		setRoot(rootElement);

		// Create the composite datasource:
		this.database = new RDFCompositeDataSourceImpl(rdfService);
		parseDatasources(rootElement);

		// Add observer to rebuild when datasource changes:
		this.database.addObserver(this);
	}

	/**
	 * Add the URIs specified in the 'datasources' attribute to the datasource.
	 */
	private void parseDatasources(Element rootElement) {
		String datasources = rootElement.getAttribute("datasources");
		if(datasources == null) {
			throw new IllegalStateException("Template root element missing 'datasources' attribute.");
		}
		for(String uri : datasources.split("[\\s]+")) {
			this.database.addDataSource(rdfService.getDataSource(uri));
		}
	}

	/**
	 * Set the root element, adding a mutation event listener to trigger
	 * a rebuild when the template DOM changes.
	 */
	private void setRoot(Element rootElement) {
		this.rootElement = rootElement;
		EventListener listener = new EventListener() {
			public void handleEvent(Event evt) {
				rebuild();
			}
		};
		((EventTarget)rootElement).addEventListener("DOMSubtreeModified", listener, false);
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

	/**
	 * Class that does the work of compiling the template and building the result
	 */
	private class XULTemplate {

		public void compileTemplate() {

		}

		public DocumentFragment buildResultDOM() {
			return null;
		}

	}

}
