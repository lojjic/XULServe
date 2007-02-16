package net.lojjic.rhino.annotations;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 * Test cases for {@link net.lojjic.rhino.annotations.AnnotationScriptableObject}
 */
public class AnnotationScriptableObjectTest extends TestCase {

	private static final String jsFile = "/net/lojjic/rhino/annotations/AnnotationScriptableObjectTest.js";

	public void testBasic() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();

			AnnotationScriptableObject.defineClass(scope, AnnotatedTestObject.class, false, false);

			InputStream inputStream = getClass().getResourceAsStream(jsFile);
			Reader reader = new InputStreamReader(inputStream);

			cx.evaluateReader(scope, reader, jsFile, 1, null);
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

			InputStream inputStream = getClass().getResourceAsStream(jsFile);
			Reader reader = new InputStreamReader(inputStream);

			cx.evaluateReader(scope, reader, jsFile, 1, null);
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

			InputStream inputStream = getClass().getResourceAsStream(jsFile);
			Reader reader = new InputStreamReader(inputStream);

			cx.evaluateReader(scope, reader, jsFile, 1, null);
			cx.evaluateString(scope, "testInheritanceNoMapping()", "<test>", 1, null);
		}
		finally {
			Context.exit();
		}
	}


}
