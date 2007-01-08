package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFLiteral;
import net.lojjic.xul.rdf.RDFNode;

/**
 * Implementation of {@link RDFLiteral}.
 */
public class RDFLiteralImpl extends RDFNodeImpl implements RDFLiteral {

	private String value;

	public RDFLiteralImpl(String value) {
		this.value = value;
	}

	/**
	 * The Unicode string value of the literal.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFLiteralImpl && this.value.equals(((RDFLiteralImpl)node).value);
	}

}
