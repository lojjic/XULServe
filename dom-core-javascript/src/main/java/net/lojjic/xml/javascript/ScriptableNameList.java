package net.lojjic.xml.javascript;

import org.w3c.dom.NameList;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;

/**
 * Scriptable wrapper for {@link org.w3c.dom.NameList}
 */
@JSClassName("NameList")
public class ScriptableNameList extends ScriptableDOMObject {

	private NameList delegateNameList;

	public ScriptableNameList(Scriptable scope, NameList delegateNameList) {
		super(scope, delegateNameList);
		this.delegateNameList = delegateNameList;
	}

	@JSFunction("contains")
	public boolean contains(String str) {
		return delegateNameList.contains(str);
	}

	@JSFunction("containsNS")
	public boolean containsNS(String namespaceURI, String name) {
		return delegateNameList.containsNS(namespaceURI, name);
	}

	@JSGetter("length")
	public int getLength() {
		return delegateNameList.getLength();
	}

	@JSFunction("getName")
	public String getName(int index) {
		return delegateNameList.getName(index);
	}

	@JSFunction("getNamespaceURI")
	public String getNamespaceURI(int index) {
		return delegateNameList.getNamespaceURI(index);
	}
}
