package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFDate;
import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Graph;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.vocabulary.XmlSchema;

/**
 * Implementation of {@link RDFDate} that wraps a Sesame {@link Literal}.
 */
public class RDFDateImpl extends RDFNodeImpl implements RDFDate {

	private Literal literal;

	public RDFDateImpl(Graph graph, long value) {
		URI datatype = graph.getValueFactory().createURI(XmlSchema.LONG);
		literal = graph.getValueFactory().createLiteral(String.valueOf(value), datatype);
	}

	/**
	 * The date value of the literal
	 */
	public long getValue() {
		return Long.parseLong(literal.getLabel());
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFDateImpl && literal.equals(((RDFDateImpl)node).literal);
	}

}
