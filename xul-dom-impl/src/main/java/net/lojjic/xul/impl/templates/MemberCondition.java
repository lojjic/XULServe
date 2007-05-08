package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFContainerImpl;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;

import java.util.*;

/**
 * The &lt;member/&gt; template rule condition
 */
public class MemberCondition extends Condition {

	private ConditionAttr containerAttr;
	private ConditionAttr childAttr;

	public MemberCondition(RDFService rdfService, String container, String child) {
		super(rdfService);
		containerAttr = new ConditionAttr(container);
		childAttr = new ConditionAttr(child);
		if(!containerAttr.isVariable() && !childAttr.isVariable()) {
			throw new RuntimeException("Either the 'condition' or 'child' attribute of the <member /> must be a variable reference.");
		}
	}

	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		RDFContainerUtils containerUtils = new RDFContainerUtilsImpl(rdfService);
		for(Map<String, RDFNode> vars : new ArrayList<Map<String, RDFNode>>(varsList)) {
			if(containerAttr.isVariable() && vars.containsKey(containerAttr.getVarName())) {
				RDFNode varValue = vars.get(containerAttr.getVarName());
				if(varValue instanceof RDFResource && containerUtils.isContainer(dataSource, (RDFResource)varValue)) {
					RDFContainerImpl containerImpl = new RDFContainerImpl(rdfService, dataSource, (RDFResource)varValue);
					if(childAttr.isVariable()) {
						Iterator<RDFNode> children = containerImpl.getElements();
						while(children.hasNext()) {
							Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
							newVars.put(childAttr.getVarName(), children.next());
							varsList.add(newVars);
						}
						varsList.remove(vars);
					}
					else if(!((childAttr.isURI() && containerImpl.indexOf(childAttr.getRDFResource()) != -1)
							|| containerImpl.indexOf(childAttr.getRDFLiteral()) != -1)) {
						varsList.remove(vars);
					}
				} else {
					varsList.remove(vars);
				}
			}
			else if(childAttr.isVariable() && vars.containsKey(childAttr.getVarName())) {
				RDFNode varValue = vars.get(childAttr.getVarName());
				if(containerAttr.isVariable()) {
					// Examine each incoming arc for ordinals
					Set<RDFResource> containers = new HashSet<RDFResource>();
					Iterator<RDFResource> arcsIn = dataSource.arcLabelsIn(varValue);
					while(arcsIn.hasNext()) {
						RDFResource arc = arcsIn.next();
						if(containerUtils.isOrdinalProperty(arc)) {
							Iterator<RDFResource> sources = dataSource.getSources(arc, varValue, true);
							while(sources.hasNext()) {
								RDFResource source = sources.next();
								if(containerUtils.isContainer(dataSource, source)) {
									containers.add(source);
								}
							}
						}
					}
					for(RDFResource container : containers) {
						Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
						newVars.put(containerAttr.getVarName(), container);
						varsList.add(newVars);
					}
					varsList.remove(vars);
				} else {
					RDFResource container = containerAttr.getRDFResource();
					if(!containerUtils.isContainer(dataSource, container) || containerUtils.indexOf(dataSource, container, varValue) == -1) {
						varsList.remove(vars);
					}
				}
			}
			else {
				varsList.remove(vars);
			}
		}
	}

}
