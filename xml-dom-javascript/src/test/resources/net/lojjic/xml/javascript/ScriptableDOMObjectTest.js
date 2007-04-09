

function testGetElementsByTagName() {
	var start = new Date();
	print("Testing getElementsByTagName()...")
	var kids = document.getElementsByTagName("child");
	assertEquals(kids.length, 3);
	print("     ...finished in " + (new Date() - start) + "ms")
}

function testChildNodes() {
	var start = new Date();
	print("Testing childNodes...")
	var kids = document.getElementsByTagName("child");
	var subkids = kids[0].childNodes;
	assertEquals(subkids.length, 7);
	print("     ...finished in " + (new Date() - start) + "ms")
}

function testAttributes() {
	var start = new Date();
	print("Testing attributes...")
	var element = document.getElementsByTagName("subchild")[0];
	assertEquals(element.getAttribute("name"), "First Subchild Name");
	print("     ...finished in " + (new Date() - start) + "ms")
}

function testTextNode() {
	var start = new Date();
	print("Testing text nodes...")
	var element = document.getElementsByTagName("subchild")[0];
	assertEquals(element.firstChild.nodeValue, "First Subchild Content");
	print("     ...finished in " + (new Date() - start) + "ms")
}

function testUnsealed() {
	var start = new Date();
	print("Testing objects are unsealed...")
	var element = document.getElementsByTagName("subchild")[1];
	element.mycustomprop = "doodah";
	assertEquals(element.mycustomprop, "doodah");
	print("     ...finished in " + (new Date() - start) + "ms")
}

function testSingleWrapperInstance() {
	var start = new Date();
	print("Testing single wrapper instance per node...")
	var element = document.getElementsByTagName("subchild")[1];
	var element2 = element.firstChild.parentNode;
	assertTrue(element === element2);
	element.mycustomprop = "doodah";
	assertEquals(element2.mycustomprop, "doodah");
	print("     ...finished in " + (new Date() - start) + "ms")
}