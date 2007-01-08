package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Graph;

/**
 * Implementation of {@link RDFNode} that uses Sesame. This class is
 * abstract so it can be shared by the specific RDF node type impls.
 */
public abstract class RDFNodeImpl implements RDFNode {

	/**
	 * Compare node equality
	 */
	public abstract boolean equalsNode(RDFNode node);

	/**
	 * Compare object logical equality
	 */
	public boolean equals(Object other) {
		return other instanceof RDFNode && equalsNode((RDFNode)other);
	}
}
