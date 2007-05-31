package net.lojjic.xul.templates.impl;

import net.lojjic.xul.templates.*;
import net.lojjic.xul.rdf.impl.RDFCompositeDataSourceImpl;
import net.lojjic.xul.rdf.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link XULTemplateBuilder}
 */
public class XULTemplateBuilderImpl implements XULTemplateBuilder, RDFObserver, Constants {

	protected List<XULBuilderListener> listeners = new ArrayList<XULBuilderListener>();
	protected Element rootElement;
	protected Template template;
	protected RDFCompositeDataSource database;
	protected RDFService rdfService;
	private boolean datasourceChanged = false;
	private boolean datasourceInBatchUpdate = false;

	/**
	 * Constructor
	 * @param rdfService The {@link RDFService} to use
	 * @param rootElement The root element to which the template builder is attached.
	 */
	public XULTemplateBuilderImpl(RDFService rdfService, Element rootElement) {
		this.rdfService = rdfService;
		init(rootElement);
	}

	/**
	 * Called to initialize a XUL content builder on a particular root element. This element presumably
	 * has a "datasources" attribute, which the builder will parse to set up the template builder's datasources.
	 */
	public void init(Element element) {
		// Set the root element, adding a mutation event listener to trigger
		// a rebuild when the template DOM changes:
		rootElement = element;

		// Create the composite datasource:
		this.database = new RDFCompositeDataSourceImpl(rdfService);

		// Parse the 'datasources' attribute and load its parts into the composite datasource
		String datasources = rootElement.getAttribute("datasources");
		if(datasources == null) {
			throw new IllegalStateException("Template root element missing 'datasources' attribute.");
		}
		for(String uri : datasources.split("[\\s]+")) {
			this.database.addDataSource(rdfService.getDataSource(uri));
		}

		// Add observer to rebuild when datasource changes:
		this.database.addObserver(this);

		// Compile the template:
		rebuild();
	}

	/**
	 * Invoked lazily by a XUL element that needs its child content built.
	 */
	public void createContents(Element element) {
		// TODO
	}

	/**
	 * Add a listener to this template builder. The template builder holds a strong reference to the listener.
	 */
	public void addListener(XULBuilderListener listener) {
		listeners.add(listener);
	}

	/**
	 * The composite datasource that the template builder observes and uses to create content
	 */
	public RDFCompositeDataSource getDatabase() {
		return database;
	}

	/**
	 * The virtual result representing the starting reference point,
	 * determined by calling the query processor's translateRef method
	 * with the root node's ref attribute as an argument.
	 */
	public XULTemplateResult getRootResult() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * The query processor used to generate results.
	 * <p/>
	 * Not scriptable.
	 */
	public XULTemplateQueryProcessor getQueryProcessor() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * The "root" node in the DOM to which this builder is attached
	 */
	public Element getRoot() {
		return rootElement;
	}

	/**
	 * Force the template builder to rebuild its content.
	 */
	public void rebuild() {
		notifyWillRebuild();
		doRebuild();
		notifyDidRebuild();
	}

	/**
	 * Notify listeners that rebuild is about to start
	 */
	private void notifyWillRebuild() {
		for(XULBuilderListener listener : listeners) {
			listener.willRebuild(this);
		}
	}

	/**
	 * Notify listeners that rebuild just finished
	 */
	private void notifyDidRebuild() {
		for(XULBuilderListener listener : listeners) {
			listener.didRebuild(this);
		}
	}

	/**
	 * Perform the rebuild.
	 */
	protected void doRebuild() {
		Element templateElement;

		// If root element has a @template attribute, look for a <template/> element with that id:
		String templateId = rootElement.getAttribute("template");
		if(templateId != null) {
			templateElement = rootElement.getOwnerDocument().getElementById(templateId);
			if(templateElement == null || (XUL_NAMESPACE.equals(templateElement.getNamespaceURI())
					&& "template".equals(templateElement.getLocalName()))) {
				throw new RuntimeException("No <template/> element exists with id '" + templateId + "'.");
			}
		}
		// Otherwise look for a child <template/> element:
		else {
			templateElement = (Element)rootElement.getElementsByTagNameNS(XUL_NAMESPACE, "template").item(0);
			if(templateElement == null || templateElement.getParentNode() != rootElement) {
				throw new RuntimeException("No child <template/> element was found.");
			}
		}

		// Compile the template:
		template = new Template(rdfService, templateElement);
	}

