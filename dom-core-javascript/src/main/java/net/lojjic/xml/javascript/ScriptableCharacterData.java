package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CharacterData;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSFunction;

@JSClassName("CharacterData")
public class ScriptableCharacterData extends ScriptableNode implements CharacterData {

	CharacterData delegateCharacterData;
	
	public ScriptableCharacterData(Scriptable scope, CharacterData cdata) {
		super(scope, cdata);
		this.delegateCharacterData = cdata;
	}


	@JSGetter("data")
	public String getData() {
		return delegateCharacterData.getData();
	}
	
	@JSSetter("data")
	public void setData(String data) {
		delegateCharacterData.setData(data);
	}
	
	@JSGetter("length")
	public int getLength() {
		return delegateCharacterData.getLength();
	}

	@JSFunction("substringData")
	public String substringData(int offset, int count) {
		return delegateCharacterData.substringData(offset, count);
	}
	
	@JSFunction("appendData")
	public void appendData(String data) {
		delegateCharacterData.appendData(data);
	}
	
	@JSFunction("insertData")
	public void insertData(int offset, String data) {
		delegateCharacterData.insertData(offset, data);
	}
	
	@JSFunction("deleteData")
	public void deleteData(int offset, int count) {
		delegateCharacterData.deleteData(offset, count);
	}
	
	@JSFunction("replaceData")
	public void replaceData(int offset, int count, String data) {
		delegateCharacterData.replaceData(offset, count, data);
	}

}
