package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFNode;

/**
 * Abstract implementation of {@link RDFNode}.
 */
public abstract class RDFNodeImpl implements RDFNode {

	/**
	 * Compare object logical equality
	 */
	public boolean equals(Object other) {
		return other != null && other instanceof RDFNode && equalsNode((RDFNode)other);
	}

}
