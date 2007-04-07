package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CharacterData;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.CharacterData}
 */
@JSClassName("CharacterData")
public class ScriptableCharacterData<T extends CharacterData> extends ScriptableNode<T> {

	public ScriptableCharacterData() {
		super();
	}

	public ScriptableCharacterData(Scriptable scope, T cdata) {
		super(scope, cdata);
	}


	@JSGetter("data")
	public String getData() {
		return unwrap().getData();
	}
	
	@JSSetter("data")
	public void setData(String data) {
		unwrap().setData(data);
	}
	
	@JSGetter("length")
	public int getLength() {
		return unwrap().getLength();
	}

	@JSFunction("substringData")
	public String substringData(int offset, int count) {
		return unwrap().substringData(offset, count);
	}
	
	@JSFunction("appendData")
	public void appendData(String data) {
		unwrap().appendData(data);
	}
	
	@JSFunction("insertData")
	public void insertData(int offset, String data) {
		unwrap().insertData(offset, data);
	}
	
	@JSFunction("deleteData")
	public void deleteData(int offset, int count) {
		unwrap().deleteData(offset, count);
	}
	
	@JSFunction("replaceData")
	public void replaceData(int offset, int count, String data) {
		unwrap().replaceData(offset, count, data);
	}

}
