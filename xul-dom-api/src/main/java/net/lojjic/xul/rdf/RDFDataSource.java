package net.lojjic.xul.rdf;

import java.util.Iterator;

/**
 * <p>RDF data source interface.</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/RDFDataSource.html</p>
 */
public interface RDFDataSource {

	/**
	 * The "URI" of the data source. This used by the RDF service's
	 * getDataSource() method to cache datasources.
	 */
	String getURI();

	/**
	 * Add an observer to this data source. If the datasource supports observers, the
	 * datasource source should hold a strong reference to the observer.
	 *
	 * @param observer
	 */
	void addObserver(RDFObserver observer);


	/**
	 * Get a cursor to iterate over all the arcs that point into a node.
	 *
	 * @param node
	 * @return Enumeration of RDFResources
	 */
	Iterator<RDFResource> arcLabelsIn(RDFNode node);


	/**
	 * Get a cursor to iterate over all the arcs that originate in a resource.
	 *
	 * @param source
	 * @return Enumeration of RDFResources
	 */
	Iterator<RDFResource> arcLabelsOut(RDFResource source);

	/**
	 * Add an assertion to the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 * @param truthValue
	 */
	void doAssert(RDFResource source, RDFResource property, RDFNode target, boolean truthValue);


	/**
	 * Notify observers that the datasource is about to send several notifications at once. This
	 * must be followed by calling endUpdateBatch(), otherwise viewers will get out of sync.
	 */
	void beginUpdateBatch();

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
	void change(RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget);

	/**
	 * Perform the specified command on set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 */
	void doCommand(Object[] sources, RDFResource command, Object[] arguments);

	/**
	 * Notify observers that the datasource has completed issuing a notification group.
	 */
	void endUpdateBatch();


	/**
	 * Returns the set of all commands defined for a given source.
	 *
	 * @param source
	 * @return
	 */
	Iterator getAllCmds(RDFResource source);

	/**
	 * Retrieve all of the resources that the data source currently refers to.
	 *
	 * @return Enumeration of RDFResource objects
	 */
	Iterator<RDFResource> getAllResources();

	/**
	 * Find an RDF resource that points to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return NS_RDF_NO_VALUE if there is no source that leads to the target with the specified property.
	 */
	RDFResource getSource(RDFResource property, RDFNode target, boolean truthValue);

	/**
	 * Find all RDF resources that point to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return Enumeration points
	 */
	Iterator<RDFResource> getSources(RDFResource property, RDFNode target, boolean truthValue);

	/**
	 * Find a child of that is related to the source by the given arc arc and truth value
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return NS_RDF_NO_VALUE if there is no target accessable from the source via the specified property.
	 */
	RDFNode getTarget(RDFResource source, RDFResource property, boolean truthValue);

	/**
	 * Find all children of that are related to the source by the given arc arc and truth value.
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return Enumeration points
	 */
	Iterator<RDFNode> getTargets(RDFResource source, RDFResource property, boolean truthValue);

	/**
	 * Returns true if the specified node is pointed to by the specified arc. Equivalent to
	 * enumerating ArcLabelsIn and comparing for the specified arc.
	 *
	 * @param node
	 * @param arc
	 * @return
	 */
	boolean hasArcIn(RDFNode node, RDFResource arc);

	/**
	 * Returns true if the specified node has the specified outward arc. Equivalent to
	 * enumerating ArcLabelsOut and comparing for the specified arc.
	 *
	 * @param source
	 * @param arc
	 * @return
	 */
	boolean hasArcOut(RDFResource source, RDFResource arc);

	/**
	 * Query whether an assertion exists in this graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return
	 */
	boolean hasAssertion(RDFResource source, RDFResource property, RDFNode target, boolean truthValue);

	/**
	 * Returns whether a given command is enabled for a set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 * @return
	 */
	boolean isCommandEnabled(Object[] sources, RDFResource command, Object[] arguments);

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
	void move(RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target);

	/**
	 * Remove an observer from this data source.
	 *
	 * @param observer
	 */
	void removeObserver(RDFObserver observer);

	/**
	 * Remove an assertion from the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 */
	void unassert(RDFResource source, RDFResource property, RDFNode target);

}
