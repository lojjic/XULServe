package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

import java.util.ArrayList;

/**
 * Abstract {@link RDFDataSource} implementation
 */
public abstract class AbstractDataSourceImpl implements RDFDataSource {

	protected RDFService rdfService;
	protected ArrayList<RDFObserver> observers = new ArrayList<RDFObserver>();

	/**
	 * Default constructor; useful for configuration via bean setter injection.
	 * <p>
	 * Be sure to set the {@link net.lojjic.xul.rdf.RDFService}
	 * by calling {@link #setRdfService(net.lojjic.xul.rdf.RDFService)}.
	 */
	protected AbstractDataSourceImpl() {
	}

	/**
	 * Constructor passing in the {@link net.lojjic.xul.rdf.RDFService}
	 * @param rdfService
	 */
	public AbstractDataSourceImpl(RDFService rdfService) {
		setRdfService(rdfService);
	}

	/**
	 * Set the {@link net.lojjic.xul.rdf.RDFService} for this instance
	 * @param rdfService
	 */
	public void setRdfService(RDFService rdfService) {
		this.rdfService = rdfService;
	}

	/**
	 * Add an observer to this data source. If the datasource supports observers, the
	 * datasource source should hold a strong reference to the observer.
	 *
	 * @param observer
	 */
	public void addObserver(RDFObserver observer) {
		observers.remove(observer);
		observers.add(observer);
	}

	/**
	 * Notify observers of an assert event
	 */
	protected void notifyAssert(RDFResource source, RDFResource property, RDFNode target, boolean truthValue) {
		for(RDFObserver observer : observers) {
			observer.onAssert(this, source, property, target);
		}
	}

	/**
	 * Notify observers that the datasource is about to send several notifications at once. This
	 * must be followed by calling endUpdateBatch(), otherwise viewers will get out of sync.
	 */
	public void beginUpdateBatch() {
		notifyBeginUpdateBatch();
	}

	/**
	 * Notify observers of a beginUpdateBatch event
	 */
	public void notifyBeginUpdateBatch() {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onBeginUpdateBatch(this);
		}
	}

	/**
	 * Notify observers of a change event
	 */
	public void notifyChange(RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		for(RDFObserver observer : observers) {
			observer.onChange(this, source, property, oldTarget, newTarget);
		}
	}

	/**
	 * Notify observers that the datasource has completed issuing a notification group.
	 */
	public void endUpdateBatch() {
		notifyEndUpdateBatch();
	}

	/**
	 * Notify observers of an endUpdateBatch event
	 */
	public void notifyEndUpdateBatch() {
		for(RDFObserver observer : observers) {
			observer.onEndUpdateBatch(this);
		}
	}

	/**
	 * Notify observers of a move event
	 */
	public void notifyMove(RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		for(RDFObserver observer : observers) {
			observer.onMove(this, oldSource, newSource, property, target);
		}
	}

	/**
	 * Remove an observer from this data source.
	 *
	 * @param observer
	 */
	public void removeObserver(RDFObserver observer) {
		observers.remove(observer);
	}

	/**
	 * Notify observers of an unassert event
	 */
	public void notifyUnassert(RDFResource source, RDFResource property, RDFNode target) {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onUnassert(this, source, property, target);
		}
	}
}
