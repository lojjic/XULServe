package net.lojjic.rhino.annotations;

/**
 * Test AnnotatedScriptableObject implementation for use in test cases
 */
@JSClassName("MyJSClass")
public class AnnotatedTestObject extends AnnotationScriptableObject {

	private String property;

	@JSSetter("prop")
	void setProperty(String property) {
		System.out.println("Java: Calling setProperty setter method with value: " + property);
		this.property = property;
	}

	@JSGetter("prop")
	String getProperty() {
		System.out.println("Java: Calling getProperty getter method, returning value: " + property);
		return property;
	}

	@JSConstructor
	void constructorMethod() {
		System.out.println("Java: Calling constructorMethod");
	}

	@JSFunction("functionNoReturnValue")
	void methodNoReturnValue(String arg) {
		System.out.println("Java: Called functionNoReturnValue with arg: " + arg);
	}

	@JSFunction("functionWithReturnValue")
	String methodWithReturnValue(String arg) {
		System.out.println("Java: Called functionWithReturnValue with arg: " + arg);
		return arg;
	}

}
