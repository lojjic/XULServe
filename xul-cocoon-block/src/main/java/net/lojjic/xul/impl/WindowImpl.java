package net.lojjic.xul.impl;

import org.apache.commons.lang.NotImplementedException;
import org.w3c.dom.Document;

import net.lojjic.xul.Window;

public class WindowImpl implements Window {
	
	private String name;
	private Document document;
	private Window parent;
	private Window top;

	public Document getDocument() {
		return document;
	}

	public Window getParent() {
		return parent;
	}

	public Window getTop() {
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
