package net.lojjic.xul.impl;

import net.lojjic.xul.XULTreeElement;
import net.lojjic.xul.XULTextBoxElement;
import net.lojjic.xul.XULElement;
import org.w3c.dom.Element;

/**
 * {@link net.lojjic.xul.XULTreeElement} implementation
 */
public class XULTreeElementImpl extends XULElementImpl implements XULTreeElement {

	/**
	 * Constructor.
	 *
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULTreeElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, qualifiedName);
	}

	public Element getBody() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public boolean isEditable() {
		return Boolean.parseBoolean(getAttribute("editable"));
	}

	public void setEditable(boolean editable) {
		setAttribute("editable", String.valueOf(editable));
	}

	// For editable trees only
	public XULTextBoxElement getInputField() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULTreeElementImpl(ownerDocument, qualifiedName);
			}
		};
	}
}
