package net.lojjic.xml.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;
import org.w3c.dom.*;
import org.w3c.dom.events.*;
import org.w3c.dom.views.*;

import java.util.HashMap;
import java.lang.reflect.Constructor;

import net.lojjic.xml.javascript.events.*;
import net.lojjic.xml.javascript.views.*;

public class DOMWrapFactory extends WrapFactory {

	public static final String USER_DATA_KEY = "ScriptableDOMObject:UserDataKey";

	private HashMap<Class, Class<? extends ScriptableDOMObject>> mappings = new HashMap<Class, Class<? extends ScriptableDOMObject>>();


	/**
	 * Add a mapping from a DOM class/interface to its Scriptable wrapper class.
	 * @param domClass The DOM class or interface to be wrapped
	 * @param scriptableClass The Scriptable class in which instances of the DOM class
	 *        should be wrapped. Must be a subclass of {@link ScriptableDOMObject}.
	 */
	public void addWrapMapping(Class domClass, Class<? extends ScriptableDOMObject> scriptableClass) {
		mappings.put(domClass, scriptableClass);
	}


	/**
	 * Override the base functionality to handle our special DOM object
	 * wrappers.
	 * <p>
	 * If the Java object is a DOM Node, the wrapper is saved as a User
	 * Data attribute so the same wrapper instance will be returned next time.
	 * 
	 * @param cx the current Context for this thread
	 * @param scope the scope of the executing script
	 * @param javaObject the object to be wrapped
	 * @param staticType type hint. If security restrictions prevent to wrap
     *        object based on its class, staticType will be used instead.
	 * @return the wrapped value which shall not be null
	 */
	@Override
	public Scriptable wrapAsJavaObject(Context cx, Scriptable scope, Object javaObject, Class staticType) {
		Scriptable wrapper = null;

		// If javaObject is a DOM Node, check the user data for an existing wrapper:
		if(javaObject instanceof Node) {
			wrapper = (Scriptable)((Node)javaObject).getUserData(USER_DATA_KEY);
			if(wrapper != null) {
				return wrapper;
			}
		}

		// If a matching wrapper class is defined in the mappings, create an instance:
		Class javaClass = javaObject.getClass();
		Class wrapperClass = getWrapperForClass(javaClass);
		if(wrapperClass != null) {
			try {
				Constructor constructor = wrapperClass.getConstructor(Scriptable.class, javaClass);
				wrapper = (Scriptable)constructor.newInstance(scope, javaObject);
			}
			catch(Exception e) {
				// TODO handle more elegantly
				System.out.println("Problem instantiating JavaScript wrapper class; falling back to default wrapper.");
			}
		}

		// Fall back to inherited behavior:
		if(wrapper == null) {
			wrapper = super.wrapAsJavaObject(cx, scope, javaObject, staticType);
		}

		// If javaObject is a DOM Node, save the wrapper as a user data attribute
		// so the same instance can be reused next time:
		if(javaObject instanceof Node) {
			((Node)javaObject).setUserData(USER_DATA_KEY, wrapper, null);
		}

		return wrapper;
	}


	/**
	 * Find the most specific mapped wrapper for the given Class.
	 * Walks the inheritance line, checking each class and its explicitly
	 * declared interfaces for a match in the mappings. If no mapping
	 * is found, null is returned.
	 */
	private Class getWrapperForClass(Class clazz) {
		while(clazz != null) {
			// Try the class directly:
			Class wrapper = mappings.get(clazz);
			if(wrapper != null) {
				return wrapper;
			}
			// Try the class's interfaces:
			for(Class iface : clazz.getInterfaces()) {
				wrapper = mappings.get(iface);
				if(wrapper != null) {
					return wrapper;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}


	/**
	 * Add a default set of mappings for the core DOM interfaces.
	 */
	public void addDefaultWrapMappings() {
		// Core:
		addWrapMapping(Attr.class, ScriptableAttr.class);
		addWrapMapping(CDATASection.class, ScriptableCDATASection.class);
		addWrapMapping(CharacterData.class, ScriptableCharacterData.class);
		addWrapMapping(Comment.class, ScriptableComment.class);
		addWrapMapping(Document.class, ScriptableDocument.class);
		addWrapMapping(DocumentFragment.class, ScriptableDocumentFragment.class);
		addWrapMapping(DocumentType.class, ScriptableDocumentType.class);
		addWrapMapping(DOMConfiguration.class, ScriptableDOMConfiguration.class);
		addWrapMapping(DOMError.class, ScriptableDOMError.class);
		addWrapMapping(DOMErrorHandler.class, ScriptableDOMErrorHandler.class);
		addWrapMapping(DOMImplementation.class, ScriptableDOMImplementation.class);
		addWrapMapping(DOMImplementationList.class, ScriptableDOMImplementationList.class);
		addWrapMapping(DOMImplementationSource.class, ScriptableDOMImplementationSource.class);
		addWrapMapping(DOMLocator.class, ScriptableDOMLocator.class);
		addWrapMapping(DOMStringList.class, ScriptableDOMStringList.class);
		addWrapMapping(Element.class, ScriptableElement.class);
		addWrapMapping(Entity.class, ScriptableEntity.class);
		addWrapMapping(EntityReference.class, ScriptableEntityReference.class);
		addWrapMapping(NamedNodeMap.class, ScriptableNamedNodeMap.class);
		addWrapMapping(NameList.class, ScriptableNameList.class);
		addWrapMapping(Node.class, ScriptableNode.class);
		addWrapMapping(NodeList.class, ScriptableNodeList.class);
		addWrapMapping(Notation.class, ScriptableNotation.class);
		addWrapMapping(ProcessingInstruction.class, ScriptableProcessingInstruction.class);
		addWrapMapping(Text.class, ScriptableText.class);
		addWrapMapping(TypeInfo.class, ScriptableTypeInfo.class);
		addWrapMapping(UserDataHandler.class, ScriptableUserDataHandler.class);

		// Events:
		addWrapMapping(Event.class, ScriptableEvent.class);
		addWrapMapping(EventListener.class, ScriptableEventListener.class);
		addWrapMapping(MouseEvent.class, ScriptableMouseEvent.class);
		addWrapMapping(MutationEvent.class, ScriptableMutationEvent.class);
		addWrapMapping(UIEvent.class, ScriptableUIEvent.class);

		// Views:
		addWrapMapping(AbstractView.class, ScriptableAbstractView.class);
	}

}
