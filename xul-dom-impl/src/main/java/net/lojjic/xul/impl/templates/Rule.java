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
 * A single &lt;rule/&gt; within a XUL &lt;template/&gt;
 */
public class Rule {

	private RDFService rdfService;
	private Template template;
	private Element element;
	private List<Condition> conditions;
	private Action action;
	private String parentTagName;

	/**
	 * Constructor
	 * @param rdfService
	 * @param element
	 */
	public Rule(RDFService rdfService, Template template, Element element) {
		this.rdfService = rdfService;
		this.template = template;
		this.element = element;
		parseConditions();
		parseAction();
	}

	private void parseConditions() {
		conditions = new ArrayList<Condition>();

		Node conditionsElement = element.getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "conditions").item(0);
		if(conditionsElement == null) {
			// simple rule format
			parseSimpleConditions(element);
		} else {
			// extended rule format
			parseExtendedConditions((Element)conditionsElement);
		}
	}

	private void parseSimpleConditions(Element ruleElement) {
		String startVarName = template.getContainerVarName();
		conditions.add(new ContentCondition(rdfService, "?1"));
		NamedNodeMap attrs = ruleElement.getAttributes();
		for(int i = 0; i < attrs.getLength(); i++) {
			Attr attr = (Attr)attrs.item(i);
			if("parent".equals(attr.getLocalName())) {
				parentTagName = attr.getNodeValue();
			}
			else if("iscontainer".equals(attr.getLocalName())) {
				conditions.add(new IsContainerCondition(rdfService, startVarName, Boolean.valueOf(attr.getNodeValue())));
			}
			else if("isempty".equals(attr.getLocalName())) {
				conditions.add(new IsEmptyCondition(rdfService, startVarName, Boolean.valueOf(attr.getNodeValue())));
			}
			else {
				conditions.add(new TripleCondition(rdfService, startVarName, attr.getNamespaceURI() + attr.getLocalName(), "?" + (++i)));
			}
		}
	}

	private void parseExtendedConditions(Element conditionsElement) {
		NodeList children = conditionsElement.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if(node instanceof Element) {
				if(XULConstants.XUL_NAMESPACE.equals(node.getNamespaceURI())) {
					if("content".equals(node.getLocalName())) {
						String uri = ((Element)node).getAttribute("uri");
						if(uri == null) {
							throw new RuntimeException("Missing 'uri' attribute on <content/> condition.");
						}
						conditions.add(new ContentCondition(rdfService, uri));

						// Parent tag condition:
						String tag = ((Element)node).getAttribute("tag");
						if(tag != null) {
							parentTagName = tag;
						}

						continue;
					}

					if(conditions.isEmpty()) {
						throw new RuntimeException("The first child of <conditions/> must be <content/>.");
					}

					if("member".equals(node.getLocalName())) {
						String container = ((Element)node).getAttribute("container");
						String child = ((Element)node).getAttribute("child");
						if(container == null || child == null) {
							throw new RuntimeException("Missing 'container' or 'child' attribute on <member/> condition.");
						}
						conditions.add(new MemberCondition(rdfService, container, child));
						continue;
					}

					if("triple".equals(node.getLocalName())) {
						String subject = ((Element)node).getAttribute("subject");
						String predicate = ((Element)node).getAttribute("predicate");
						String object = ((Element)node).getAttribute("object");
						if(subject == null || predicate == null || object == null) {
							throw new RuntimeException("Missing 'subject', 'predicate', or 'object' attribute on <triple/> condition.");
						}
						conditions.add(new TripleCondition(rdfService, subject, predicate, object));
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
