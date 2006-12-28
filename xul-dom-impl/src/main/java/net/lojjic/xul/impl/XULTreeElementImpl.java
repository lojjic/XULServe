package net.lojjic.xul.impl;

import net.lojjic.xul.XULTreeElement;
import net.lojjic.xul.XULTextBoxElement;
import org.w3c.dom.Element;

/**
 * {@link net.lojjic.xul.XULTreeElement} implementation
 */
public class XULTreeElementImpl extends XULElementImpl implements XULTreeElement {

	private boolean editable;

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
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	// For editable trees only
	public XULTextBoxElement getInputField() //readonly
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
