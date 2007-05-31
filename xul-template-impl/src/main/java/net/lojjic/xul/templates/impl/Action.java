package net.lojjic.xul.templates.impl;

import net.lojjic.xul.rdf.RDFNode;
import org.w3c.dom.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A XUL template rule &lt;action/&gt;
 */
public class Action implements Constants {

	private Element element;
	private List<NodeTemplate> nodeTemplates;

	public Action(Element element) {
		this.element = element;
		addChangeListener();
		compile();
	}

	/**
	 * Hook up a listener to recompile the action if the DOM subtree of the
	 * &lt;action/&gt; element is modified.
	 */
	private void addChangeListener() {
		EventListener listener = new EventListener() {
			public void handleEvent(Event evt) {
				compile();
			}
		};
		((EventTarget)element).addEventListener("DOMSubtreeModified", listener, false);
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
							if(XUL_NAMESPACE.equals(child.getNamespaceURI()) && "textnode".equals(child.getLocalName())) {
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
			contextNode.appendChild(contextNode.getOwnerDocument().createTextNode(expression.evaluate(vars)));
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
			((Element)contextNode).setAttributeNS(node.getNamespaceURI(), node.getLocalName(), expression.evaluate(vars));
		}
	}


}
