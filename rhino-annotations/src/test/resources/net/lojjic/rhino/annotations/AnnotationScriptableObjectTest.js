
function testBasic() {
	var obj = new MyJSClass();

	var propValue = "my test property value";
	obj.prop = propValue;
	assertEquals(obj.prop, propValue);

	obj.functionNoReturnValue("my argument 1");

	var ret = obj.functionWithReturnValue("my argument 2");
	assertEquals(ret, "my argument 2");

	var staticPropValue = "my test static property value";
	MyJSClass.staticProp = staticPropValue;
	assertEquals(MyJSClass.staticProp, staticPropValue);

	var ret2 = MyJSClass.staticFunction("my static argument");
	assertEquals(ret2, "my static argument");
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