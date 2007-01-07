package net.lojjic.xul.rdf;

/**
 * <p>RDF integer literal interface.</p>
 * <p>An object of this interface should be created via {@link RDFService#getIntLiteral(int)}</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFInt.html</p>
 */
public interface RDFInt extends RDFNode {

	/**
	 * The integer value of the literal
	 */
	int getValue();

}
