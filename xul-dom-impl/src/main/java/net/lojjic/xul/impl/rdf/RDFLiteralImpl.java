package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFLiteral;
import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Graph;
import org.openrdf.model.Literal;

/**
 * Implementation of {@link RDFLiteral} that wraps a Sesame {@link Literal}.
 */
public class RDFLiteralImpl extends RDFNodeImpl implements RDFLiteral {

	private Literal literal;

	public RDFLiteralImpl(Graph graph, String value) {
		literal = graph.getValueFactory().createLiteral(value);
	}

	/**
	 * The Unicode string value of the literal.
	 */
	public String getValue() {
		return literal.getLabel();
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFLiteralImpl && literal.equals(((RDFLiteralImpl)node).literal);
	}

}