	/**
	 * Reload any of our RDF datasources that support RDFRemoteDatasource.
	 */
	public void refresh() {
		Iterator<RDFDataSource> dataSources = database.getDataSources();
		while(dataSources.hasNext()) {
			RDFDataSource source = dataSources.next();
			if(source instanceof RDFRemoteDataSource) {
				((RDFRemoteDataSource)source).refresh(true);
			}
		}
	}

	/**
	 * Inform the template builder that a new result is available. The builder
	 * will add this result to the set of results. The query node that the
	 * new result applies to must be specified using the aQueryNode parameter.
	 * <p/>
	 * The builder will apply the rules associated with the query to the new
	 * result, unless a result with the same id from an earlier query
	 * supersedes it, and the result's RuleMatched method will be called if it
	 * matches.
	 *
	 * @param aResult    the result to add
	 * @param aQueryNode the query that the result applies to
	 * @throws NullPointerException if aResult or aQueryNode are null
	 */
	public void addResult(XULTemplateResult aResult, Node aQueryNode) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Inform the template builder that a result no longer applies. The builder
	 * will call the remove content generated for the result, if any. If a different
	 * query would then match instead, it will become the active match. This
	 * method will have no effect if the result isn't known to the builder.
	 *
	 * @param aResult the result to remove
	 * @throws NullPointerException if aResult is null
	 */
	public void removeResult(XULTemplateResult aResult) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Inform the template builder that one result should be replaced with
	 * another. Both the old result (aOldResult) and the new result
	 * (aNewResult) must have the same id. The query node that the new result
	 * applies to must be specified using the aQueryNode parameter.
	 * <p/>
	 * This method is expected to have the same effect as calling both
	 * removeResult for the old result and addResult for the new result.
	 *
	 * @param aOldResult the old result
	 * @param aNewResult the new result
	 * @param aQueryNode the query that the new result applies to
	 * @throws NullPointerException     if either argument is null
	 * @throws IllegalArgumentException if the ids don't match
	 */
	public void replaceResult(XULTemplateResult aOldResult, XULTemplateResult aNewResult, Node aQueryNode) throws NullPointerException, IllegalArgumentException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Inform the template builder that one or more of the optional bindings
	 * for a result has changed. In this case, the rules are not reapplied as
	 * it is expected that the same rule will still apply. The builder will
	 * resynchronize any variables that are referenced in the action body.
	 *
	 * @param aResult the result to change
	 * @throws NullPointerException if aResult is null
	 */
	public void resultBindingChanged(XULTemplateResult aResult) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Return the result for a given id. Only one such result is returned and
	 * is always the result with that id associated with the active match.
	 * This method will return null is there is no result for the id.
	 *
	 * @param aId the id to return the result for
	 */
	public XULTemplateResult getResultForId(String aId) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Retrieve the result corresponding to a generated element, or null is
	 * there isn't one.
	 *
	 * @param aElement element to result the result of
	 */
	public XULTemplateResult getResultForContent(Element aElement) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Returns true if the node has content generated for it. This method is
	 * intended to be called only by the RDF query processor. If aTag is set,
	 * the content must have a tag name that matches aTag. aTag may be ignored
	 * for builders that don't generate real DOM content.
	 *
	 * @param aNode node to check
	 * @param aTag  tag that must match
	 */
	public boolean hasGeneratedContent(RDFResource aNode, String aTag) {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Adds a rule filter for a given rule, which may be used for specialized
	 * rule filtering. Any existing filter on the rule is removed. The default
	 * conditions specified inside the &lt;rule> tag are applied before the
	 * rule filter is applied, meaning that the filter may be used to further
	 * filter out results but not reaccept results that have already been
	 * rejected.
	 *
	 * @param aRule   the rule to apply the filter to
	 * @param aFilter the filter to add
	 */
	public void addRuleFilter(Node aRule, XULTemplateRuleFilter aFilter) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Remove a listener from this template builder.
	 */
	public void removeListener(XULBuilderListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onBeginUpdateBatch(net.lojjic.xul.rdf.RDFDataSource)
	 */
	public void onBeginUpdateBatch(RDFDataSource dataSource) {
		datasourceInBatchUpdate = true;
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onEndUpdateBatch(net.lojjic.xul.rdf.RDFDataSource)
	 */
	public void onEndUpdateBatch(RDFDataSource dataSource) {
		if(datasourceChanged) {
			rebuild();
		}
		datasourceChanged = false;
		datasourceInBatchUpdate = false;
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onAssert(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onChange(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onMove(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)
	 */
	public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * @see net.lojjic.xul.rdf.RDFObserver#onUnassert(net.lojjic.xul.rdf.RDFDataSource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFResource, net.lojjic.xul.rdf.RDFNode)  
	 */
	public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

}
