package net.lojjic.xul;

import org.w3c.dom.Element;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFCompositeDataSource;

/**
 * XUL template builder interface.
 * See http://xulplanet.com/references/objref/XULTemplateBuilder.html
 */
public interface XULTemplateBuilder {

	/**
	 * The "root" node in the DOM to which this builder is attached
	 */
	Element getRoot();

	/**
	 * The composite datasource that the template builder observes and uses to create content
	 */
	RDFCompositeDataSource getDatabase();

	/**
	 * Add a listener to this template builder. The template builder holds a strong reference to the listener.
	 */
	void addListener(XULBuilderListener listener);

// TODO
//	/**
//	 * Invoked lazily by a XUL element that needs its child content built.
//	 */
//	void createContents(Content_ptr element);

// TODO
//	/**
//	 * Called to initialize a XUL content builder on a particular root element. This element presumably
//	 * has a "datasources" attribute, which the builder will parse to set up the template builder's datasources.
//	 *
//	 * @param element
//	 */
//	void init(Content_ptr element);

	/**
	 * Force the template builder to rebuild its content.
	 */
	void rebuild();

	/**
	 * Reload any of our RDF datasources that support RDFRemoteDatasource.
	 */
	void refresh();

	/**
	 * Remove a listener from this template builder.
	 */
	void removeListener(XULBuilderListener listener);

}
