package net.lojjic.xul.impl;

import net.lojjic.xul.XULBuilderListener;
import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFCompositeDataSourceImpl;
import net.lojjic.xul.rdf.impl.RDFContainerImpl;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.util.*;

/**
 * Implementation of {@link XULTemplateBuilder}
 */
public class XULTemplateBuilderImpl implements XULTemplateBuilder, RDFObserver {

	protected List<XULBuilderListener> listeners = new ArrayList<XULBuilderListener>();
	protected Element rootElement;
	protected Element builtElement;
	protected RDFCompositeDataSource database;
	protected RDFService rdfService;
	private boolean datasourceChanged = false;
	private boolean datasourceInBatchUpdate = false;

	/**
	 * Constructor
	 * @param rdfService The {@link RDFService} to use
	 * @param rootElement The root element to which the template builder is attached.
	 */
	protected XULTemplateBuilderImpl(RDFService rdfService, Element rootElement) {
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
		this.rootElement = element;
		EventListener listener = new EventListener() {
			public void handleEvent(Event evt) {
				rebuild();
			}
		};
		((EventTarget)rootElement).addEventListener("DOMSubtreeModified", listener, false);

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
	 * Remove a listener from this template builder.
	 */
	public void removeListener(XULBuilderListener listener) {
		listeners.remove(listener);
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
		// TODO
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

	/**
	 * Class that does the work of compiling the template and building the result
	 */
	private class XULTemplate {

		public void compileTemplate() {

		}

		public DocumentFragment buildResultDOM() {
			return null;
		}

	}






	private enum ConditionType {
		CONTENT, MEMBER, TRIPLE
	}

	private class Condition {
		private Element element;

		public Condition(Element element) {
			this.element = element;
		}

		public ConditionAttr getAttribute(String name) {
			return new ConditionAttr(element.getAttribute(name));
		}

		public ConditionType getType() {
			return ConditionType.valueOf(element.getLocalName().toUpperCase());
		}
	}

	private class ConditionAttr {
		private String value;

		public ConditionAttr(String value) {
			this.value = value;
		}

		public boolean isVariable() {
			return value.charAt(0) == '?';
		}

		public String getVarName() {
			return value;
		}

		public RDFResource getRDFResource() {
			return rdfService.getResource(value);
		}

		public RDFLiteral getRDFLiteral() {
			return rdfService.getLiteral(value);
		}
	}

	private List<Map<String, RDFNode>> matchConditions(RDFDataSource datasource, RDFResource start, List<Condition> conditions) {
		List<Map<String, RDFNode>> list = new ArrayList<Map<String, RDFNode>>();
		for(Condition condition : conditions) {
			switch(condition.getType()) {
				case CONTENT:
					Map<String, RDFNode> map = new HashMap<String, RDFNode>();
					map.put(condition.getAttribute("uri").getVarName(), start);
					list.add(map);
					break;

				case MEMBER:
					for(Map<String, RDFNode> vars : list) {
						ConditionAttr containerAttr = condition.getAttribute("container");
						ConditionAttr childAttr = condition.getAttribute("child");

						if(containerAttr.isVariable()) {
							RDFNode varValue = vars.get(containerAttr.getVarName());
							if(varValue != null && varValue instanceof RDFResource) {
								RDFContainerImpl containerImpl = new RDFContainerImpl(rdfService, datasource, (RDFResource)varValue);
								if(childAttr.isVariable()) {
									Iterator<RDFNode> children = containerImpl.getElements();
									while(children.hasNext()) {
										Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
										newVars.put(childAttr.getVarName(), children.next());
										list.add(newVars);
									}
									list.remove(vars);
								} else {
									// try as uri, then literal
									if(containerImpl.indexOf(childAttr.getRDFResource()) == -1 || containerImpl.indexOf(childAttr.getRDFLiteral()) == -1) {
										list.remove(vars);
									}
								}
							}
						}
						else if(childAttr.isVariable()) {
							RDFContainerUtils containerUtils = new RDFContainerUtilsImpl(rdfService);
							RDFNode varValue = vars.get(childAttr.getVarName());
							if(varValue != null) {
								if(containerAttr.isVariable()) {
									// Examine each incoming arc for ordinals
									Set<RDFResource> containers = new HashSet<RDFResource>();
									Iterator<RDFResource> arcsIn = datasource.arcLabelsIn(varValue);
									while(arcsIn.hasNext()) {
										RDFResource arc = arcsIn.next();
										if(containerUtils.isOrdinalProperty(arc)) {
											Iterator<RDFResource> sources = datasource.getSources(arc, varValue, true);
											while(sources.hasNext()) {
												RDFResource source = sources.next();
												if(containerUtils.isContainer(datasource, source)) {
													containers.add(source);
												}
											}
										}
									}
									for(RDFResource container : containers) {
										Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
										newVars.put(containerAttr.getVarName(), container);
										list.add(newVars);
									}
									list.remove(vars);
								} else {
									RDFResource container = containerAttr.getRDFResource();
									if(!containerUtils.isContainer(datasource, container) || containerUtils.indexOf(datasource, container, varValue) == -1) {
										list.remove(vars);
									}
								}
							}
						}
						else {
							throw new RuntimeException("Either the 'condition' or 'child' attribute of the <member /> must be a variable reference.");
						}
					}
					break;

				case TRIPLE:
					ConditionAttr subjectAttr = condition.getAttribute("subject");
					ConditionAttr predicateAttr = condition.getAttribute("predicate");
					ConditionAttr objectAttr = condition.getAttribute("object");
					for(Map<String, RDFNode> vars : list) {
						if(subjectAttr.isVariable()) {
							RDFNode varValue = vars.get(subjectAttr.getVarName());
							if(varValue != null && varValue instanceof RDFResource) {
								if(objectAttr.isVariable()) {
									Iterator<RDFNode> targets = datasource.getTargets((RDFResource) subjectAttr, predicateAttr.getRDFResource(), true);
									while(targets.hasNext()) {
										Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
										newVars.put(objectAttr.getVarName(), targets.next());
										list.add(newVars);
									}
									list.remove(vars);
								} else {
									// try as uri, then literal
									if(
										!datasource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFResource(), true) &&
										!datasource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFLiteral(), true)
									) {
										list.remove(vars);
									}
								}
							}
						}
						else if(objectAttr.isVariable()) {
							RDFNode varValue = vars.get(objectAttr.getVarName());
							if(varValue != null) {
								if(subjectAttr.isVariable()) {
									Iterator<RDFResource> sources = datasource.getSources((RDFResource) subjectAttr, predicateAttr.getRDFResource(), true);
									while(sources.hasNext()) {
										Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
										newVars.put(subjectAttr.getVarName(), sources.next());
										list.add(newVars);
									}
									list.remove(vars);
								} else {
									if(!datasource.hasAssertion(subjectAttr.getRDFResource(), predicateAttr.getRDFResource(), varValue, true)) {
										list.remove(vars);
									}
								}
							}
						}
						else {
							throw new RuntimeException("Either the 'subject' or 'object' attribute of the <triple/> must be a variable reference.");
						}
					}
					break;
			}
		}
		return list;
	}


}
