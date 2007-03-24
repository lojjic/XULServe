package net.lojjic.xml.javascript;

import junit.framework.TestCase;
import net.lojjic.rhino.annotations.AnnotationScriptableObject;
import net.lojjic.rhino.annotations.JSUtils;
import net.lojjic.xml.javascript.bootstrap.ScriptableDOMImplementationRegistry;
import net.lojjic.xml.javascript.events.*;
import net.lojjic.xml.javascript.views.ScriptableAbstractView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;

/**
 * Test cases for the ScriptableDOMObject wrappers
 */
public class ScriptableDOMObjectTest extends TestCase {

	public void testGetElementsByTagName() throws Exception {
		callJSFunction("testGetElementsByTagName");
	}

	public void testChildNodes() throws Exception {
		callJSFunction("testChildNodes");
	}

	public void testAttributes() throws Exception {
		callJSFunction("testAttributes");
	}

	public void testTextNode() throws Exception {
		callJSFunction("testTextNode");
	}

	public void testUnsealed() throws Exception {
		callJSFunction("testUnsealed");
	}

	public void testSingleWrapperInstance() throws Exception {
		callJSFunction("testSingleWrapperInstance");
	}



	private void callJSFunction(String functionName) throws Exception {
		Context ctxt = Context.enter();
		try {
			// Set up the WrapFactory:
			DOMWrapFactory wrapFactory = new DOMWrapFactory();
			wrapFactory.addDefaultWrapMappings();
			ctxt.setWrapFactory(wrapFactory);

			// Define DOM JS classes:
			ScriptableObject scope = ctxt.initStandardObjects();
			defineDOMClasses(scope);

			// Load the DOM document:
			InputStream xmlIS = getClass().getResourceAsStream("/net/lojjic/xml/javascript/ScriptableDOMObjectTest.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlIS);
			scope.put("document", scope, Context.javaToJS(document, scope));

			// Load and execute the JavaScript test file:
			JSUtils.loadJSFile(ctxt, scope, "/net/lojjic/rhino/annotations/UnitTest.js");
			JSUtils.loadJSFile(ctxt, scope, "/net/lojjic/xml/javascript/ScriptableDOMObjectTest.js");
			ctxt.evaluateString(scope, functionName + "()", "<test>", 1, null);
		}
		finally {
			Context.exit();
		}
	}


	private void defineDOMClasses(ScriptableObject scope) throws IllegalAccessException,
			InvocationTargetException, InstantiationException {
		AnnotationScriptableObject.defineClass(scope, ScriptableAttr.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableCDATASection.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableCharacterData.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableComment.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDocument.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDocumentFragment.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDocumentType.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMConfiguration.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMError.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMErrorHandler.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMException.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMImplementation.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMImplementationList.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMImplementationSource.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMLocator.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMStringList.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableElement.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableEntity.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableEntityReference.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableNamedNodeMap.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableNameList.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableNode.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableNodeList.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableNotation.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableProcessingInstruction.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableText.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableTypeInfo.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableUserDataHandler.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableDOMImplementationRegistry.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableEvent.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableEventListener.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableMouseEvent.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableMutationEvent.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableUIEvent.class);
		AnnotationScriptableObject.defineClass(scope, ScriptableAbstractView.class);
	}

}
