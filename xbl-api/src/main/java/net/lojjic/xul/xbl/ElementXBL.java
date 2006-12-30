package net.lojjic.xul.xbl;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 * ElementXBL interface.
 *
 * See http://www.mozilla.org/projects/xbl/xbl.html#dom-elementxbl
 */
public interface ElementXBL {

	/**
	 * The xblChildNodes property is a NodeList that represents the true children of the
	 * element after insertion points and element tags have been applied. This property
	 * can be used to navigate the content model according to XBL after bindings have
	 * moved explicit and anonymous children using element and children tags.
	 */
	NodeList getXblChildNodes();

	/**
	 * The bindingOwner property is used to obtain the bound element with the binding
	 *  attached that is responsible for the generation of the specified anonymous node.
	 * This property enables an author to determine the scope of any content node. When
	 * content at the document-level scope is passed in as an argument, the property's
	 * value is null. See http://www.mozilla.org/projects/xbl/xbl.html#scoping for
	 * more information.
	 */
	Element getBindingOwner();

	/**
	 * The anonymousParent property's value is the anonymous parent for an element
	 * that was placed underneath an insertion point using the children element in
	 * XBL or for a bound element that was repositioned using the element tag. When
	 * an element that was not repositioned is passed in as an argument, the
	 * property's value is null.
	 */
	Element getAnonymousParent();

	/**
	 * The addBinding method attaches the specified binding (and any bindings that
	 * the binding inherits from) to an element. This call is not necessarily synchronous.
	 * The binding may not be attached yet when the call completes. See
	 * http://www.mozilla.org/projects/xbl/xbl.html#attach-dom for more information.
	 * 
	 * @param bindingURL A URI that specifies the location of a specific binding to attach.
	 */
	void addBinding(String bindingURL);

	/**
	 * The removeBinding method detaches the specified binding (and any bindings that the
	 * binding inherits from explicitly using the extends attribute) from the element. See
	 * http://www.mozilla.org/projects/xbl/xbl.html#binding-detached for more information.
	 *
	 * @param bindingURL A URL that specifies the location of a specific binding to detach.
	 */
	void removeBinding(String bindingURL);
}
