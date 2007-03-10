package net.lojjic.xml.javascript;

import junit.framework.TestCase;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Scriptable;
import net.lojjic.rhino.annotations.AnnotationScriptableObject;

import java.lang.reflect.InvocationTargetException;

/**
 * Test cases for the ScriptableDOMObject wrappers
 */
public class ScriptableDOMObjectTest extends TestCase {

	public void testScriptableDOM() throws Exception {
		Context ctxt = Context.enter();
		try {
			DOMWrapFactory wrapFactory = new DOMWrapFactory();
			wrapFactory.addDefaultWrapMappings();
			ctxt.setWrapFactory(wrapFactory);

			ScriptableObject scope = ctxt.initStandardObjects();
			defineDOMClasses(scope);
		}
		finally {
			Context.exit();
		}
	}


	private void defineDOMClasses(ScriptableObject scope) throws IllegalAccessException,
			InvocationTargetException, InstantiationException {
		AnnotationScriptableObject.defineClass(scope, ScriptableNode.class);
	}

}
