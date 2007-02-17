
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
