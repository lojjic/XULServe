package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CDATASection;

/**
 * Scriptable wrapper for {@link org.w3c.dom.CDATASection}
 */
@JSClassName("CDATASection")
public class ScriptableCDATASection<T extends CDATASection> extends ScriptableText<T> {

	public ScriptableCDATASection() {
		super();
	}

	public ScriptableCDATASection(Scriptable scope, T delegateCDATASection) {
		super(scope, delegateCDATASection);
	}

}
