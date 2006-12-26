package net.lojjic.xml.javascript;

import org.mozilla.javascript.Scriptable;
import org.w3c.dom.CharacterData;

public class ScriptableCharacterData extends ScriptableNode {
	
	public static String JS_CLASS_NAME = "CharacterData";

	CharacterData delegateCharacterData;
	
	public ScriptableCharacterData(Scriptable scope, CharacterData cdata) {
		super(scope, cdata);
		this.delegateCharacterData = cdata;
	}	
	
	
	public String jsGet_data() {
		return delegateCharacterData.getData();
	}
	
	public void jsSet_data(String data) {
		delegateCharacterData.setData(data);
	}
	
	public int jsGet_length() {
		return delegateCharacterData.getLength();
	}
	
	public String jsFunction_substringData(int offset, int count) {
		return delegateCharacterData.substringData(offset, count);
	}
	
	public void jsFunction_appendData(String data) {
		delegateCharacterData.appendData(data);
	}
	
	public void jsFunction_insertData(int offset, String data) {
		delegateCharacterData.insertData(offset, data);
	}
	
	public void jsFunction_deleteData(int offset, int count) {
		delegateCharacterData.deleteData(offset, count);
	}
	
	public void jsFunction_replaceData(int offset, int count, String data) {
		delegateCharacterData.replaceData(offset, count, data);
	}

}
