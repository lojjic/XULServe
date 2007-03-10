package net.lojjic.xul.rdf;

/**
 * <p>RDF literal interface.</p>
 * <p>A literal node in the graph, whose value is a string.</p>
 * <p>An object of this interface should be created via {@link RDFService#getLiteral(String)}</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFLiteral.html</p>
 */
public interface RDFLiteral extends RDFNode {

	/**
	 * The Unicode string value of the literal.
	 */
	String getValue();

//	/**
//	 * An unscriptable version used to avoid a string copy. Meant for use as a performance optimization.
//	 */
//	void getValueConst ( out PRUnichar* constValue );

}
