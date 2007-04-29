package net.lojjic.xul.impl.templates;

import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import net.lojjic.xul.rdf.RDFNode;

import java.util.Map;
import java.util.List;

/**
 * A XUL template rule &lt;action/&gt;
 */
public class Action {

	private Element element;

	public Action(Element element) {
		this.element = element;
		compile();
	}

	private void compile() {
		// TODO
	}

	public DocumentFragment generateResult(List<Map<String, RDFNode>> varsList) {
		return null; //TODO
	}
}
