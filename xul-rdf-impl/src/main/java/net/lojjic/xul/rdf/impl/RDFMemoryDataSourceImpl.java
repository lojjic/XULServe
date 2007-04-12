package net.lojjic.xul.rdf.impl;

import info.aduna.iteration.Iterations;
import net.lojjic.xul.rdf.*;
import org.apache.xerces.impl.dv.util.Base64;
import org.openrdf.model.*;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFDataSource} that stores
 * its RDF graph in memory.  Uses a Sesame in-memory repository.
 */
public class RDFMemoryDataSourceImpl extends AbstractDataSourceImpl implements RDFDataSource {

	private Repository repository;

	/**
	 * Construct the instance, setting up the in-memory Sesame repository.
	 */
	public RDFMemoryDataSourceImpl(RDFService rdfService) {
		super(rdfService);
		try {
			repository = new SailRepository(new MemoryStore());
			repository.initialize();
		}
		catch (Exception e) {
			throw new RuntimeException("Exception creating local Sesame repository.", e);
		}
	}

	/**
	 * The "URI" of the data source. This used by the RDF service's
	 * getDataSource() method to cache datasources.
	 */
	public String getURI() {
		return "mem-repo:" + hashCode();
	}

	protected static interface ConnectionCallback<T> {
		T doInConnection(RepositoryConnection conn) throws Exception;
	}

	protected <T> T execute(ConnectionCallback<T> callback) {
		try {
			RepositoryConnection con = repository.getConnection();
			try {
				return callback.doInConnection(con);
			}
			finally {
				con.close();
			}
		}
		catch (Exception e) {
			throw new RDFException(e);
		}
	}

