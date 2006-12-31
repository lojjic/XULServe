package net.lojjic.xul.xbl.impl;

import java.util.Map;
import java.util.HashMap;

/**
 * Class for managing XBL bindings.
 */
public class BindingManager {

	private Map<String, Binding> bindings = new HashMap<String, Binding>();

	public Binding getBindingForURL(String url) {
		Binding binding = bindings.get(url);
		if(binding == null) {
			binding = createBinding(url);
		}
		return binding;
	}

	private Binding createBinding(String url) {
		return null; //TODO
	}

}
