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
public class ScriptableNameList<T extends NameList> extends ScriptableDOMObject<T> {

	public ScriptableNameList() {
		super();
	}

	public ScriptableNameList(Scriptable scope, T nameList) {
		super(scope, nameList);
	}

	@JSFunction("contains")
	public boolean contains(String str) {
		return unwrap().contains(str);
	}

	@JSFunction("containsNS")
	public boolean containsNS(String namespaceURI, String name) {
		return unwrap().containsNS(namespaceURI, name);
	}

	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSFunction("getName")
	public String getName(int index) {
		return unwrap().getName(index);
	}

	@JSFunction("getNamespaceURI")
	public String getNamespaceURI(int index) {
		return unwrap().getNamespaceURI(index);
	}
}
