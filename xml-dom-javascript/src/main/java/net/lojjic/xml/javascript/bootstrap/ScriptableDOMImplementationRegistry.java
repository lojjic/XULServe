package net.lojjic.xml.javascript.bootstrap;

import net.lojjic.rhino.annotations.AnnotationScriptableObject;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSStatic;
import net.lojjic.xml.javascript.ScriptableDOMImplementation;
import net.lojjic.xml.javascript.ScriptableDOMImplementationList;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

/**
 * Scriptable wrapper for {@link org.w3c.dom.bootstrap.DOMImplementationRegistry}
 */
@JSClassName("DOMImplementationRegistry")
public class ScriptableDOMImplementationRegistry extends AnnotationScriptableObject {

	public ScriptableDOMImplementationRegistry() {
		super();
	}

	public ScriptableDOMImplementationRegistry(Scriptable scope) {
		super(scope);
	}

	@JSStatic @JSFunction("getDOMImplementation")
	public static ScriptableDOMImplementation getDOMImplementation(ScriptableObject scope, String features) {
		try {
			DOMImplementation result = DOMImplementationRegistry.newInstance().getDOMImplementation(features);
			return new ScriptableDOMImplementation<DOMImplementation>(scope, result);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@JSStatic @JSFunction("getDOMImplementation")
	public static ScriptableDOMImplementationList getDOMImplementationList(ScriptableObject scope, String features) {
		try {
			DOMImplementationList result = DOMImplementationRegistry.newInstance().getDOMImplementationList(features);
			return new ScriptableDOMImplementationList<DOMImplementationList>(scope, result);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
