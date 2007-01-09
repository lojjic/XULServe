package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.*;

import java.util.Enumeration;

/**
 * RDF data source implementation that gets its data from a RDF-XML file.
 * That file may be local or remote.
 */
public class RDFXMLDataSourceImpl extends AbstractDataSourceImpl implements RDFDataSource, RDFRemoteDataSource {

	private String uri;

	/**
	 * Constructor.
	 * @param uri: The URI of the RDF-XML file holding the graph data.
	 */
	public RDFXMLDataSourceImpl(RDFService rdfService, String uri) {
		super(rdfService);
		this.uri = uri;
	}

	/**
	 * The "URI" of the data source. This used by the RDF service's
	 * getDataSource() method to cache datasources.
	 */
	public String getURI() {
		return uri;
	}

	/**
	 * Get a cursor to iterate over all the arcs that point into a node.
	 *
	 * @param node
	 * @return Enumeration of ??? TODO
	 */
	public Enumeration arcLabelsIn(RDFNode node) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Get a cursor to iterate over all the arcs that originate in a resource.
	 *
	 * @param source
	 * @return Enumeration of ??? TODO
	 */
	public Enumeration arcLabelsOut(RDFResource source) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Perform the specified command on set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 */
	public void doCommand(Object[] sources, RDFResource command, Object[] arguments) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Returns the set of all commands defined for a given source.
	 *
	 * @param source
	 * @return
	 */
	public Enumeration getAllCmds(RDFResource source) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Retrieve all of the resources that the data source currently refers to.
	 *
	 * @return
	 */
	public Enumeration getAllResources() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Find an RDF resource that points to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return NS_RDF_NO_VALUE if there is no source that leads to the target with the specified property.
	 */
	public RDFResource getSource(RDFResource property, RDFNode target, boolean truthValue) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Find all RDF resources that point to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Enumeration<RDFResource> getSources(RDFResource property, RDFNode target, boolean truthValue) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Find a child of that is related to the source by the given arc arc and truth value
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return NS_RDF_NO_VALUE if there is no target accessable from the source via the specified property.
	 */
	public RDFNode getTarget(RDFResource source, RDFResource property, boolean truthValue) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Find all children of that are related to the source by the given arc arc and truth value.
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Enumeration<RDFNode> getTargets(RDFResource source, RDFResource property, boolean truthValue) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
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
		return false;  //To change body of implemented methods use File | Settings | File Templates.
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
		return false;  //To change body of implemented methods use File | Settings | File Templates.
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
		return false;  //To change body of implemented methods use File | Settings | File Templates.
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
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Notify observers that the datasource is about to send several notifications at once. This
	 * must be followed by calling endUpdateBatch(), otherwise viewers will get out of sync.
	 */
	public void beginUpdateBatch() {
		// TODO
		super.beginUpdateBatch();
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
		// TODO
		super.change(source, property, oldTarget, newTarget);
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
		// TODO
		super.doAssert(source, property, target, truthValue);
	}

	/**
	 * Notify observers that the datasource has completed issuing a notification group.
	 */
	public void endUpdateBatch() {
		// TODO
		super.endUpdateBatch();
	}

	/**
	 * Remove an assertion from the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 */
	public void unassert(RDFResource source, RDFResource property, RDFNode target) {
		// TODO
		super.unassert(source, property, target);
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
		// TODO
		super.move(oldSource, newSource, property, target);
	}

	/**
	 * This value is true when the datasource has fully loaded itself.
	 */
	public boolean isLoaded() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Request that a data source write it's contents out to permanent storage, if applicable.
	 */
	public void flush() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * @param uri
	 */
	public void flushTo(String uri) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Specify the URI for the data source: this is the prefix that will be used to register
	 * the data source in the data source registry.
	 *
	 * @param uri: the URI to load
	 */
	public void init(String uri) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Refresh the remote datasource, re-loading its contents from the URI.
	 *
	 * @param blocking: If true, the call will block until the datasource has completely reloaded.
	 */
	public void refresh(boolean blocking) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
