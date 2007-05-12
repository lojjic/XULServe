package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFService;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.Repository;
import org.openrdf.model.Statement;
import info.aduna.iteration.Iterations;

/**
 * A {@link net.lojjic.xul.rdf.RDFDataSource} implementation that
 * performs its operations on a wrapped Sesame {@link org.openrdf.repository.Repository}.
 */
public class SesameDataSourceImpl extends AbstractDataSourceImpl {

	private Repository repository;

	/**
	 * Default constructor; useful for configuration via bean setter injection.
	 * <p>
	 * Be sure to set the {@link net.lojjic.xul.rdf.RDFService}
	 * by calling {@link #setRdfService(net.lojjic.xul.rdf.RDFService)}.
	 */
	public SesameDataSourceImpl() {
		super();
	}

	/**
	 * Constructor passing in the {@link net.lojjic.xul.rdf.RDFService} and
	 * {@link org.openrdf.repository.Repository}.
	 * 
	 * @param rdfService
	 * @param repository
	 */
	public SesameDataSourceImpl(RDFService rdfService, Repository repository) {
		super(rdfService);
		this.repository = repository;
	}

	/**
	 * Set the Sesame {@link org.openrdf.repository.Repository} for this instance. 
	 * @param repository
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	/**
	 * The "URI" of the data source. This used by the RDF service's
	 * getDataSource() method to cache datasources.
	 */
	public String getURI() {
		return "sesame:" + repository.hashCode();
	}

