package net.lojjic.xul.impl;

import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.XULBuilderListener;
import net.lojjic.xul.impl.rdf.RDFCompositeDataSourceImpl;
import net.lojjic.xul.rdf.*;
import org.w3c.dom.Element;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Abstract implementation of {@link XULTemplateBuilder}
 */
public class XULTemplateBuilderImpl implements XULTemplateBuilder {

	protected List<XULBuilderListener> listeners = new ArrayList<XULBuilderListener>();
	protected Element rootElement;
	protected RDFCompositeDataSource database;

	/**
	 * Constructor
	 * @param rdfService The {@link RDFService} to use
	 * @param rootElement The root element to which the template builder is attached.
	 */
	protected XULTemplateBuilderImpl(RDFService rdfService, Element rootElement) {
		this.rootElement = rootElement;

		// Create the composite datasource:
		this.database = new RDFCompositeDataSourceImpl(rdfService);

		// Add the URIs specified in the 'datasources' attribute to the datasource:
		String datasources = rootElement.getAttribute("datasources");
		if(datasources == null) {
			throw new IllegalStateException("Template root element missing 'datasources' attribute.");
		}
		for(String uri : datasources.split("[\\s]+")) {
			this.database.addDataSource(rdfService.getDataSource(uri));
		}

		// Add rebuild observer:
		this.database.addObserver(new RebuildObserver());
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
		// TODO
		notifyDidRebuild();
	}

	/**
	 * Reload any of our RDF datasources that support RDFRemoteDatasource.
	 */
	public void refresh() {
		Enumeration<RDFDataSource> dataSources = database.getDataSources();
		while(dataSources.hasMoreElements()) {
			RDFDataSource source = dataSources.nextElement();
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

}
