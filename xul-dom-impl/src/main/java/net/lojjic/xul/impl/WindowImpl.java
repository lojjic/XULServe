package net.lojjic.xul.impl;

import net.lojjic.xul.Window;
import org.apache.commons.lang.NotImplementedException;
import org.w3c.dom.views.DocumentView;

public class WindowImpl implements Window {
	
	private String name;
	private XULDocumentImpl document;
	private WindowImpl parent;

	public WindowImpl(String name, WindowImpl parent, XULDocumentImpl document) {
		this.name = name;
		this.parent = (parent == null) ? this : parent;
		this.document = document;
	}

	public DocumentView getDocument() {
		return document;
	}

	public Window getParent() {
		return parent;
	}

	public Window getTop() {
		Window top = this;
		while(top.getParent() != null) {
			top = top.getParent();
		}
		return top;
	}

	public String getName() {
		return name;
	}

	public float getTextZoom() {
		return 1.0f;
	}

	public void setTextZoom(float textZoom) {
		throw new NotImplementedException("Text zoom is not suppported in this implementation.");
	}

	public long getScrollX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getScrollY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void scrollTo(long xScroll, long yScroll) {
		// TODO Auto-generated method stub
	}

	public void scrollBy(long xScrollDif, long yScrollDif) {
		// TODO Auto-generated method stub
	}

	public void scrollByLines(long numLines) {
		// TODO Auto-generated method stub
	}

	public void scrollByPages(long numPages) {
		// TODO Auto-generated method stub
	}

	public void sizeToContent() {
		// TODO Auto-generated method stub
	}

}
