package net.lojjic.xul.impl.templates;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentFragment;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.XULConstants;

import java.util.List;
import java.util.ArrayList;

/**
 * A XUL &lt;template/&gt;
 */
public class Template {

	private RDFService rdfService;
	private Element element;
	private List<Rule> rules;
	private String containerVarName;
	private String memberVarName;

	public Template(RDFService rdfService, Element element) {
		this.rdfService = rdfService;
		this.element = element;
		parseRules();
	}

	private void parseRules() {
		rules = new ArrayList<Rule>();

		// Find all the direct child <rule/> elements:
		List<Element> ruleElements = new ArrayList<Element>();
		NodeList children = element.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if(child.getNodeType() == Node.ELEMENT_NODE && XULConstants.XUL_NAMESPACE.equals(child.getNamespaceURI())
					&& "rule".equals(child.getLocalName())) {
				ruleElements.add((Element)child);
			}
		}

		// If no <rule/>s are present, treat the <template/> as the rule (shorthand form):
		if(ruleElements.size() == 0) {
			rules.add(new Rule(rdfService, this, this.element));
			return;
		}

		// Look for custom container/member variable names:
		containerVarName = element.getAttribute("container");
		memberVarName = element.getAttribute("member");

		// Compile all rules:
		for(Element ruleElement : ruleElements) {
			rules.add(new Rule(rdfService, this, ruleElement));
		}
	}

	public DocumentFragment generateContent(RDFDataSource dataSource, RDFResource start, Element containerElement) {
		for(Rule rule : rules) {
			DocumentFragment result = rule.applyRule(dataSource, start, containerElement);
			if(result != null) {
				return result;
			}
		}
		return null;
	}

	public String getContainerVarName() {
		return (containerVarName == null) ? "?start" : containerVarName;
	}

	public String getMemberVarName() {
		return (memberVarName == null) ? "?member" : memberVarName;
	}
}
