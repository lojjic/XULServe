package net.lojjic.xul;

import org.w3c.dom.Element;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;

/**
 * XUL template builder interface.
 * See http://xulplanet.com/references/objref/XULTemplateBuilder.html
 */
public interface XULTemplateBuilder {

	/**
	 * The ``root'' node in the DOM to which this builder is attached
	 */
	Element getRoot();


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
	 * This method is called whenever a new assertion is made in the data source
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target);

	/**
	 * This method is called when a datasource is about to send several notifications at once. The
	 * observer can use this as a cue to optimize its behavior. The observer can expect the datasource
	 * to call endUpdateBatch() when the group of notifications has completed.
	 *
	 * @param dataSource the datasource that is going to be issuing the notifications.
	 */
	void onBeginUpdateBatch(RDFDataSource dataSource);

	/**
	 * This method is called when the object of an assertion changes from one value to another.
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param oldTarget  the old object of the assertion
	 * @param newTarget  the new object of the assertion
	 */
	void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget);

	/**
	 * This method is called when a datasource has completed issuing a notification group.
	 *
	 * @param dataSource the datasource that has finished issuing a group of notifications
	 */
	void onEndUpdateBatch(RDFDataSource dataSource);

	/**
	 * This method is called when the subject of an assertion changes from one value to another.
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param oldSource  the old subject of the assertion
	 * @param newSource  the new subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target);

	/**
	 * This method is called whenever an assertion is removed from the data source
	 *
	 * @param dataSource the datasource that is issuing the notification.
	 * @param source     the subject of the assertion
	 * @param property   the predicate of the assertion
	 * @param target     the object of the assertion
	 */
	void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target);

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
