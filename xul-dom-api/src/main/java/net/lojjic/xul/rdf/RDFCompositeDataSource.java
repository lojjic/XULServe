package net.lojjic.xul.rdf;

import java.util.Enumeration;

/**
 * <p>An nsIRDFCompositeDataSource composes individual data sources, providing
 * the illusion of a single, coherent RDF graph.</p>
 * <p>This interface is intended to be used as an instance. To create an
 * object implementing this interface:</p>
 * <pre><code>var obj = Components.classes["@mozilla.org/rdf/datasource;1?name=composite-datasource"].
 *         createInstance(Components.interfaces.nsIRDFCompositeDataSource);</code></pre>
 */
public interface RDFCompositeDataSource {

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 */
	boolean isAllowNegativeAssertions();

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 */
	void setAllowNegativeAssertions(boolean allow);

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	boolean isCoalesceDuplicateArcs();

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	void setCoalesceDuplicateArcs(boolean coalesce);

	/**
	 * Add a datasource the the composite data source.
	 *
	 * @param dataSource the datasource to add to composite
	 */
	void addDataSource(RDFDataSource dataSource);

	/**
	 * Retrieve the datasources in the composite data source.
	 *
	 * @return java.util.Enumeration that will enumerate each of the datasources in the composite
	 */
	Enumeration<RDFDataSource> getDataSources();

	/**
	 * Remove a datasource from the composite data source.
	 *
	 * @param dataSource: the datasource to remove from the composite
	 */
	void removeDataSource(RDFDataSource dataSource);

}
