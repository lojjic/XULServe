package net.lojjic.xul.xbl.javascript;

import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.xml.javascript.ScriptableElement;
import net.lojjic.xul.xbl.impl.*;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/**
 * JavaScript Scriptable wrapper for {@link net.lojjic.xul.xbl.impl.ElementXBLImpl}
 */
@JSClassName("ElementXBL")
public class ScriptableElementXBLImpl extends ScriptableElement {

	private ElementXBLImpl delegateElementXBLImpl;

	public ScriptableElementXBLImpl() {
		super();
	}

	public ScriptableElementXBLImpl(Scriptable scope, ElementXBLImpl delegateElementXBLImpl) {
		super(scope, delegateElementXBLImpl);
		this.delegateElementXBLImpl = delegateElementXBLImpl;
	}

	@JSFunction("addBinding")
	public void addBinding(String bindingURL) {
		delegateElementXBLImpl.addBinding(bindingURL);
	}

	@JSGetter("anonymousParent")
	public Object getAnonymousParent() {
		return convertReturnValue(delegateElementXBLImpl.getAnonymousParent());
	}

	@JSGetter("bindingOwner")
	public Object getBindingOwner() {
		return convertReturnValue(delegateElementXBLImpl.getBindingOwner());
	}

	@JSGetter("xblChildNodes")
	public Object getXblChildNodes() {
		return convertReturnValue(delegateElementXBLImpl.getXblChildNodes());
	}

	@JSFunction("removeBinding")
	public void removeBinding(String bindingURL) {
		delegateElementXBLImpl.removeBinding(bindingURL);
	}


	/**
	 * Enhance JS get to inspect the fields, properties, and methods
	 * of the applied XBL bindings.
	 */
	@Override
	public Object get(String name, Scriptable start) {
		Object result = super.get(name, start);
		if(result == NOT_FOUND && delegateElementXBLImpl.appliedBindings.size() > 0) {
			for(XBLBinding binding : delegateElementXBLImpl.appliedBindings) {
				// Check fields:
				XBLField field = binding.getField(name);
				if(field != null && field.getInitializer() != null) {
					Function init = asFunction(field.getInitializer());
					field.setInitializer(init);
					result = init.call(Context.getCurrentContext(), getParentScope(), this, new Object[]{});
					put(name, this, result);
					break;
				}

				// Check property getters:
				XBLProperty prop = binding.getProperty(name);
				if(prop != null && prop.getGetter() != null) {
					Function getter = asFunction(prop.getGetter());
					prop.setGetter(getter);
					result = getter.call(Context.getCurrentContext(), getParentScope(), this, new Object[]{});
					break;
				}

				// Check methods:
				XBLMethod method = binding.getMethod(name);
				if(method != null && method.getBody() != null) {
					result = asFunction(method.getBody());
					method.setBody(result);
					break;
				}
			}
		}
		return convertReturnValue(result);
	}


	/**
	 * Enhance JS set to use a property setter from the applied XBL bindings
	 * if one exists.
	 */
	@Override
	public void put(String name, Scriptable start, Object value) {
		if(delegateElementXBLImpl.appliedBindings.size() > 0) {
			for(XBLBinding binding : delegateElementXBLImpl.appliedBindings) {
				XBLProperty prop = binding.getProperty(name);
				if(prop != null && prop.getSetter() != null) {
					Function setter = asFunction(prop.getSetter());
					prop.setSetter(setter);
					setter.call(Context.getCurrentContext(), getParentScope(), this, new Object[]{value});
					return;
				}
			}
		}
		super.put(name, start, value);
	}


	private Function asFunction(Object obj) {
		if(obj instanceof Function) {
			return (Function)obj;
		}
		if(obj instanceof String) {
			return Context.getCurrentContext().compileFunction(getParentScope(), (String)obj, delegateElement.toString(), 0, null);
		}
		return null;
	}

}
