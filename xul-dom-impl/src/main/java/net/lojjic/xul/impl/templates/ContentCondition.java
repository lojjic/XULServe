package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The &lt;content/&gt; template rule condition
 */
public class ContentCondition extends Condition {

	private ConditionAttr uriAttr;

	public ContentCondition(RDFService rdfService, String uri) {
		super(rdfService);
		uriAttr = new ConditionAttr(uri);
		if(!uriAttr.isVariable()) {
			throw new RuntimeException("The 'uri' attribute of the 'content' condition must be a variable reference.");
		}
	}

	public void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start) {
		if(!varsList.isEmpty()) {
			throw new IllegalStateException("Variables list already contains variable mappings.");
		}

		Map<String, RDFNode> map = new HashMap<String, RDFNode>();
		map.put(uriAttr.getVarName(), start);
		varsList.add(map);
	}

}