	/**
	 * Get a cursor to iterate over all the arcs that point into a node.
	 *
	 * @param node
	 * @return Enumeration of RDFResources
	 */
	public Iterator<RDFResource> arcLabelsIn(final RDFNode node) {
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection con) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						con.getStatements(null, null, SesameUtils.toValue(repository, node), false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return SesameUtils.toRDFResource(rdfService, iter.next().getPredicate());
						}
						public void remove() {}
					};
				}
			}
		);
	}

	/**
	 * Get a cursor to iterate over all the arcs that originate in a resource.
	 *
	 * @param source
	 * @return Enumeration of RDFResources
	 */
	public Iterator<RDFResource> arcLabelsOut(final RDFResource source) {
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection con) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						con.getStatements(SesameUtils.toResource(repository, source), null, null, false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return SesameUtils.toRDFResource(rdfService, iter.next().getPredicate());
						}
						public void remove() {}
					};
				}
			}
		);
	}

	/**
	 * Add an assertion to the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 * @param truthValue - Currently not supported.
	 */
	public void doAssert(final RDFResource source, final RDFResource property, final RDFNode target, boolean truthValue) {
		checkTruthValue(truthValue);

		SesameUtils.execute(repository,
			new SesameConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					conn.add(SesameUtils.toStatement(repository, source, property, target));
					return null;
				}
			}
		);

		notifyAssert(source, property, target, truthValue);
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
		unassert(source, property, oldTarget);
		doAssert(source, property, newTarget, true);
		notifyChange(source, property, oldTarget, newTarget);
	}

	/**
	 * Perform the specified command on set of sources.
	 *
	 * @param sources
	 * @param command
	 * @param arguments
	 */
	public void doCommand(Object[] sources, RDFResource command, Object[] arguments) {
		// TODO
	}

	/**
	 * Returns the set of all commands defined for a given source.
	 *
	 * @param source
	 * @return
	 */
	public Iterator getAllCmds(RDFResource source) {
		// TODO
		return null;
	}

	/**
	 * Retrieve all of the resources that the data source currently refers to.
	 */
	public Iterator<RDFResource> getAllResources() {
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection conn) throws Exception {
					List<Statement> statements = Iterations.asList(
						conn.getStatements(null, null, null, false)
					);
					HashSet<RDFResource> resources = new HashSet<RDFResource>();
					for(Statement stmt : statements) {
						resources.add(SesameUtils.toRDFResource(rdfService, stmt.getSubject()));
					}
					return resources.iterator();
				}
			}
		);
	}

	/**
	 * Find an RDF resource that points to a given node over the specified arc & truth value
	 */
	public RDFResource getSource(RDFResource property, RDFNode target, boolean truthValue) {
		Iterator<RDFResource> sources = getSources(property, target, truthValue);
		return sources.hasNext() ? sources.next() : null;
	}

	/**
	 * Find all RDF resources that point to a given node over the specified arc & truth value
	 *
	 * @param property
	 * @param target
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Iterator<RDFResource> getSources(final RDFResource property, final RDFNode target, boolean truthValue) {
		checkTruthValue(truthValue);
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection conn) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						conn.getStatements(
								null, SesameUtils.toURI(repository, property),
								SesameUtils.toValue(repository, target), false
						),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return SesameUtils.toRDFResource(rdfService, iter.next().getSubject());
						}
						public void remove() {}
					};
				}
			}
		);
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
		Iterator<RDFNode> targets = getTargets(source, property, truthValue);
		return targets.hasNext() ? targets.next() : null;
	}

	/**
	 * Find all children of that are related to the source by the given arc arc and truth value.
	 *
	 * @param source
	 * @param property
	 * @param truthValue
	 * @return Enumeration points
	 */
	public Iterator<RDFNode> getTargets(final RDFResource source, final RDFResource property, boolean truthValue) {
		checkTruthValue(truthValue);
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Iterator<RDFNode>>() {
				public Iterator<RDFNode> doInConnection(RepositoryConnection conn) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						conn.getStatements(
								SesameUtils.toResource(repository, source),
								SesameUtils.toURI(repository, property), null, false
						),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFNode>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFNode next() {
							return SesameUtils.toRDFNode(rdfService, iter.next().getObject());
						}
						public void remove() {}
					};
				}
			}
		);
	}

	/**
	 * Returns true if the specified node is pointed to by the specified arc. Equivalent to
	 * enumerating ArcLabelsIn and comparing for the specified arc.
	 *
	 * @param node
	 * @param arc
	 * @return
	 */
	public boolean hasArcIn(final RDFNode node, final RDFResource arc) {
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(null, SesameUtils.toURI(repository, arc),
							SesameUtils.toValue(repository, node), false);
				}
			}
		);
	}

	/**
	 * Returns true if the specified node has the specified outward arc. Equivalent to
	 * enumerating ArcLabelsOut and comparing for the specified arc.
	 *
	 * @param source
	 * @param arc
	 * @return
	 */
	public boolean hasArcOut(final RDFResource source, final RDFResource arc) {
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(SesameUtils.toResource(repository, source),
							SesameUtils.toURI(repository, arc), null, false);
				}
			}
		);
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
	public boolean hasAssertion(final RDFResource source, final RDFResource property,
	                            final RDFNode target, boolean truthValue) {
		checkTruthValue(truthValue);
		if(source == null || property == null || target == null) {
			throw new IllegalArgumentException("The source, property, and target arguments must all be non-null.");
		}
		return SesameUtils.execute(repository,
			new SesameConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(SesameUtils.toStatement(repository, source, property, target), false);
				}
			}
		);
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
		return false;  // TODO
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
		unassert(oldSource, property, target);
		doAssert(newSource, property, target, true);
		notifyMove(oldSource, newSource, property, target);
	}

	/**
	 * Remove an assertion from the graph.
	 *
	 * @param source
	 * @param property
	 * @param target
	 */
	public void unassert(final RDFResource source, final RDFResource property, final RDFNode target) {
		SesameUtils.execute(repository,
			new SesameConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					conn.remove(SesameUtils.toStatement(repository, source, property, target));
					return null;
				}
			}
		);
		notifyUnassert(source, property, target);
	}

	/**
	 * Utility to check that the given truth value is not false, as it's
	 * not supported by this implementation.
	 */
	private void checkTruthValue(boolean truthValue) {
		if(!truthValue) {
			throw new UnsupportedOperationException("Untrue assertions are not supported by this implementation.");
		}
	}

	/**
	 * Return the underlying Sesame {@link org.openrdf.repository.Repository} being used.
	 * Implementation-specific; use with caution.
	 */
	public Repository getSesameRepository() {
		return repository;
	}
}
