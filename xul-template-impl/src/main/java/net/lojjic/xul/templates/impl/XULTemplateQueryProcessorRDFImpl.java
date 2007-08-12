package net.lojjic.xul.templates.impl;

import net.lojjic.xul.templates.XULTemplateQueryProcessor;
import net.lojjic.xul.templates.XULTemplateResult;
import net.lojjic.xul.templates.XULTemplateBuilder;
import net.lojjic.xul.rdf.RDFCompositeDataSource;
import org.w3c.dom.Node;

import java.util.Iterator;

/**
 * RDF-based implementation of {@link net.lojjic.xul.templates.XULTemplateQueryProcessor}
 * <p>
 * {@inheritDoc}
 */
public class XULTemplateQueryProcessorRDFImpl implements XULTemplateQueryProcessor {

	private RDFCompositeDataSource dataSource;
	private XULTemplateBuilder builder;
	private Node rootNode;
	private boolean hasGeneratedResults;

	/**
	 * {@inheritDoc}
	 */
	public void initializeForBuilding(Object datasource, XULTemplateBuilder builder, Node rootNode)
			throws IllegalArgumentException, IllegalStateException {
		if(hasGeneratedResults) {
			throw new IllegalStateException("initializeForBuilding() cannot be called after generateResults() has already been called.");
		}
		if(!(datasource instanceof RDFCompositeDataSource)) {
			throw new IllegalArgumentException("The datasource must be an instance of RDFCompositeDataSource.");
		}
		this.dataSource = (RDFCompositeDataSource)datasource;
		this.builder = builder;
		this.rootNode = rootNode;
	}

	/**
	 * {@inheritDoc}
	 */
	public void done() {
		dataSource = null;
		builder = null;
		rootNode = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object compileQuery(XULTemplateBuilder builder, Node query, String refVariable, String memberVariable) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<XULTemplateResult> generateResults(Object datasource, XULTemplateResult ref, Object query)
			throws IllegalArgumentException {

		hasGeneratedResults = true;
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public void addBinding(Node ruleNode, String var, String ref, String expr) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public XULTemplateResult translateRef(Object datasource, String refString) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareResults(XULTemplateResult left, XULTemplateResult right, String var) {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}
}