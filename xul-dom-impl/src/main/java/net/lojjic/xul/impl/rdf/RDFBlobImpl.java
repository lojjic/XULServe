package net.lojjic.xul.impl.rdf;

import net.lojjic.xul.rdf.RDFBlob;
import net.lojjic.xul.rdf.RDFNode;

import java.util.Arrays;

/**
 * Implementation of {@link RDFBlob}.
 */
public class RDFBlobImpl extends RDFNodeImpl implements RDFBlob {

	private byte[] value;

	/**
	 * Constructor
	 */
	public RDFBlobImpl(byte[] value) {
		this.value = value;
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
		return value;
	}

	/**
	 * Compare node equality
	 */
	public boolean equalsNode(RDFNode node) {
		return node != null && node instanceof RDFBlobImpl && Arrays.equals(value, ((RDFBlobImpl)node).value);
	}
}
