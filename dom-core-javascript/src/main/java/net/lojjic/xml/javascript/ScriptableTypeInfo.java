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
public class ScriptableTypeInfo<T extends TypeInfo> extends ScriptableDOMObject<T> {

	public ScriptableTypeInfo() {
		super();
	}

	public ScriptableTypeInfo(Scriptable scope, T typeInfo) {
		super(scope, typeInfo);
	}

	@JSStatic @JSGetter("DERIVATION_RESTRICTION")
	public static int get_DERIVATION_RESTRICTION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_RESTRICTION;
	}

	@JSStatic @JSGetter("DERIVATION_EXTENSION")
	public static int get_DERIVATION_EXTENSION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_EXTENSION;
	}

	@JSStatic @JSGetter("DERIVATION_UNION")
	public static int get_DERIVATION_UNION(ScriptableObject obj) {
		return TypeInfo.DERIVATION_UNION;
	}

	@JSStatic @JSGetter("DERIVATION_LIST")
	public static int get_DERIVATION_LIST(ScriptableObject obj) {
		return TypeInfo.DERIVATION_LIST;
	}

	@JSGetter("typeName")
	public String getTypeName() {
		return unwrap().getTypeName();
	}

	@JSGetter("typeNamespace")
	public String getTypeNamespace() {
		return unwrap().getTypeNamespace();
	}

	@JSFunction("isDerivedFrom")
	public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
		return unwrap().isDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
	}
}
