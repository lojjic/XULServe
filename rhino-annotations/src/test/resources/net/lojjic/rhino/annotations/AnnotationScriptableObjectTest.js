
function print(val) {
	java.lang.System.out.println(val);
}

function doTest() {

	var obj = new MyJSClass();

	obj.prop = "my test property value";
	print("JS prop value: " + obj.prop);

	obj.functionNoReturnValue("my argument 1");

	var ret = obj.functionWithReturnValue("my argument 2");
	print("JS function return value: " + ret);

}

doTest();