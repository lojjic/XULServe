package net.lojjic.xul;

import org.w3c.dom.Element;

public interface XULCommandDispatcher {

	Element getFocusedElement();
	void setFocusedElement(Element element);
	
	Window getFocusedWindow();
	void setFocusedWindow(Window window);
	
	void addCommandUpdater(Element updater, String events, String targets);
	
	void removeCommandUpdater(Element updater);
	
	void updateCommands(String eventName);
	
	Controller getControllerForCommand(String command);
	Controllers getControllers();
	
	void advanceFocus();
	void rewindFocus();
	void advanceFocusIntoSubtree(Element elt);
	
	boolean isSuppressFocusScroll();
	void setSuppressFocusScroll(boolean suppressFocusScroll);
}
