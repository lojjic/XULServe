package net.lojjic.xul.rdf;

/**
 * <p>RDF resource interface.</p>
 * <p>An RDFResource is an object that has unique identity in the RDF data model. The
 * object's identity is determined by its URI.  An object of this interface should
 * be created via {@link RDFService#getResource(String)}</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFResource.html</p>
 */
public interface RDFResource extends RDFNode {

	/**
	 * The single-byte string value of the resource.
	 */
	String getValue();

	/**
	 * The UTF-8 URI of the resource.
	 */
	String getValueUTF8();


	/**
	 * Determine if the resource has the given URI.
	 */
	boolean equalsString(String uri);

// TODO
//	/**
//	 * <p>Retrieve the "delegate" object for this resource. A resource may have several delegate
//	 * objects, each of whose lifetimes is bound to the life of the resource object.</p>
//	 * <p/>
//	 * <p>This method will return the delegate for the given key after QueryInterface()-ing it to
//	 * the requested IID.</p>
//	 * <p/>
//	 * <p>If no delegate exists for the specified key, this method will attempt to create one using
//	 * the component manager. Specifically, it will combine key with the resource's URI scheme to
//	 * produce a ContractID as follows:</p>
//	 * <p/>
//	 * <pre><code>Component:/rdf/delegate-factory/[key]/[scheme]</code></pre>
//	 * <p/>
//	 * This ContractID will be used to locate a factory using the FindFactory() method of
//	 * nsIComponentManager. If the nsIFactory exists, it will be used to create a
//	 * "delegate factory"; that is, an object that supports nsIRDFDelegateFactory. The
//	 * delegate factory will be used to construct the delegate object.
//	 *
//	 * @param IID
//	 * @param nsQIResult
//	 */
//	void getDelegate(String key, nsIIDRef IID, out nsQIResult*result);

	/**
	 * This method is called by the RDFService after constructing a resource object to
	 * initialize it's URI. You would not normally call this method directly.
	 */
	void init(String uri);

//	/**
//	 * <p>Force a delegate to be "unbound" from the resource.</p>
//	 * <p/>
//	 * <p>Normally, a delegate object's lifetime will be identical to that of the resource to
//	 * which it is bound; this method allows a delegate to unlink itself from an RDF resource
//	 * prematurely.</p>
//	 */
//	void releaseDelegate(String key);

}
