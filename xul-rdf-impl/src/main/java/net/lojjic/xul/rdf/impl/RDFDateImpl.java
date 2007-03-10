package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFDate;
import net.lojjic.xul.rdf.RDFNode;

/**
 * Implementation of {@link RDFDate}.
 */
public class RDFDateImpl extends RDFNodeImpl implements RDFDate {

	private long value;

	public RDFDateImpl(long value) {
		this.value = value;
	}

	/**
	 * The date value of the literal
	 */
	public long getValue() {
		return value;
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFDateImpl && value == ((RDFDateImpl)node).value;
	}

}
