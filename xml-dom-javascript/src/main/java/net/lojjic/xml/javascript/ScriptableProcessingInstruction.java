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
public class ScriptableProcessingInstruction<T extends ProcessingInstruction> extends ScriptableNode<T> {

	public ScriptableProcessingInstruction() {
		super();
	}

	public ScriptableProcessingInstruction(Scriptable scope, T processingInstruction) {
		super(scope, processingInstruction);
	}

	@JSGetter("data")
	public String getData() {
		return unwrap().getData();
	}

	@JSGetter("target")
	public String getTarget() {
		return unwrap().getTarget();
	}

	@JSSetter("data")
	public void setData(String data) {
		unwrap().setData(data);
	}
}
