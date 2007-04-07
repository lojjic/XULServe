package net.lojjic.xml.javascript;

import net.lojjic.xml.javascript.events.*;
import net.lojjic.xml.javascript.views.ScriptableAbstractView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;
import org.w3c.dom.*;
import org.w3c.dom.events.*;
import org.w3c.dom.views.AbstractView;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.WeakHashMap;

public class DOMWrapFactory extends WrapFactory {

	private HashMap<Class, Class<? extends Scriptable>> mappings =
			new HashMap<Class, Class<? extends Scriptable>>();

	private HashMap<Class, KeyValue<Class, Class<? extends Scriptable>>> classCache =
			new HashMap<Class, KeyValue<Class, Class<? extends Scriptable>>>();

	/**
	 * Cache mapping Java object instances to Scriptable wrapper object instances. This
	 * allows the same wrapper instance to be used for each call of #wrapAsJavaObject
	 * for a given Java object instance, which is necessary for e.g. setting custom
	 * JS properties on a given DOM node.
	 * <p>
	 * The cache uses weak references so that the wrapper instance will be automatically
	 * garbage collected when the Java object is garbage collected. It is important that
	 * the wrapper object itself maintains only a weak reference to the java object, to
	 * avoid a strong circular dependency that would prevent garbage collection (see the
	 * javadocs for {@link WeakHashMap}).
	 */
	private WeakHashMap<Object, Scriptable> wrapperCache =
			new WeakHashMap<Object, Scriptable>();

	/**
	 * Add a mapping from a DOM class/interface to its Scriptable wrapper class.
	 * @param domClass The DOM class or interface to be wrapped
	 * @param scriptableClass The Scriptable class in which instances of the DOM class
	 *        should be wrapped. Must be a subclass of {@link ScriptableDOMObject}.
	 */
	public void addWrapMapping(Class domClass, Class<? extends ScriptableDOMObject> scriptableClass) {
		mappings.put(domClass, scriptableClass);
		classCache.clear(); //clear cache
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

		// First check the cache for an existing wrapper instance:
		Scriptable wrapper = wrapperCache.get(javaObject);
		if(wrapper != null) {
			return wrapper;
		}

		// If a matching wrapper class is defined in the mappings, create an instance:
		KeyValue<Class, Class<? extends Scriptable>> mapping = findMappingForClass(javaObject.getClass());
		if(mapping != null) {
			try {
				Class wrappedClass = mapping.getKey();
				Class<? extends Scriptable> wrapperClass = mapping.getValue();
				Constructor<? extends Scriptable> constructor = wrapperClass.getConstructor(Scriptable.class, wrappedClass);
				wrapper = constructor.newInstance(scope, javaObject);
			}
			catch(Exception e) {
				Context.throwAsScriptRuntimeEx(e);
			}
		}

		// Fall back to inherited behavior:
		if(wrapper == null) {
			return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
		}

		// Save wrapper instance to the cache:
		wrapperCache.put(javaObject, wrapper);

		return wrapper;
	}


	/**
	 * Find a JS wrapper to use for the given Java class defined in the mappings, if
	 * one exists. A mapping may be defined for one of the class's implemented interfaces
	 * or superclasses, so the interface/inheritance chain is walked until a match is
	 * found. The result is cached for fast access later.
	 * @param javaClass The Java class which will be wrapped
	 * @return A {@link KeyValue} mapping whose key is the interface/superclass for which
	 *         the mapping was found, and whose value is the JavaScript wrapper class to
	 *         use. If no mapping was found, null will be returned.
	 */
	private KeyValue<Class, Class<? extends Scriptable>> findMappingForClass(Class javaClass) {
		KeyValue<Class, Class<? extends Scriptable>> result = null;

		// Check the cache - can have a null value so need to check for key existence:
		if(classCache.containsKey(javaClass)) {
			return classCache.get(javaClass);
		}

		// Check the mappings:
		Class wrappedClass = javaClass;
		loop: while(wrappedClass != null) {
			// Try the class directly:
			if(mappings.containsKey(wrappedClass)) {
				break;
			}
			// Try the class's interfaces:
			for(Class iface : wrappedClass.getInterfaces()) {
				if(mappings.containsKey(iface)) {
					wrappedClass = iface;
					break loop;
				}
			}
			// Walk up the inheritance chain:
			wrappedClass = wrappedClass.getSuperclass();
		}

		if(wrappedClass != null) {
			Class<? extends Scriptable> wrapperClass = mappings.get(wrappedClass);
			result = new KeyValue<Class,Class<? extends Scriptable>>(wrappedClass, wrapperClass);
		}

		// Add the result to the cache:
		classCache.put(javaClass, result);

		return result;
	}


	/**
	 * Simple container for a single key-value mapping
	 */
	private static class KeyValue<K, V> {
		private K key;
		private V val;
		public KeyValue(K key, V val) {
			this.key = key;
			this.val = val;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return val;
		}
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
