package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFInt;
import net.lojjic.xul.rdf.RDFNode;

/**
 * Implementation of {@link RDFInt}.
 */
public class RDFIntImpl extends RDFNodeImpl implements RDFInt {

	private int value;

	public RDFIntImpl(int value) {
		this.value = value;
	}

	/**
	 * The integer value of the literal
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFIntImpl && value == ((RDFIntImpl)node).value;
	}

}
