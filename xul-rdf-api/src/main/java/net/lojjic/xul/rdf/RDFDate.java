package net.lojjic.xul.rdf;

/**
 * <p>RDF date literal interface</p>
 * <p>An object of this interface should be created via {@link RDFService#getDateLiteral(long)}</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFDate.html</p>
 */
public interface RDFDate extends RDFNode {

	/**
	 * The date value of the literal
	 */
	long getValue();

}
