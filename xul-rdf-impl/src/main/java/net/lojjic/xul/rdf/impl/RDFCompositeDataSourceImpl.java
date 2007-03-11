package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFCompositeDataSource}
 */
public class RDFCompositeDataSourceImpl extends AbstractDataSourceImpl implements RDFCompositeDataSource, RDFObserver {

	private boolean allowNegativeAssertions = false;
	private boolean coalesceDuplicateArcs = true;
	private List<RDFDataSource> dataSources = new ArrayList<RDFDataSource>();

	/**
	 * Constructor.
	 */
	public RDFCompositeDataSourceImpl(RDFService rdfService) {
		super(rdfService);
	}

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 */
	public boolean isAllowNegativeAssertions() {
		return allowNegativeAssertions;
	}

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 * @throws UnsupportedOperationException for 'true' argument since negative assertions are not
	 *         supported by this implementation.
	 */
	public void setAllowNegativeAssertions(boolean allow) {
		if(allow) {
			throw new UnsupportedOperationException("Negative assertions are not supported by this implementation.");
		}
	}

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	public boolean isCoalesceDuplicateArcs() {
		return coalesceDuplicateArcs;
	}

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	public void setCoalesceDuplicateArcs(boolean coalesce) {
		this.coalesceDuplicateArcs = coalesce;
	}

	/**
	 * Add a datasource the the composite data source.
	 *
	 * @param dataSource the datasource to add to composite
	 */
	public void addDataSource(RDFDataSource dataSource) {
		dataSource.addObserver(this);
		dataSources.add(dataSource);
	}

	/**
	 * Retrieve the datasources in the composite data source.
	 *
	 * @return java.util.Enumeration that will enumerate each of the datasources in the composite
	 */
	public Iterator<RDFDataSource> getDataSources() {
		return dataSources.iterator();
	}

	/**
	 * Remove a datasource from the composite data source.
	 *
	 * @param dataSource: the datasource to remove from the composite
	 */
	public void removeDataSource(RDFDataSource dataSource) {
		dataSource.removeObserver(this);
		dataSources.remove(dataSource);
	}


