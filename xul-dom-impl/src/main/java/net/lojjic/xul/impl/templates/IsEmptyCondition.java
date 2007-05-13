package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.*;
import net.lojjic.xul.rdf.impl.RDFContainerUtilsImpl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Condition for the isempty rule attribute
 */
public class IsEmptyCondition extends Condition {

	private boolean requireEmpty;
	private String containerVarName;
	private RDFContainerUtils containerUtils;

	/**
	 * Constructor.
	 * @param rdfService
	 * @param containerVarName - name of the variable holding the container resource
	 * @param requireEmpty - true if the container must be empty, false if it must not be empty
	 */
	public IsEmptyCondition(RDFService rdfService, String containerVarName, boolean requireEmpty) {
		super(rdfService);
		this.containerVarName = containerVarName;
		this.containerUtils = new RDFContainerUtilsImpl(rdfService);
		this.requireEmpty = requireEmpty;
	}

	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		for(Map<String, RDFNode> vars : new ArrayList<Map<String, RDFNode>>(varsList)) {
			RDFNode container = vars.get(containerVarName);
			boolean isEmpty = (container != null && container instanceof RDFResource &&
					containerUtils.isEmpty(dataSource, (RDFResource)container));
			if(requireEmpty != isEmpty) {
				varsList.remove(vars);
			}
		}
	}

}
