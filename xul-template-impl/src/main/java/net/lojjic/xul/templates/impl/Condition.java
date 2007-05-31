package net.lojjic.xul.templates.impl;

import net.lojjic.xul.rdf.*;

import java.util.List;
import java.util.Map;

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
