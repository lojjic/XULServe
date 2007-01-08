package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Graph;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;

/**
 * Implementation of {@link RDFResource} that wraps a Sesame {@link Resource}.
 * It can either be a URI resource or a blank node resource, depending on the
 * constructor used.
 */
public class RDFResourceImpl extends RDFNodeImpl implements RDFResource {

	private Resource resource;

	/**
	 * Constructor for an anonymous resource
	 * @param graph
	 */
	public RDFResourceImpl(Graph graph) {
		this.resource = graph.getValueFactory().createBNode();
	}

	/**
	 * Constructor for a URI (non-anonymous) resource
	 */
	public RDFResourceImpl(Graph graph, String uri) {
		this.resource = graph.getValueFactory().createURI(uri);
	}

	/**
	 * The single-byte string value of the resource.
	 */
	public String getValue() {
		// XXX what should this return for blank node? Currently returns null.
		return (resource instanceof URI) ? ((URI)resource).getURI() : null;
	}

	/**
	 * The UTF-8 URI of the resource.
	 */
	public String getValueUTF8() {
		return getValue();
	}

	/**
	 * Determine if the resource has the given URI.
	 */
	public boolean equalsString(String uri) {
		// XXX what should this return for blank node? Currently returns false.
		return (resource instanceof URI) && ((URI) resource).getURI().equals(uri);
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFResourceImpl && resource.equals(((RDFResourceImpl)node).resource);
	}
}
