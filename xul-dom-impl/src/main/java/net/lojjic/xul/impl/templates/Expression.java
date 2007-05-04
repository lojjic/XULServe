package net.lojjic.xul.impl.templates;

import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFLiteral;
import net.lojjic.xul.rdf.RDFResource;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * A template action attribute expression
 */
public class Expression {
	private List<ExpressionPart> parts = new ArrayList<ExpressionPart>();

	public Expression(String expression) {
		compile(expression);
	}

	private void compile(String expression) {
		char[] chars = expression.toCharArray();
		boolean isInVar = false;
		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < chars.length; i++) {
			switch(chars[i]) {
				case '?':
					if(isInVar) { //already in var
						stringBuilder.append(chars[i]);
					}
					else if(chars[i + 1] == '?') { //escaped
						stringBuilder.append(chars[i]);
						i++; //skip duplicate
					}
					else { //new var
						if(stringBuilder.length() > 0) {
							parts.add(new Characters(stringBuilder.toString()));
							stringBuilder.setLength(0);
						}
						stringBuilder.append(chars[i]);
						isInVar = true;
					}
					break;

				case '^':
					if(isInVar) { //if in var, caret serves to terminate var name
						parts.add(new Variable(stringBuilder.toString()));
						isInVar = false;
						stringBuilder.setLength(0);
					} else {
						stringBuilder.append(chars[i]);
					}
					break;

				case ' ':
					if(isInVar) { //terminates var name
						parts.add(new Variable(stringBuilder.toString()));
						isInVar = false;
						stringBuilder.setLength(0);
					}
					stringBuilder.append(chars[i]);
					break;

				default:
					stringBuilder.append(chars[i]);
			}
		}

		if(stringBuilder.length() > 0) {
			String leftover = stringBuilder.toString();
			parts.add(isInVar ? new Variable(leftover) : new Characters(leftover));
		}
	}

	public String expand(Map<String, RDFNode> vars) {
		StringBuilder stringBuilder = new StringBuilder();
		for(ExpressionPart part : parts) {
			stringBuilder.append(part.expand(vars));
		}
		return stringBuilder.toString();
	}

	private static interface ExpressionPart {
		String expand(Map<String,RDFNode> vars);
	}

	private static class Characters implements ExpressionPart {
		private String value;
		public Characters(String value) {
			this.value = value;
		}
		public String expand(Map<String,RDFNode> vars) {
			return value;
		}
	}

	private static class Variable implements ExpressionPart {
		private String name;
		public Variable(String name) {
			this.name = name;
		}
		public String expand(Map<String,RDFNode> vars) {
			RDFNode var = vars.get(name);
			if(var != null) {
				if(var instanceof RDFLiteral) {
					return ((RDFLiteral)var).getValue();
				} else {
					return ((RDFResource)var).getValue();
				}
			}
			return name;
		}
	}
}
