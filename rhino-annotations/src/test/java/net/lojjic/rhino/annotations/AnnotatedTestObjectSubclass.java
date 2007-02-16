package net.lojjic.rhino.annotations;

/**
 *
 */
@JSClassName("MyJSSubclass")
public class AnnotatedTestObjectSubclass extends AnnotatedTestObject {

	private String subProperty;

	@JSSetter("subprop")
	void setSubProperty(String subProperty) {
		System.out.println("Java: Calling setSubProperty setter method with value: " + subProperty);
		this.subProperty = subProperty;
	}

	@JSGetter("subprop")
	String getSubProperty() {
		System.out.println("Java: Calling getSubProperty getter method, returning value: " + subProperty);
		return subProperty;
	}

	@JSConstructor
	void subConstructorMethod() {
		System.out.println("Java: Calling subConstructorMethod");
	}

	@JSFunction("subFunctionNoReturnValue")
	void subMethodNoReturnValue(String arg) {
		System.out.println("Java: Called subMethodNoReturnValue with arg: " + arg);
	}

	@JSFunction("subFunctionWithReturnValue")
	String subMethodWithReturnValue(String arg) {
		System.out.println("Java: Called subFunctionWithReturnValue with arg: " + arg);
		return "this is the return value from the subclass";
	}


}
