package net.lojjic.xul.templates.impl;

import net.lojjic.xul.rdf.RDFService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Rule} for the extended rule syntax
 */
public class ExtendedRule extends Rule implements Constants {

	/**
	 * Constructor
	 *
	 * @param rdfService
	 * @param template
	 * @param element
	 */
	protected ExtendedRule(RDFService rdfService, Template template, Element element) {
		super(rdfService, template, element);
	}

	/**
	 * Parse the rule to build a list of {@link Condition}s
	 */
	protected List<Condition> parseConditions() {
		List<Condition> conditions = new ArrayList<Condition>();

		Node conditionsElement = element.getElementsByTagNameNS(XUL_NAMESPACE, "conditions").item(0);
		NodeList children = conditionsElement.getChildNodes();

		for(int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if(node instanceof Element) {
				if(XUL_NAMESPACE.equals(node.getNamespaceURI())) {
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

		return conditions;
	}


}
