package net.lojjic.xml.javascript;

import org.w3c.dom.TypeInfo;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSStatic;

/**
 * Scriptable wrapper for {@link org.w3c.dom.TypeInfo}
 */
@JSClassName("TypeInfo")
public class ScriptableTypeInfo extends ScriptableDOMObject {

	private TypeInfo delegateTypeInfo;

	public ScriptableTypeInfo(Scriptable scope, TypeInfo delegateTypeInfo) {
		super(scope, delegateTypeInfo);
		this.delegateTypeInfo = delegateTypeInfo;
	}

	@JSStatic @JSGetter("DERIVATION_RESTRICTION")
	public static short get_DERIVATION_RESTRICTION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_RESTRICTION;
	}

	@JSStatic @JSGetter("DERIVATION_EXTENSION")
	public static short get_DERIVATION_EXTENSION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_EXTENSION;
	}

	@JSStatic @JSGetter("DERIVATION_UNION")
	public static short get_DERIVATION_UNION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_UNION;
	}

	@JSStatic @JSGetter("DERIVATION_LIST")
	public static short get_DERIVATION_LIST(ScriptableObject obj) {
		return TypeInfo.DERIVATION_LIST;
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
