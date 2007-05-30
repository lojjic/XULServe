package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFService;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.ArrayList;

/**
 * A {@link net.lojjic.xul.impl.templates.Rule} for the simple rule syntax
 */
public class SimpleRule extends Rule {

	/**
	 * Constructor
	 *
	 * @param rdfService
	 * @param template
	 * @param element
	 */
	protected SimpleRule(RDFService rdfService, Template template, Element element) {
		super(rdfService, template, element);
	}

	/**
	 * Parse the rule to build a list of {@link Condition}s
	 */
	protected List<Condition> parseConditions() {
		List<Condition> conditions = new ArrayList<Condition>();

		String startVarName = template.getContainerVarName();
		conditions.add(new ContentCondition(rdfService, "?1"));
		NamedNodeMap attrs = element.getAttributes();
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

		return conditions;
	}
	
}
