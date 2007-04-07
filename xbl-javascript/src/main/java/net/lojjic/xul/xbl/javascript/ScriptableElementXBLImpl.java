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
public class ScriptableElementXBLImpl<T extends ElementXBLImpl> extends ScriptableElement<T> {

	public ScriptableElementXBLImpl() {
		super();
	}

	public ScriptableElementXBLImpl(Scriptable scope, T elementXBLImpl) {
		super(scope, elementXBLImpl);
	}

	@JSFunction("addBinding")
	public void addBinding(String bindingURL) {
		unwrap().addBinding(bindingURL);
	}

	@JSGetter("anonymousParent")
	public Object getAnonymousParent() {
		return convertReturnValue(unwrap().getAnonymousParent());
	}

	@JSGetter("bindingOwner")
	public Object getBindingOwner() {
		return convertReturnValue(unwrap().getBindingOwner());
	}

	@JSGetter("xblChildNodes")
	public Object getXblChildNodes() {
		return convertReturnValue(unwrap().getXblChildNodes());
	}

	@JSFunction("removeBinding")
	public void removeBinding(String bindingURL) {
		unwrap().removeBinding(bindingURL);
	}


	/**
	 * Enhance JS get to inspect the fields, properties, and methods
	 * of the applied XBL bindings.
	 */
	@Override
	public Object get(String name, Scriptable start) {
		Object result = super.get(name, start);
		if(result == NOT_FOUND && unwrap().appliedBindings.size() > 0) {
			for(XBLBinding binding : unwrap().appliedBindings) {
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
		if(unwrap().appliedBindings.size() > 0) {
			for(XBLBinding binding : unwrap().appliedBindings) {
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
			return Context.getCurrentContext().compileFunction(getParentScope(), (String)obj, unwrap().toString(), 0, null);
		}
		return null;
	}

}
