

function testGetElementsByTagName() {
	print("Testing getElementsByTagName()...")
	var kids = document.getElementsByTagName("child");
	assertEquals(kids.length, 3);
}

function testChildNodes() {
	print("Testing childNodes...")
	var kids = document.getElementsByTagName("child");
	var subkids = kids[0].childNodes;
	assertEquals(subkids.length, 7);
}

function testAttributes() {
	print("Testing attributes...")
	var element = document.getElementsByTagName("subchild")[0];
	assertEquals(element.getAttribute("name"), "First Subchild Name");
}

function testTextNode() {
	print("Testing text nodes...")
	var element = document.getElementsByTagName("subchild")[0];
	assertEquals(element.firstChild.nodeValue, "First Subchild Content");
}

function testUnsealed() {
	print("Testing objects are unsealed...")
	var element = document.getElementsByTagName("subchild")[1];
	element.mycustomprop = "doodah";
	assertEquals(element.mycustomprop, "doodah");
}

function testSingleWrapperInstance() {
	print("Testing single wrapper instance per node...")
	var element = document.getElementsByTagName("subchild")[1];
	var element2 = element.firstChild.parentNode;
	assertTrue(element === element2);
	element.mycustomprop = "doodah";
	assertEquals(element2.mycustomprop, "doodah");
}