package net.lojjic.xul.impl.templates;

import net.lojjic.xul.XULConstants;
import net.lojjic.xul.rdf.RDFLiteral;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A XUL template rule &lt;action/&gt;
 */
public class Action {

	private Element element;
	private List<NodeTemplate> nodeTemplates;

	public Action(Element element) {
		this.element = element;
		compile();
	}

	private void compile() {
		
	}

	public DocumentFragment generateResult(List<Map<String, RDFNode>> varsList) {
		return null; //TODO
	}





	private static abstract class NodeTemplate {
		protected Node node;
		
		protected NodeTemplate(Node node) {
			this.node = node;
		}

		public abstract void expand(Node contextNode, Map<String, RDFNode> vars);
	}

	private static class ElementTemplate extends NodeTemplate {
		private List<AttrTemplate> attributes;
		private List<NodeTemplate> children;

		protected ElementTemplate(Element element) {
			super(element);

			// Parse attributes:
			NamedNodeMap attrs = element.getAttributes();
			int length = attrs.getLength();
			if(length > 0) {
				attributes = new ArrayList<AttrTemplate>(length);
				for(int i = 0; i < length; i++) {
					attributes.add(new AttrTemplate(attrs.item(i)));
				}
			}
			
			// Parse children:
			NodeList children = node.getChildNodes();
			length = children.getLength();
			if(length > 0) {
				this.children = new ArrayList<NodeTemplate>(length);
				for(int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					switch(child.getNodeType()) {
						case Node.ELEMENT_NODE:
							if(XULConstants.XUL_NAMESPACE.equals(child.getNamespaceURI()) && "textnode".equals(child.getLocalName())) {
								this.children.add(new TextnodeElementTemplate((Element)child));
							} else {
								this.children.add(new ElementTemplate((Element)child));
							}
							break;
						case Node.TEXT_NODE:
							this.children.add(new TextTemplate((Text)child));
							break;
						default:
							//ignore other node types e.g. comments
					}
				}
			}
		}

		public void expand(Node contextNode, Map<String, RDFNode> vars) {
			Element element = contextNode.getOwnerDocument().createElementNS(node.getNamespaceURI(), node.getLocalName());
			for(AttrTemplate attr : attributes) {
				attr.expand(element, vars);
			}
			for(NodeTemplate node : children) {
				node.expand(element, vars);
			}
			contextNode.appendChild(element);
		}
	}

	private static class TextnodeElementTemplate extends NodeTemplate {
		private Expression expression;

		public TextnodeElementTemplate(Element element) {
			super(element);
			String value = element.getAttribute("value");
			if(value == null) {
				throw new RuntimeException("<textnode/> element must have a 'value' attribute.");
			}
			expression = new Expression(value);
		}

		public void expand(Node contextNode, Map<String, RDFNode> vars) {
			contextNode.appendChild(contextNode.getOwnerDocument().createTextNode(expression.expand(vars)));
		}
	}

	private static class TextTemplate extends NodeTemplate {
		public TextTemplate(Text text) {
			super(text);
		}

		public void expand(Node contextNode, Map<String, RDFNode> vars) {
			contextNode.appendChild(contextNode.getOwnerDocument().createTextNode(node.getNodeValue()));
		}
	}

	private static class AttrTemplate extends NodeTemplate {
		private Expression expression;

		protected AttrTemplate(Node node) {
			super(node);
			expression = new Expression(node.getNodeValue());
		}

		public void expand(Node contextNode, Map<String, RDFNode> vars) {
			((Element)contextNode).setAttributeNS(node.getNamespaceURI(), node.getLocalName(), expression.expand(vars));
		}
	}

	public static class Expression {
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
							if(chars[i + 1] == '^') { //escaped
								stringBuilder.append(chars[i]);
								i++; //skip duplicate
							} else {
								parts.add(new Variable(stringBuilder.toString()));
								isInVar = false;
								stringBuilder.setLength(0);
							}
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



}
