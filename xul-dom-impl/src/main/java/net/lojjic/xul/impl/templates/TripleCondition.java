package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;

import java.util.*;

/**
 * The &lt;triple/&gt; template rule condition
 */
public class TripleCondition extends Condition {

	private ConditionAttr subjectAttr;
	private ConditionAttr predicateAttr;
	private ConditionAttr objectAttr;

	public TripleCondition(RDFService rdfService, String subject, String predicate, String object) {
		super(rdfService);
		subjectAttr = new ConditionAttr(subject);
		predicateAttr = new ConditionAttr(predicate);
		objectAttr = new ConditionAttr(object);
		if(!subjectAttr.isVariable() && !objectAttr.isVariable()) {
			throw new RuntimeException("Either the 'subject' or 'object' attribute of the <triple/> must be a variable reference.");
		}
	}

	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		ArrayList<Map<String, RDFNode>> varsListCopy = new ArrayList<Map<String, RDFNode>>(varsList);
		for(int i = 0; i < varsListCopy.size(); i++) {
			Map<String, RDFNode> vars = varsListCopy.get(i);
			varsList.remove(i);
			List<Map<String, RDFNode>> newVarsList = applyToVars(dataSource, vars);
			if(newVarsList != null) {
				varsList.addAll(i, newVarsList);
			}
		}
	}

	protected List<Map<String, RDFNode>> applyToVars(RDFDataSource dataSource, Map<String, RDFNode> vars) {
		if(subjectAttr.isVariable() && vars.containsKey(subjectAttr.getVarName())) {
			RDFNode varValue = vars.get(subjectAttr.getVarName());
			if(varValue instanceof RDFResource) {
				if(objectAttr.isVariable()) {
					List<Map<String, RDFNode>> newVarsList = new ArrayList<Map<String, RDFNode>>();
					Iterator<RDFNode> targets = dataSource.getTargets((RDFResource)varValue, predicateAttr.getRDFResource(), true);
					while(targets.hasNext()) {
						Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
						newVars.put(objectAttr.getVarName(), targets.next());
						newVarsList.add(newVars);
					}
					return newVarsList;
				}
				else if((objectAttr.isURI() && dataSource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFResource(), true))
						|| dataSource.hasAssertion((RDFResource)varValue, predicateAttr.getRDFResource(), objectAttr.getRDFLiteral(), true)) {
					List<Map<String, RDFNode>> newVarsList = new ArrayList<Map<String, RDFNode>>();
					newVarsList.add(vars);
					return newVarsList;
				}
			}
		}
		else if(objectAttr.isVariable() && vars.containsKey(objectAttr.getVarName())) {
			RDFNode varValue = vars.get(objectAttr.getVarName());
			if(subjectAttr.isVariable()) {
				List<Map<String, RDFNode>> newVarsList = new ArrayList<Map<String, RDFNode>>();
				Iterator<RDFResource> sources = dataSource.getSources(predicateAttr.getRDFResource(), varValue, true);
				while(sources.hasNext()) {
					Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
					newVars.put(subjectAttr.getVarName(), sources.next());
					newVarsList.add(newVars);
				}
				return newVarsList;
			} else if(dataSource.hasAssertion(subjectAttr.getRDFResource(), predicateAttr.getRDFResource(), varValue, true)) {
				List<Map<String, RDFNode>> newVarsList = new ArrayList<Map<String, RDFNode>>();
				newVarsList.add(vars);
				return newVarsList;
			}
		}
		return null;
	}

}