	/**
	 * Get a cursor to iterate over all the arcs that point into a node.
	 *
	 * @param node
	 * @return Enumeration of RDFResources
	 */
	public Iterator<RDFResource> arcLabelsIn(RDFNode node) {
		Collection<RDFResource> results = (coalesceDuplicateArcs ? new HashSet<RDFResource>() : new ArrayList<RDFResource>());
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.arcLabelsIn(node));
		}
		return results.iterator();
	}

	/**
	 * Get a cursor to iterate over all the arcs that originate in a resource.
	 *
	 * @param source
	 * @return Enumeration of RDFResources
	 */
	public Iterator<RDFResource> arcLabelsOut(RDFResource source) {
		Collection<RDFResource> results = (coalesceDuplicateArcs ? new HashSet<RDFResource>() : new ArrayList<RDFResource>());
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.arcLabelsOut(source));
		}
		return results.iterator();
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
		// TODO how to handle changes?
		throw new UnsupportedOperationException("Modification of composite datasources is not supported.");
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
		// TODO how to handle changes?
		throw new UnsupportedOperationException("Modification of composite datasources is not supported.");
	}

	/**
	 * Perform the specified command on set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 */
	public void doCommand(Object[] sources, RDFResource command, Object[] arguments) {
		for(RDFDataSource dataSource : dataSources) {
			dataSource.doCommand(sources, command, arguments);
		}
	}

	/**
	 * Returns the set of all commands defined for a given source.
	 *
	 * @param source
	 * @return
	 */
	public Iterator getAllCmds(RDFResource source) {
		Collection results = new ArrayList();
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.getAllCmds(source));
		}
		return results.iterator();
	}

	/**
	 * Retrieve all of the resources that the data source currently refers to.
	 *
	 * @return Enumeration of RDFResource objects
	 */
	public Iterator<RDFResource> getAllResources() {
		Collection<RDFResource> results = (coalesceDuplicateArcs ? new HashSet<RDFResource>() : new ArrayList<RDFResource>());
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.getAllResources());
		}
		return results.iterator();
	}

	/**
	 * Find an RDF resource that points to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return null if there is no source that leads to the target with the specified property.
	 */
	public RDFResource getSource(RDFResource property, RDFNode target, boolean truthValue) {
		for(RDFDataSource dataSource : dataSources) {
			RDFResource source = dataSource.getSource(property, target, truthValue);
			if(source != null) {
				return source;
			}
		}
		return null;
	}

	/**
	 * Find all RDF resources that point to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Iterator<RDFResource> getSources(RDFResource property, RDFNode target, boolean truthValue) {
		Collection<RDFResource> results = (coalesceDuplicateArcs ? new HashSet<RDFResource>() : new ArrayList<RDFResource>());
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.getSources(property, target, truthValue));
		}
		return results.iterator();
	}

	/**
	 * Find a child of that is related to the source by the given arc arc and truth value
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return null if there is no target accessable from the source via the specified property.
	 */
	public RDFNode getTarget(RDFResource source, RDFResource property, boolean truthValue) {
		for(RDFDataSource dataSource : dataSources) {
			RDFNode target = dataSource.getTarget(source, property, truthValue);
			if(target != null) {
				return target;
			}
		}
		return null;
	}

	/**
	 * Find all children of that are related to the source by the given arc arc and truth value.
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Iterator<RDFNode> getTargets(RDFResource source, RDFResource property, boolean truthValue) {
		Collection<RDFNode> results = (coalesceDuplicateArcs ? new HashSet<RDFNode>() : new ArrayList<RDFNode>());
		for(RDFDataSource dataSource : dataSources) {
			CollectionUtils.addAll(results, dataSource.getTargets(source, property, truthValue));
		}
		return results.iterator();
	}

	/**
	 * The "URI" of the data source. This used by the RDF service's
	 * getDataSource() method to cache datasources.
	 */
	public String getURI() {
		// TODO
		return null;
	}

	/**
	 * Returns true if the specified node is pointed to by the specified arc. Equivalent to
	 * enumerating ArcLabelsIn and comparing for the specified arc.
	 *
	 * @param node
	 * @param arc
	 * @return
	 */
	public boolean hasArcIn(RDFNode node, RDFResource arc) {
		for(RDFDataSource dataSource : dataSources) {
			if(dataSource.hasArcIn(node, arc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the specified node has the specified outward arc. Equivalent to
	 * enumerating ArcLabelsOut and comparing for the specified arc.
	 *
	 * @param source
	 * @param arc
	 * @return
	 */
	public boolean hasArcOut(RDFResource source, RDFResource arc) {
		for(RDFDataSource dataSource : dataSources) {
			if(dataSource.hasArcOut(source, arc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Query whether an assertion exists in this graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return
	 */
	public boolean hasAssertion(RDFResource source, RDFResource property, RDFNode target, boolean truthValue) {
		for(RDFDataSource dataSource : dataSources) {
			if(dataSource.hasAssertion(source, property, target, truthValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether a given command is enabled for a set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 * @return
	 */
	public boolean isCommandEnabled(Object[] sources, RDFResource command, Object[] arguments) {
		for(RDFDataSource dataSource : dataSources) {
			if(dataSource.isCommandEnabled(sources, command, arguments)) {
				return true;
			}
		}
		return false;
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
		// TODO how to handle changes?
		throw new UnsupportedOperationException("Modification of composite datasources is not supported.");
	}

	/**
	 * Remove an assertion from the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 */
	public void unassert(RDFResource source, RDFResource property, RDFNode target) {
		// TODO how to handle changes?
		throw new UnsupportedOperationException("Modification of composite datasources is not supported.");
	}




	//////// RDFObserver interface methods: ////////

	/**
	 * This method is called whenever a new assertion is made in the data source
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		notifyAssert(source, property, target, true);
	}

	/**
	 * This method is called when a datasource is about to send several notifications at once.
	 * The observer can use this as a cue to optimize its behavior. The observer can expect the
	 * datasource to call endUpdateBatch() when the group of notifications has completed.
	 *
	 * @param dataSource the datasource that is going to be issuing the notifications.
	 */
	public void onBeginUpdateBatch(RDFDataSource dataSource) {
		notifyBeginUpdateBatch();
	}

	/**
	 * This method is called when the object of an assertion changes from one value to another.
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param oldTarget  the old object of the assertion
	 * @param newTarget  the new object of the assertion
	 */
	public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		notifyChange(source, property, oldTarget, newTarget);
	}

	/**
	 * This method is called when a datasource has completed issuing a notification group.
	 *
	 * @param dataSource the datasource that has finished issuing a group of notifications
	 */
	public void onEndUpdateBatch(RDFDataSource dataSource) {
		notifyEndUpdateBatch();
	}

	/**
	 * This method is called when the subject of an assertion changes from one value to another.
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param oldSource  the old subject of the assertion
	 * @param newSource  the new subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		notifyMove(oldSource, newSource, property, target);
	}

	/**
	 * This method is called whenever an assertion is removed from the data source
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		notifyUnassert(source, property, target);
	}

}
