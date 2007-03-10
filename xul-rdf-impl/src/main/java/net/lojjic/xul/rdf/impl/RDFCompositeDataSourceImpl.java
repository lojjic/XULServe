package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFCompositeDataSource;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFService;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of {@link net.lojjic.xul.rdf.RDFCompositeDataSource}
 */
public class RDFCompositeDataSourceImpl extends RDFMemoryDataSourceImpl implements RDFCompositeDataSource {

	private boolean allowNegativeAssertions;
	private boolean coalesceDuplicateArcs;
	private List<RDFDataSource> dataSources = new ArrayList<RDFDataSource>();

	/**
	 * Constructor.
	 */
	public RDFCompositeDataSourceImpl(RDFService rdfService) {
		super(rdfService);
	}

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 */
	public boolean isAllowNegativeAssertions() {
		return allowNegativeAssertions;
	}

	/**
	 * <p>Set this value to true if the composite datasource may contains at least one datasource
	 * that has negative assertions. (This is the default.)</p>
	 * <p>Set this value to false if none of the datasources being composed contains a negative
	 * assertion. This allows the composite datasource to perform some query optimizations.</p>
	 * <p>By default, this value is true.</p>
	 */
	public void setAllowNegativeAssertions(boolean allow) {
		this.allowNegativeAssertions = allow;
	}

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	public boolean isCoalesceDuplicateArcs() {
		return coalesceDuplicateArcs;
	}

	/**
	 * <p>Set to true if the composite datasource should take care to coalesce duplicate arcs when
	 * returning values from queries. (This is the default.)</p>
	 * <p>Set to false if the composite datasource shouldn't bother to check for duplicates. This
	 * allows the composite datasource to more efficiently answer queries.</p>
	 * <p>By default, this value is true.</p>
	 */
	public void setCoalesceDuplicateArcs(boolean coalesce) {
		this.coalesceDuplicateArcs = coalesce;
	}

	/**
	 * Add a datasource the the composite data source.
	 *
	 * @param dataSource the datasource to add to composite
	 */
	public void addDataSource(RDFDataSource dataSource) {
		dataSources.add(dataSource);
	}

	/**
	 * Retrieve the datasources in the composite data source.
	 *
	 * @return java.util.Enumeration that will enumerate each of the datasources in the composite
	 */
	public Iterator<RDFDataSource> getDataSources() {
		return dataSources.iterator();
	}

	/**
	 * Remove a datasource from the composite data source.
	 *
	 * @param dataSource: the datasource to remove from the composite
	 */
	public void removeDataSource(RDFDataSource dataSource) {
		dataSources.remove(dataSource);
	}
}
