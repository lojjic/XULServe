package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.ProcessingInstruction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.ProcessingInstruction}
 */
@JSClassName("ProcessingInstruction")
public class ScriptableProcessingInstruction extends ScriptableNode {

	private ProcessingInstruction delegateProcessingInstruction;

	public ScriptableProcessingInstruction(Scriptable scope, ProcessingInstruction delegateProcessingInstruction) {
		super(scope, delegateProcessingInstruction);
		this.delegateProcessingInstruction = delegateProcessingInstruction;
	}

	@JSGetter("data")
	public String getData() {
		return delegateProcessingInstruction.getData();
	}

	@JSGetter("target")
	public String getTarget() {
		return delegateProcessingInstruction.getTarget();
	}

	@JSSetter("data")
	public void setData(String data) {
		delegateProcessingInstruction.setData(data);
	}
}
