package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.*;

import java.util.ArrayList;

/**
 * Abstract {@link RDFDataSource} implementation
 */
public abstract class AbstractDataSourceImpl implements RDFDataSource {

	protected RDFService rdfService;
	protected ArrayList<RDFObserver> observers = new ArrayList<RDFObserver>();

	/**
	 * Base constructor
	 */
	public AbstractDataSourceImpl(RDFService rdfService) {
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
	 * Add an assertion to the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 * @param truthValue
	 */
	public void doAssert(RDFResource source, RDFResource property, RDFNode target, boolean truthValue) {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onAssert(this, source, property, target);
		}
	}

	/**
	 * Notify observers that the datasource is about to send several notifications at once. This
	 * must be followed by calling endUpdateBatch(), otherwise viewers will get out of sync.
	 */
	public void beginUpdateBatch() {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onBeginUpdateBatch(this);
		}
	}

	/**
	 * <p>Change an assertion from</p>
	 * <pre>[source]--[property]-->[oldTarget]</pre>
	 * <p>To</p>
	 * <pre>[source]--[property]-->[newTarget]</pre>
	 *
	 * @param source
	 * @param property
	 * @param oldTarget
	 * @param newTarget
	 */
	public void change(RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onChange(this, source, property, oldTarget, newTarget);
		}
	}

	/**
	 * Notify observers that the datasource has completed issuing a notification group.
	 */
	public void endUpdateBatch() {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onEndUpdateBatch(this);
		}
	}

	/**
	 * <p>'Move' an assertion from</p>
	 * <pre>[oldSource]--[property]-->[target]</pre>
	 * <p>To</p>
	 * <pre>[newSource]--[property]-->[target]</pre>
	 *
	 * @param oldSource
	 * @param newSource
	 * @param property
	 * @param target
	 */
	public void move(RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		// Notify observers:
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
	 * Remove an assertion from the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 */
	public void unassert(RDFResource source, RDFResource property, RDFNode target) {
		// Notify observers:
		for(RDFObserver observer : observers) {
			observer.onUnassert(this, source, property, target);
		}
	}
}
