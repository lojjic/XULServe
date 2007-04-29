package net.lojjic.xul.impl.templates;

import net.lojjic.xul.XULConstants;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DocumentFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A single &lt;rule/&gt; within a XUL &lt;template/&gt;
 */
public class Rule {

	private RDFService rdfService;
	private Element element;
	private List<Condition> conditions;
	private Action action;

	/**
	 * Constructor
	 * @param rdfService
	 * @param element
	 */
	public Rule(RDFService rdfService, Element element) {
		this.rdfService = rdfService;
		this.element = element;
		parseConditions();
		parseAction();
	}

	private void parseConditions() {
		conditions = new ArrayList<Condition>();

		Node conditionsElement = element.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "conditions").item(0);

		NodeList children = conditionsElement.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if(node instanceof Element) {
				if(XULConstants.XUL_NAMESPACE.equals(node.getNamespaceURI())) {
					if("content".equals(node.getLocalName())) {
						conditions.add(new ContentCondition(rdfService, (Element)node));
						continue;
					}

					if(conditions.isEmpty()) {
						throw new RuntimeException("The first child of <conditions/> must be <content/>.");
					}

					if("member".equals(node.getLocalName())) {
						conditions.add(new MemberCondition(rdfService, (Element)node));
						continue;
					}

					if("triple".equals(node.getLocalName())) {
						conditions.add(new TripleCondition(rdfService, (Element)node));
						continue;
					}
				}
				throw new RuntimeException("Element " + node.getNodeName() + " is not allowed as a child of <conditions/>.");
			}
		}
	}

	private void parseAction() {
		Element actionElement = (Element)element.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "action").item(0);
		if(actionElement != null) {
			action = new Action(actionElement);
		}
	}

	public DocumentFragment applyRule(RDFDataSource dataSource, RDFResource start) {
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		for(Condition condition : conditions) {
			condition.applyToVariablesList(dataSource, varsList, start);
		}
		return action.generateResult(varsList);
	}

}
