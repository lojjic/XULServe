package net.lojjic.xml.javascript.bootstrap;

import net.lojjic.rhino.annotations.AnnotationScriptableObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSStatic;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * Scriptable wrapper for {@link org.w3c.dom.bootstrap.DOMImplementationRegistry}
 */
@JSClassName("DOMImplementationRegistry")
public class ScriptableDOMImplementationRegistry extends AnnotationScriptableObject {

	public ScriptableDOMImplementationRegistry(Scriptable scope) {
		super(scope);
	}

	@JSStatic @JSFunction("getDOMImplementation")
	public static DOMImplementation getDOMImplementation(ScriptableObject obj, String features) {
		try {
			return DOMImplementationRegistry.newInstance().getDOMImplementation(features);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@JSStatic @JSFunction("getDOMImplementation")
	public static DOMImplementationList getDOMImplementationList(ScriptableObject obj, String features) {
		try {
			return DOMImplementationRegistry.newInstance().getDOMImplementationList(features);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
