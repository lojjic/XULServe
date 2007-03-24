
function testDOM() {

	var kids = document.getElementsByTagName("child");
	assertEquals(kids.length, 3);

	var subkids = kids[0].childNodes;
	assertEquals(subkids.length, 7);

	var subkid = kids[0].getElementsByTagName("subchild")[0];
	assertEquals(subkid.getAttribute("name"), "First Subchild Name");
	assertEquals(subkid.firstChild.nodeValue, "First Subchild Content");

	// Unsealed properties:
	subkid.mycustomprop = "doodah";
	assertEquals(subkid.mycustomprop, "doodah");

	// Test same wrapper instance is used for a node:
	var subkidAgain = subkid.firstChild.parentNode;
	assertEquals(subkidAgain.mycustomprop, "doodah");

}