	/**
	 * Get a cursor to iterate over all the arcs that point into a node.
	 *
	 * @param node
	 * @return Enumeration of RDFResources
	 */
	public Iterator<RDFResource> arcLabelsIn(final RDFNode node) {
		return execute(
			new ConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection con) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						con.getStatements(null, null, toValue(node), false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return toRDFResource(iter.next().getPredicate());
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
		return execute(
			new ConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection con) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						con.getStatements(toResource(source), null, null, false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return toRDFResource(iter.next().getPredicate());
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

		execute(
			new ConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					conn.add(toStatement(source, property, target));
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
		//To change body of implemented methods use File | Settings | File Templates.
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
		return execute(
			new ConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection conn) throws Exception {
					List<Statement> statements = Iterations.asList(
						conn.getStatements(null, null, null, false)
					);
					HashSet<RDFResource> resources = new HashSet<RDFResource>();
					for(Statement stmt : statements) {
						resources.add(toRDFResource(stmt.getSubject()));
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
		return execute(
			new ConnectionCallback<Iterator<RDFResource>>() {
				public Iterator<RDFResource> doInConnection(RepositoryConnection conn) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						conn.getStatements(null, toURI(property), toValue(target), false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFResource>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFResource next() {
							return toRDFResource(iter.next().getSubject());
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
		return execute(
			new ConnectionCallback<Iterator<RDFNode>>() {
				public Iterator<RDFNode> doInConnection(RepositoryConnection conn) throws RepositoryException {
					final Iterator<Statement> iter = Iterations.addAll(
						conn.getStatements(toResource(source), toURI(property), null, false),
						new ArrayList<Statement>()
					).iterator();
					return new Iterator<RDFNode>() {
						public boolean hasNext() {
							return iter.hasNext();
						}
						public RDFNode next() {
							return toRDFNode(iter.next().getObject());
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
		return execute(
			new ConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(null, toURI(arc), toValue(node), false);
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
		return execute(
			new ConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(toResource(source), toURI(arc), null, false);
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
		return execute(
			new ConnectionCallback<Boolean>() {
				public Boolean doInConnection(RepositoryConnection conn) throws Exception {
					return conn.hasStatement(toStatement(source, property, target), false);
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
		return false;  //To change body of implemented methods use File | Settings | File Templates.
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
		execute(
			new ConnectionCallback<Object>() {
				public Object doInConnection(RepositoryConnection conn) throws Exception {
					conn.remove(toStatement(source, property, target));
					return null;
				}
			}
		);
		notifyUnassert(source, property, target);
	}


	/**
	 * Utility to convert a Mozilla RDFNode to a Sesame Value of the appropriate type.
	 */
	protected Value toValue(RDFNode rdfNode) {
		// Resources:
		if(rdfNode instanceof RDFResource) {
			return toResource((RDFResource)rdfNode);
		}
		// Literals:
		return toLiteral(rdfNode);
	}

	/**
	 * Utility to convert a Mozilla RDFNode to a Sesame Literal
	 */
	protected Literal toLiteral(RDFNode rdfNode) {
		ValueFactory factory = repository.getValueFactory();
		if(rdfNode instanceof RDFLiteral) {
			return factory.createLiteral(((RDFLiteral)rdfNode).getValue());
		}
		if(rdfNode instanceof RDFInt) {
			return factory.createLiteral(String.valueOf(((RDFInt)rdfNode).getValue()), XMLSchema.INT);
		}
		if(rdfNode instanceof RDFDate) {
			URI datatype = XMLSchema.DATETIME;
			return null; //TODO create literal with xsd:datetime-formatted string
		}
		if(rdfNode instanceof RDFBlob) {
			String base64 = Base64.encode(((RDFBlob)rdfNode).getValue());
			return factory.createLiteral(base64, XMLSchema.BASE64BINARY);
		}
		throw new IllegalArgumentException("Cannot convert given object to a Literal.");
	}

	/**
	 * Utility to convert a Mozilla RDFResource to a Sesame Resource.
	 * Same as toURI() but it can return a BNode.
	 */
	protected Resource toResource(RDFResource mozResource) {
		// URI resource:
		if(mozResource.getValue() != null) {
			return toURI(mozResource);
		}
		// Blank node; use the object's hashcode as the bnode id
		return repository.getValueFactory().createBNode(String.valueOf(mozResource.hashCode()));
	}

	/**
	 * Utility to convert a Mozilla RDFResource to a Sesame URI
	 */
	protected URI toURI(RDFResource mozResource) {
		if(mozResource.getValue() == null) {
			throw new IllegalArgumentException("Cannot convert an anonymous resource to a URI.");
		}
		return repository.getValueFactory().createURI(mozResource.getValue());
	}

	/**
	 * Utility to build a Sesame Statement from the given Mozilla triple parts
	 */
	protected Statement toStatement(RDFResource subject, RDFResource predicate, RDFNode object) {
		return repository.getValueFactory().createStatement(toResource(subject), toURI(predicate), toValue(object));
	}

	/**
	 * Utility to convert a Sesame Resource to a Mozilla RDFResource
	 */
	protected RDFResource toRDFResource(Resource resource) {
		// Blank nodes:
		if(resource instanceof BNode) {
			// TODO figure out how to guarantee the same instance for the same logical bnode
			return rdfService.getAnonymousResource();
		}
		// URI nodes:
		return rdfService.getResource(resource.toString());
	}

	/**
	 * Utility to convert a Sesame Value to a Mozilla RDFNode
	 */
	protected RDFNode toRDFNode(Value value) {
		// Resources:
		if(value instanceof Resource) {
			return toRDFResource((Resource)value);
		}
		// Literals:
		Literal literal = (Literal)value;
		if(XMLSchema.INT.equals(literal.getDatatype())) {
			return rdfService.getIntLiteral(Integer.parseInt(literal.getLabel()));
		}
		if(XMLSchema.DATETIME.equals(literal.getDatatype())) {
			return rdfService.getDateLiteral(0); //TODO parse xsd:datetime string into time
		}
		if(XMLSchema.BASE64BINARY.equals(literal.getDatatype())) {
			byte[] bytes = Base64.decode(literal.getLabel());
			return rdfService.getBlobLiteral(bytes, bytes.length);
		}
		return rdfService.getLiteral(literal.getLabel());
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



}
