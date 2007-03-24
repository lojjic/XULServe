package net.lojjic.rhino.annotations;

import org.mozilla.javascript.ScriptableObject;

/**
 * Test AnnotatedScriptableObject implementation for use in test cases
 */
@JSClassName("MyJSClass")
public class AnnotatedTestObject extends AnnotationScriptableObject {

	private String property;

	@JSConstructor
	public AnnotatedTestObject() {
		System.out.println("Java: Calling constructorMethod");
	}

	@JSSetter("prop")
	public void setProperty(String property) {
		System.out.println("Java: Calling setProperty setter method with value: " + property);
		this.property = property;
	}

	@JSGetter("prop")
	public String getProperty() {
		System.out.println("Java: Calling getProperty getter method, returning value: " + property);
		return property;
	}

	@JSFunction("functionNoReturnValue")
	public void methodNoReturnValue(String arg) {
		System.out.println("Java: Called methodNoReturnValue with arg: " + arg);
	}

	@JSFunction("functionWithReturnValue")
	public String methodWithReturnValue(String arg) {
		System.out.println("Java: Called methodWithReturnValue with arg: " + arg);
		return arg;
	}

	@JSStatic @JSFunction("staticFunction")
	public static String staticMethod(String arg) {
		System.out.println("Java: Called staticMethod with arg: " + arg);
		return arg;
	}

	@JSStatic @JSGetter("staticProp")
	public static String getStaticProperty(ScriptableObject obj) {
		System.out.println("Java: Calling getStaticProperty getter method");
		return (String)obj.get("__staticProp", obj);
	}

	@JSStatic @JSSetter("staticProp")
	public static void setStaticProperty(ScriptableObject obj, String val) {
		System.out.println("Java: Calling setProperty setter method with value: " + val);
		obj.put("__staticProp", obj, val);
	}

}
