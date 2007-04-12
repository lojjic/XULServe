package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFNode;

/**
 * Implementation of {@link RDFResource}
 */
public class RDFResourceImpl extends RDFNodeImpl implements RDFResource {

	private String uri;

	/**
	 * Constructor for an anonymous resource
	 */
	public RDFResourceImpl() {
		init(null);
	}

	/**
	 * Constructor for a URI (non-anonymous) resource
	 */
	public RDFResourceImpl(String uri) {
		init(uri);
	}

	/**
	 * The single-byte string value of the resource.
	 */
	public String getValue() {
		// XXX what should this return for blank node? Currently returns null.
		return uri;
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
		// XXX what should this return for blank node? Currently returns true if uri param is null.
		return ((this.uri == null && uri == null) || (this.uri != null && this.uri.equals(uri)));
	}

	/**
	 * This method is called by the RDFService after constructing a resource object to
	 * initialize it's URI. You would not normally call this method directly.
	 */
	public void init(String uri) {
		this.uri = uri;
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFResourceImpl && this.uri.equals(((RDFResourceImpl)node).uri);
	}
}
