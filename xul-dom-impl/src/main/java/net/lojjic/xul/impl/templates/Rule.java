package net.lojjic.xul.impl.templates;

import net.lojjic.xul.XULConstants;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A single &lt;rule/&gt; within a XUL &lt;template/&gt;.
 * <p>
 * To create an instance, use the {@link #createRule(net.lojjic.xul.rdf.RDFService, Template, org.w3c.dom.Element)}
 * factory method.
 */
public abstract class Rule {

	protected RDFService rdfService;
	protected Template template;
	protected Element element;
	private List<Condition> conditions;
	protected Action action;
	protected String parentTagName;

	/**
	 * Constructor
	 * @param rdfService
	 * @param template
	 * @param element
	 */
	protected Rule(RDFService rdfService, Template template, Element element) {
		this.rdfService = rdfService;
		this.template = template;
		this.element = element;
		this.conditions = parseConditions();
	}

	/**
	 * Factory method to create an instance appropriate for the given &lt;rule/&gt; element.
	 * @param rdfService
	 * @param template
	 * @param element
	 */
	public static Rule createRule(RDFService rdfService, Template template, Element element) {
		Node conditionsElement = element.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "conditions").item(0);
		if(conditionsElement == null) {
			// simple rule format
			return new SimpleRule(rdfService, template, element);
		} else {
			// extended rule format
			return new ExtendedRule(rdfService, template, element);
		}
	}

	/**
	 * Parse the rule to build a list of {@link Condition}s
	 */
	protected abstract List<Condition> parseConditions();

	protected void parseAction() {
		Element actionElement = (Element)element.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "action").item(0);
		if(actionElement != null) {
			action = new Action(actionElement);
		}
	}

	public DocumentFragment applyRule(RDFDataSource dataSource, RDFResource start, Element containerElement) {
		// Check parent tag if condition specified:
		if(parentTagName != null && !parentTagName.equals(containerElement.getNodeName())) {
			return null;
		}

		// Check conditions:
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		for(Condition condition : conditions) {
			condition.applyToVariablesList(dataSource, varsList, start);
		}

		// Generate result:
		return action.generateResult(varsList);
	}

}
