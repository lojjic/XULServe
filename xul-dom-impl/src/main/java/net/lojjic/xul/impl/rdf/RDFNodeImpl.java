package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFNode;

/**
 * Abstract implementation of {@link RDFNode}.
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
