package net.lojjic.xul;

import org.w3c.dom.views.AbstractView;

public interface Window extends AbstractView {
	
	/**
	 * Accessor for this window's parent window, or the window itself if
	 * there is no parent, or if the parent is of different type
	 * (i.e. this does not cross chrome-content boundaries).
	 */
	Window getParent(); //readonly
	
	/**
	 * Accessor for the root of this hierarchy of windows. This root may
	 * be the window itself if there is no parent, or if the parent is
	 * of different type (i.e. this does not cross chrome-content
	 * boundaries).
	 *
	 * This property is "replaceable" in JavaScript
	 */
	Window getTop(); //readonly
	
	/**
	 * Accessor for the object that controls whether or not scrollbars
	 * are shown in this window.
	 *
	 * This attribute is "replaceable" in JavaScript
	 */
	//TODO nsIDOMBarProp getScrollbars(); //readonly
	
	/**
	 * Accessor for the child windows in this window.
	 */
	//TODO WindowCollection getFrames(); //readonly
	
	/**
	 * Set/Get the name of this window.
	 *
	 * This attribute is "replaceable" in JavaScript
	 */
	String getName();
	
	/**
	 * Set/Get the document scale factor as a multiplier on the default
	 * size. When setting this attribute, a NS_ERROR_NOT_IMPLEMENTED
	 * error may be returned by implementations not supporting
	 * zoom. Implementations not supporting zoom should return 1.0 all
	 * the time for the Get operation. 1.0 is equals normal size,
	 * i.e. no zoom.
	 */
	float getTextZoom();
	void setTextZoom(float textZoom);
	
	/**
	 * Accessor for the current x scroll position in this window in
	 * pixels.
	 *
	 * This attribute is "replaceable" in JavaScript
	 */
	long getScrollX(); //readonly
	
	/**
	 * Accessor for the current y scroll position in this window in
	 * pixels.
	 *
	 * This attribute is "replaceable" in JavaScript
	 */
	long getScrollY(); //readonly
	
	/**
	 * Method for scrolling this window to an absolute pixel offset.
	 */
	void scrollTo(long xScroll, long yScroll);
	
	/**
	 * Method for scrolling this window to a pixel offset relative to
	 * the current scroll position.
	 */
	void scrollBy(long xScrollDif, long yScrollDif);
	
	/**
	 * Method for accessing this window's selection object.
	 */
	//TODO Selection getSelection();
	
	/**
	 * Method for scrolling this window by a number of lines.
	 */
	void scrollByLines(long numLines);
	
	/**
	 * Method for scrolling this window by a number of pages.
	 */
	void scrollByPages(long numPages);
	
	/**
	 * Method for sizing this window to the content in the window.
	 */
	void sizeToContent();
		
}
