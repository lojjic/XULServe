
function print(val) {
	java.lang.System.out.println(String(val));
}

function assertNotNull(obj, failMessage) {
	if(obj == null) throw failMessage || "Got unexpected null.";
}

function assertNull(obj, failMessage) {
	if(obj != null) throw failMessage || "Expected null, got " + obj;
}

function assertEquals(obj1, obj2, failMessage) {
	if(obj1 != obj2) throw failMessage || "Expected " + obj2 + ", got " + obj1;
}

function listProps(obj) {
	print("=== Properties for object " + obj + ": ===")
	for(var i in obj) {
		print("   " + i + " = " + obj[i]);
	}
}

function testBasic() {
	var obj = new MyJSClass();

	var propValue = "my test property value";
	obj.prop = propValue;
	assertEquals(obj.prop, propValue);

	obj.functionNoReturnValue("my argument 1");

	var ret = obj.functionWithReturnValue("my argument 2");
	assertEquals(ret, "my argument 2");
}

function testInheritanceMapping() {
	assertNotNull(MyJSSubclass.prototype.subFunctionNoReturnValue);
	assertNotNull(this.MyJSClass);
	assertNotNull(MyJSClass.prototype.functionNoReturnValue);

	var obj = new MyJSSubclass();

	var subPropValue = "my test sub property value";
	obj.subprop = subPropValue;
	assertEquals(obj.subprop, subPropValue);

	obj.functionNoReturnValue("my sub argument 1");

	var ret = obj.functionWithReturnValue("my sub argument 2");
	assertEquals(ret, "my sub argument 2");
}

function testInheritanceNoMapping() {
	assertNotNull(MyJSSubclass.prototype.subFunctionNoReturnValue);
	assertNotNull(MyJSSubclass.prototype.functionNoReturnValue);
	assertNull(this.MyJSClass);

	var obj = new MyJSSubclass();

	var subPropValue = "my test sub property value";
	obj.subprop = subPropValue;
	assertEquals(obj.subprop, subPropValue);

	obj.functionNoReturnValue("my sub argument 1");

	var ret = obj.functionWithReturnValue("my sub argument 2");
	assertEquals(ret, "my sub argument 2");
}