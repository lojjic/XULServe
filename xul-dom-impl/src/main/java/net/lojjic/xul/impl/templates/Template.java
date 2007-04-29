package net.lojjic.xul.impl.templates;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import net.lojjic.xul.rdf.RDFService;
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

	public Template(RDFService rdfService, Element element) {
		this.rdfService = rdfService;
		this.element = element;
		parseRules();
	}

	private void parseRules() {
		rules = new ArrayList<Rule>();
		NodeList children = element.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if(child.getNodeType() == Node.ELEMENT_NODE && XULConstants.XUL_NAMESPACE.equals(child.getNamespaceURI()) && "rule".equals(child.getLocalName())) {
				rules.add(new Rule(rdfService, (Element)child));
			} else {
				throw new RuntimeException("Element " + child.getNodeName() + " not allowed as child of <template/>.");
			}
		}
	}

}
