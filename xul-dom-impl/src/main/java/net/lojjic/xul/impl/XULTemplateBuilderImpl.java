package net.lojjic.xul.impl;

import net.lojjic.xul.XULBuilderListener;
import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.impl.rdf.RDFCompositeDataSourceImpl;
import net.lojjic.xul.rdf.*;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Implementation of {@link XULTemplateBuilder}
 */
public class XULTemplateBuilderImpl implements XULTemplateBuilder {

	protected List<XULBuilderListener> listeners = new ArrayList<XULBuilderListener>();
	protected Element rootElement;
	protected Element builtElement;
	protected RDFCompositeDataSource database;
	protected RDFService rdfService;

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
		this.database.addObserver(new RebuildObserver());
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
	 * {@link RDFObserver} implementation that triggers a rebuild for any change to
	 * the datasource.
	 */
	private class RebuildObserver implements RDFObserver {
		private boolean changed = false;
		private boolean inBatch = false;

		public void onBeginUpdateBatch(RDFDataSource dataSource) {
			inBatch = true;
		}
		public void onEndUpdateBatch(RDFDataSource dataSource) {
			if(changed) {
				rebuild();
			}
			changed = false;
			inBatch = false;
		}

		public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
			changed = true;
			if(!inBatch) {
				rebuild();
				changed = false;
			}
		}
		public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
			changed = true;
			if(!inBatch) {
				rebuild();
				changed = false;
			}
		}
		public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
			changed = true;
			if(!inBatch) {
				rebuild();
				changed = false;
			}
		}
		public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
			changed = true;
			if(!inBatch) {
				rebuild();
				changed = false;
			}
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
