package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFContainerImpl;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;
import org.w3c.dom.Element;

import java.util.*;

/**
 * The &lt;member/&gt; template rule condition
 */
public class MemberCondition extends Condition {

	private ConditionAttr containerAttr;
	private ConditionAttr childAttr;

	public MemberCondition(RDFService rdfService, Element element) {
		super(rdfService, element);
		parseAttributes();
	}

	private void parseAttributes() {
		String container = element.getAttribute("container");
		String child = element.getAttribute("child");
		if(container == null || child == null) {
			throw new RuntimeException("Missing 'container' or 'child' attribute on <member/> condition.");
		}

		containerAttr = new ConditionAttr(container);
		childAttr = new ConditionAttr(child);

		if(!containerAttr.isVariable() && !childAttr.isVariable()) {
			throw new RuntimeException("Either the 'condition' or 'child' attribute of the <member /> must be a variable reference.");
		}
	}


	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		for(Map<String, RDFNode> vars : varsList) {
			if(containerAttr.isVariable()) {
				RDFNode varValue = vars.get(containerAttr.getVarName());
				if(varValue != null && varValue instanceof RDFResource) {
					RDFContainerImpl containerImpl = new RDFContainerImpl(rdfService, dataSource, (RDFResource)varValue);
					if(childAttr.isVariable()) {
						Iterator<RDFNode> children = containerImpl.getElements();
						while(children.hasNext()) {
							Map<String, RDFNode> newVars = new HashMap<String, RDFNode>(vars);
							newVars.put(childAttr.getVarName(), children.next());
							varsList.add(newVars);
						}
						varsList.remove(vars);
					} else {
						// try as uri, then literal
						if(containerImpl.indexOf(childAttr.getRDFResource()) == -1 || containerImpl.indexOf(childAttr.getRDFLiteral()) == -1) {
							varsList.remove(vars);
						}
					}
				} else {
					varsList.remove(vars);
				}
			}
			else if(childAttr.isVariable()) {
				RDFContainerUtils containerUtils = new RDFContainerUtilsImpl(rdfService);
				RDFNode varValue = vars.get(childAttr.getVarName());
				if(varValue != null) {
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
				} else {
					varsList.remove(vars);
				}
			}
		}
	}

}
