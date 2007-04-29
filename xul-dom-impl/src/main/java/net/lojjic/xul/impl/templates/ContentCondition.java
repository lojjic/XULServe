package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFResource;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

/**
 * The &lt;content/&gt; template rule condition
 */
public class ContentCondition extends Condition {

	private ConditionAttr uriAttr;

	public ContentCondition(RDFService rdfService, Element element) {
		super(rdfService, element);
		parseAttributes();
	}

	private void parseAttributes() {
		String uri = element.getAttribute("uri");
		if(uri == null) {
			throw new RuntimeException("Missing 'uri' attribute on <content/> condition.");
		}

		uriAttr = new ConditionAttr(uri);

		if(!uriAttr.isVariable()) {
			throw new RuntimeException("The 'uri' attribute of the <content /> condition must be a variable reference.");
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
