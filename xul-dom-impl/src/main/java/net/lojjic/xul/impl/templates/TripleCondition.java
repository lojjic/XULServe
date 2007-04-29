package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;

/**
 * The &lt;triple/&gt; template rule condition
 */
public class TripleCondition extends Condition {

	private ConditionAttr subjectAttr;
	private ConditionAttr predicateAttr;
	private ConditionAttr objectAttr;

	public TripleCondition(RDFService rdfService, Element element) {
		super(rdfService, element);
		parseAttributes();
	}

	private void parseAttributes() {
		String subject = element.getAttribute("subject");
		String predicate = element.getAttribute("predicate");
		String object = element.getAttribute("object");
		if(subject == null || predicate == null || object == null) {
			throw new RuntimeException("Missing 'subject', 'predicate', or 'object' attribute on <triple/> condition.");
		}

		subjectAttr = new ConditionAttr(subject);
		predicateAttr = new ConditionAttr(predicate);
		objectAttr = new ConditionAttr(object);

		if(!subjectAttr.isVariable() && !objectAttr.isVariable()) {
			throw new RuntimeException("Either the 'subject' or 'object' attribute of the <triple/> must be a variable reference.");
		}
	}


	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		for(Map<String, RDFNode> vars : varsList) {
			if(subjectAttr.isVariable()) {
				RDFNode varValue = vars.get(subjectAttr.getVarName());
				if(varValue != null && varValue instanceof RDFResource) {
					if(objectAttr.isVariable()) {
						Iterator<RDFNode> targets = dataSource.getTargets((RDFResource) subjectAttr, predicateAttr.getRDFResource(), true);
						while(targets.hasNext()) {
							Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
							newVars.put(objectAttr.getVarName(), targets.next());
							varsList.add(newVars);
						}
						varsList.remove(vars);
					} else {
						// try as uri, then literal
						if(
							!dataSource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFResource(), true) &&
							!dataSource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFLiteral(), true)
						) {
							varsList.remove(vars);
						}
					}
				} else {
					varsList.remove(vars);
				}
			}
			else if(objectAttr.isVariable()) {
				RDFNode varValue = vars.get(objectAttr.getVarName());
				if(varValue != null) {
					if(subjectAttr.isVariable()) {
						Iterator<RDFResource> sources = dataSource.getSources((RDFResource) subjectAttr, predicateAttr.getRDFResource(), true);
						while(sources.hasNext()) {
							Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
							newVars.put(subjectAttr.getVarName(), sources.next());
							varsList.add(newVars);
						}
						varsList.remove(vars);
					} else {
						if(!dataSource.hasAssertion(subjectAttr.getRDFResource(), predicateAttr.getRDFResource(), varValue, true)) {
							varsList.remove(vars);
						}
					}
				} else {
					varsList.remove(vars);
				}
			}
		}

	}
}
