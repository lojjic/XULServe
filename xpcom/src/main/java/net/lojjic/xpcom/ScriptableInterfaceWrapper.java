package net.lojjic.xpcom;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.NativeJavaObject;

/**
 * A specialization of {@link org.mozilla.javascript.NativeJavaObject} that only
 * exposes the methods/properties of the given interface.
 */
public class ScriptableInterfaceWrapper extends NativeJavaObject {

	public ScriptableInterfaceWrapper() {
	}

	/**
	 * This is an exact reimplementation of the superclass constructor, except for the
	 * order in which the statements are executed.  We delay setting the javaObject
	 * member until after initMembers(), because that causes the staticType to be
	 * used rather than the object's class. This is nasty-hacky since it relies heavily on
	 * NativeJavaObject's exact implementation, but appears to be the best option
	 * since the class that does most of the actual work, JavaMembers, is package
	 * scope so it cannot be extended or called directly from this class. 
	 */
	public ScriptableInterfaceWrapper(Scriptable scope, Object javaObject, Class interfaceClass) {
		this.parent = scope;
		this.staticType = interfaceClass;
		initMembers();
		this.javaObject = javaObject;
	}

}
