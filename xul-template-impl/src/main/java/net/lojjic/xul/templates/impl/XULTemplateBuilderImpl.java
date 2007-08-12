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
	protected XULTemplateQueryProcessor queryProcessor;
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
	 * {@inheritDoc}
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

		// Initialize the query processor:
		initQueryProcessor();

		// Add observer to rebuild when datasource changes:
		this.database.addObserver(this);

		// Compile the template:
		rebuild();
	}

	/**
	 * Initialize the {@link net.lojjic.xul.templates.XULTemplateQueryProcessor} instance
	 */
	private void initQueryProcessor() {
		// TODO allow looking up other implementions based on the querytype attribute
		this.queryProcessor = new XULTemplateQueryProcessorRDFImpl();
	}

	/**
	 * {@inheritDoc}
	 */
	public void createContents(Element element) {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	public void addListener(XULBuilderListener listener) {
		listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public RDFCompositeDataSource getDatabase() {
		return database;
	}

	/**
	 * {@inheritDoc}
	 */
	public XULTemplateResult getRootResult() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public XULTemplateQueryProcessor getQueryProcessor() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public Element getRoot() {
		return rootElement;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public void addResult(XULTemplateResult aResult, Node aQueryNode) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeResult(XULTemplateResult aResult) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void replaceResult(XULTemplateResult aOldResult, XULTemplateResult aNewResult, Node aQueryNode) throws NullPointerException, IllegalArgumentException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void resultBindingChanged(XULTemplateResult aResult) throws NullPointerException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public XULTemplateResult getResultForId(String aId) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public XULTemplateResult getResultForContent(Element aElement) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasGeneratedContent(RDFResource aNode, String aTag) {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void addRuleFilter(Node aRule, XULTemplateRuleFilter aFilter) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(XULBuilderListener listener) {
		listeners.remove(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onBeginUpdateBatch(RDFDataSource dataSource) {
		datasourceInBatchUpdate = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void onEndUpdateBatch(RDFDataSource dataSource) {
		if(datasourceChanged) {
			rebuild();
		}
		datasourceChanged = false;
		datasourceInBatchUpdate = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
		datasourceChanged = true;
		if(!datasourceInBatchUpdate) {
			rebuild();
			datasourceChanged = false;
		}
	}

}
