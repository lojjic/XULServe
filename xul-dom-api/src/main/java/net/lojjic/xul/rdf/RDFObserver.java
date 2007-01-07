package net.lojjic.xul.rdf;

/**
 * <p>RDF observer interface.</p>
 * <p>See http://www.xulplanet.com/references/xpcomref/ifaces/nsIRDFObserver.html</p>
 */
public interface RDFObserver {

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
	 * This method is called when a datasource is about to send several notifications at once.
	 * The observer can use this as a cue to optimize its behavior. The observer can expect the
	 * datasource to call endUpdateBatch() when the group of notifications has completed.
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

}
