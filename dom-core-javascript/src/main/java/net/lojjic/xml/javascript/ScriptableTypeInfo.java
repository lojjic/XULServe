package net.lojjic.xml.javascript;

import org.w3c.dom.TypeInfo;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;

/**
 * Scriptable wrapper for {@link org.w3c.dom.TypeInfo}
 */
@JSClassName("TypeInfo")
public class ScriptableTypeInfo extends ScriptableDOMObject implements TypeInfo {

	private TypeInfo delegateTypeInfo;

	public ScriptableTypeInfo(Scriptable scope, TypeInfo delegateTypeInfo) {
		super(scope, delegateTypeInfo);
		this.delegateTypeInfo = delegateTypeInfo;
	}

	@JSGetter("typeName")
	public String getTypeName() {
		return delegateTypeInfo.getTypeName();
	}

	@JSGetter("typeNamespace")
	public String getTypeNamespace() {
		return delegateTypeInfo.getTypeNamespace();
	}

	@JSFunction("isDerivedFrom")
	public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
		return delegateTypeInfo.isDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
	}
}
