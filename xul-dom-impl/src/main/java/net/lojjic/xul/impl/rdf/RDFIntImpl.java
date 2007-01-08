package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFInt;
import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Graph;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.vocabulary.XmlSchema;

/**
 * Implementation of {@link RDFInt} that wraps a Sesame {@link Literal}.
 */
public class RDFIntImpl extends RDFNodeImpl implements RDFInt {

	private Literal literal;

	public RDFIntImpl(Graph graph, int value) {
		URI datatype = graph.getValueFactory().createURI(XmlSchema.INT);
		literal = graph.getValueFactory().createLiteral(String.valueOf(value), datatype);
	}

	/**
	 * The integer value of the literal
	 */
	public int getValue() {
		return Integer.parseInt(literal.getLabel());
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFIntImpl && literal.equals(((RDFIntImpl)node).literal);
	}

}
