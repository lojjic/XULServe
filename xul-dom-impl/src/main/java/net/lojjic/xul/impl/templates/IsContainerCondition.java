package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Condition for the iscontainer rule attribute
 */
public class IsContainerCondition extends Condition {

	private RDFContainerUtils containerUtils;
	private String resourceVarName;
	private boolean requireContainer;

	/**
	 * Constructor.
	 * @param rdfService
	 * @param resourceVarName - name of the variable holding the subject resource
	 * @param requireContainer - true if the subject resource must be a container, false if it must not be a container.
	 */
	public IsContainerCondition(RDFService rdfService, String resourceVarName, boolean requireContainer) {
		super(rdfService);
		this.resourceVarName = resourceVarName;
		this.requireContainer = requireContainer;
		this.containerUtils = new RDFContainerUtilsImpl(rdfService);
	}

	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		for(Map<String, RDFNode> vars : new ArrayList<Map<String, RDFNode>>(varsList)) {
			RDFNode resource = vars.get(resourceVarName);
			boolean isContainer = (resource != null && resource instanceof RDFResource &&
					containerUtils.isContainer(dataSource, (RDFResource)resource));
			if(requireContainer != isContainer) {
				varsList.remove(vars);
			}
		}
	}

}
