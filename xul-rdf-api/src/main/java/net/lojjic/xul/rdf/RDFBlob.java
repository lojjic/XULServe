package net.lojjic.xul.rdf;

/**
 * <p>RDF blob interface.</p>
 * <p>An object of this interface should be created via {@link RDFService#getBlobLiteral(byte[], int)}</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFBlob.html</p>
 */
public interface RDFBlob extends RDFNode {

	/**
	 * The data's length.
	 */
	int getLength();

	/**
	 * The binary data.
	 */
	byte[] getValue();

}
