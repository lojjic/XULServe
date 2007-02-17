package net.lojjic.rhino.annotations;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import junit.framework.TestCase;

/**
 * Test cases for {@link net.lojjic.rhino.annotations.AnnotationScriptableObject}
 */
public class AnnotationScriptableObjectTest extends TestCase {

	private static final String baseJSFile = "/net/lojjic/rhino/annotations/UnitTest.js";
	private static final String testsJSFile = "/net/lojjic/rhino/annotations/AnnotationScriptableObjectTest.js";

	public void testBasic() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			AnnotationScriptableObject.defineClass(scope, AnnotatedTestObject.class, false, false);
			JSUtils.loadJSFile(cx, scope, baseJSFile);
			JSUtils.loadJSFile(cx, scope, testsJSFile);
			cx.evaluateString(scope, "testBasic()", "<test>", 1, null);
		}
		finally {
			Context.exit();
		}
	}

	public void testInheritanceMapping() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			AnnotationScriptableObject.defineClass(scope, AnnotatedTestObjectSubclass.class, false, true);
			JSUtils.loadJSFile(cx, scope, baseJSFile);
			JSUtils.loadJSFile(cx, scope, testsJSFile);
			cx.evaluateString(scope, "testInheritanceMapping()", "<test>", 1, null);
		}
		finally {
			Context.exit();
		}
	}

	public void testInheritanceNoMapping() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			AnnotationScriptableObject.defineClass(scope, AnnotatedTestObjectSubclass.class, false, false);
			JSUtils.loadJSFile(cx, scope, baseJSFile);
			JSUtils.loadJSFile(cx, scope, testsJSFile);
			cx.evaluateString(scope, "testInheritanceNoMapping()", "<test>", 1, null);
		}
		finally {
			Context.exit();
		}
	}

}
