package net.lojjic.xul.templates.impl;

import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Template rule binding. Very similar to a triple condition except that it does not
 * remove variable sets that don't match the triple.
 */
public class Binding extends TripleCondition {

	public Binding(RDFService rdfService, String subject, String predicate, String object) {
		super(rdfService, subject, predicate, object);
	}

	@Override
	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		ArrayList<Map<String, RDFNode>> varsListCopy = new ArrayList<Map<String, RDFNode>>(varsList);
		for(int i = 0; i < varsListCopy.size(); i++) {
			Map<String, RDFNode> vars = varsListCopy.get(i);
			List<Map<String, RDFNode>> newVarsList = applyToVars(dataSource, vars);
			if(newVarsList != null) {
				varsList.remove(i);
				varsList.addAll(i, newVarsList);
			}
		}
	}

}
