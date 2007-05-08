package net.lojjic.xul.impl.templates;

import org.w3c.dom.Element;
import net.lojjic.xul.rdf.*;

import java.util.Map;
import java.util.List;

/**
 * Abstract class for XUL template &lt;rule/&gt; conditions
 */
public abstract class Condition {
	protected RDFService rdfService;

	public Condition(RDFService rdfService) {
		this.rdfService = rdfService;
	}

	protected class ConditionAttr {
		private String value;

		public ConditionAttr(String value) {
			this.value = value;
		}

		public boolean isVariable() {
			return value.charAt(0) == '?';
		}

		public String getVarName() {
			return value;
		}

		public boolean isURI() {
			return value.indexOf(':') != -1;
		}

		public RDFResource getRDFResource() {
			return rdfService.getResource(value);
		}

		public RDFLiteral getRDFLiteral() {
			return rdfService.getLiteral(value);
		}
	}

	public abstract void applyToVariablesList(RDFDataSource dataSource, List<Map<String, RDFNode>> varsList, RDFResource start);

}
