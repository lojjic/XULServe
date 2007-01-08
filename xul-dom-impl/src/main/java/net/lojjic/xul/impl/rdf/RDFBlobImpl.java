package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFBlob;
import net.lojjic.xul.rdf.RDFNode;
import org.openrdf.model.Literal;
import org.openrdf.model.Graph;
import org.openrdf.model.URI;
import org.openrdf.vocabulary.XmlSchema;
import org.apache.xerces.impl.dv.util.Base64;

/**
 * Implementation of {@link RDFBlob} that wraps a Sesame {@link Literal}.
 * The literal's value is encoded as a Base64 string.
 */
public class RDFBlobImpl extends RDFNodeImpl implements RDFBlob {

	private Literal literal;

	/**
	 * Construct an RDFBlobImpl instance wrapping a {@link org.openrdf.model.Literal}
	 */
	public RDFBlobImpl(Graph graph, byte[] value) {
		URI datatype = graph.getValueFactory().createURI(XmlSchema.BASE64BINARY);
		String base64Value = Base64.encode(value);
		literal = graph.getValueFactory().createLiteral(base64Value, datatype);
	}

	/**
	 * The data's length.
	 */
	public int getLength() {
		return getValue().length;
	}

	/**
	 * The binary data.
	 */
	public byte[] getValue() {
		return Base64.decode(literal.getLabel());
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFBlobImpl && literal.equals(((RDFBlobImpl)node).literal);
	}
}
