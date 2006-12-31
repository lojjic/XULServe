package net.lojjic.xul.xbl.impl;

import org.w3c.dom.DocumentFragment;

import java.util.HashMap;

/**
 * Class modeling an XBL binding.
 */
public class Binding {

	private HashMap<String, Object> methods;
	private HashMap<String, Object> handlers;
	private DocumentFragment content;
	private Object constructor;

}
