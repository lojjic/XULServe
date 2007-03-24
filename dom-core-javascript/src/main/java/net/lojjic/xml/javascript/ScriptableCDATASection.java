package net.lojjic.xml.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CDATASection;

/**
 * Scriptable wrapper for {@link org.w3c.dom.CDATASection}
 */
@JSClassName("CDATASection")
public class ScriptableCDATASection extends ScriptableText {

	private CDATASection delegateCDATASection;

	public ScriptableCDATASection(Scriptable scope, CDATASection delegateCDATASection) {
		super(scope, delegateCDATASection);
		this.delegateCDATASection = delegateCDATASection;
	}

}
