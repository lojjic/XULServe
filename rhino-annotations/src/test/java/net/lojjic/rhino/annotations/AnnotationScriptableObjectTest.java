package net.lojjic.rhino.annotations;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Date;

/**
 * Test cases for {@link net.lojjic.rhino.annotations.AnnotationScriptableObject}
 */
public class AnnotationScriptableObjectTest {

	public void testAnnotationScriptableObject() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();

			AnnotationScriptableObject.defineClass(scope, AnnotatedTestObject.class);

			String jsFile = "/net/lojjic/rhino/annotations/AnnotationScriptableObjectTest.js";
			InputStream inputStream = getClass().getResourceAsStream(jsFile);
			Reader reader = new InputStreamReader(inputStream);

			cx.evaluateReader(scope, reader, jsFile, 0, null);
		}
		finally {
			Context.exit();
		}
	}

}
