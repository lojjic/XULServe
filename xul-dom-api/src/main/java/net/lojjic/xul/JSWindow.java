package net.lojjic.xul;

import org.w3c.dom.events.Event;

/**
 * JSWindow interface
 */
public interface JSWindow {

	/**
	 * Window.frames in Netscape 4.x and IE is just a reference to the window itself
	 * (i.e. window.frames === window), but this doesn't make sense from a generic
	 * API point of view so that's why this is JS specific.
	 */
	Window getFrames();

	void captureEvents (int eventFlags);

	void clearInterval();

	/**
	 * These methods take one optional argument that's the timer ID to clear. Often in
	 * existing code these methods are passed undefined, which is a nop so we need to
	 * support that as well.
	 */
	void clearTimeout();
	void clearTimeout(int timeout);

	void disableExternalCapture();

	void dump(String str);

	void enableExternalCapture();

	boolean find();

	Window open();

	Window openDialog();

	/**
	 * The prompt method takes up to four arguments, the arguments are message, initial
	 * prompt value, title and a save password flag
	 */
	String prompt();
	String prompt(String message);
	String prompt(String message, String initialValue);
	String prompt(String message, String initialValue, String title);
	String prompt(String message, String initialValue, String title, boolean savePassword);

	void releaseEvents(int eventFlags);

	void routeEvent(Event evt);

	int setInterval();

	void setResizable(boolean resizable);

	/**
	 * These methods take typeless arguments and optional arguments, the first argument
	 * is either a function or a string, the second argument must be a number (ms) and
	 * the rest of the arguments (2 ... n) are passed to the callback function
	 */
	int setTimeout(Object exec, int time, Object... args);

}
