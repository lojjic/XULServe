package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
import net.lojjic.rhino.annotations.JSClassName;
import net.lojjic.xml.javascript.ScriptableDocument;
import net.lojjic.xul.XULDocument;
import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.views.DocumentView;

/**
 * Scriptable wrapper for {@link net.lojjic.xul.XULDocument}
 */
@JSClassName("XULDocument")
public class ScriptableXULDocument<T extends XULDocument> extends ScriptableDocument<T> {

	public ScriptableXULDocument() {
		super();
	}

	public ScriptableXULDocument(Scriptable scope, T xulDocument) {
		super(scope, xulDocument);
	}

	@JSFunction("addBroadcastListenerFor")
	public void addBroadcastListenerFor(Object broadcaster, Object observer, String attr) {
		unwrap().addBroadcastListenerFor(
				convertArg(broadcaster, Element.class), convertArg(observer, Element.class), attr);
	}

	@JSGetter("commandDispatcher")
	public Object getCommandDispatcher() {
		return convertReturnValue(unwrap().getCommandDispatcher());
	}

	@JSFunction("getElementsByAttribute")
	public Object getElementsByAttribute(String name, String value) {
		return convertReturnValue(unwrap().getElementsByAttribute(name, value));
	}

	@JSGetter("height")
	public long getHeight() {
		return unwrap().getHeight();
	}

	@JSGetter("popupNode")
	public Object getPopupNode() {
		return convertReturnValue(unwrap().getPopupNode());
	}

	@JSGetter("popupRangeOffset")
	public long getPopupRangeOffset() {
		return unwrap().getPopupRangeOffset();
	}

	@JSGetter("popupRangeParent")
	public Object getPopupRangeParent() {
		return convertReturnValue(unwrap().getPopupRangeParent());
	}

	@JSGetter("tooltipNode")
	public Object getTooltipNode() {
		return convertReturnValue(unwrap().getTooltipNode());
	}

	@JSGetter("trustedPopupEvent")
	public Object getTrustedPopupEvent() {
		return convertReturnValue(unwrap().getTrustedPopupEvent());
	}

	@JSGetter("width")
	public long getWidth() {
		return unwrap().getWidth();
	}

	@JSFunction("loadOverlay")
	public void loadOverlay(String url) {
		unwrap().loadOverlay(url);
	}

	@JSFunction("persist")
	public void persist(String id, String attr) {
		unwrap().persist(id, attr);
	}

	@JSFunction("removeBroadcastListenerFor")
	public void removeBroadcastListenerFor(Object broadcaster, Object observer, String attr) {
		unwrap().removeBroadcastListenerFor(
				convertArg(broadcaster, Element.class), convertArg(observer, Element.class), attr);
	}

	@JSSetter("popupNode")
	public void setPopupNode(Object node) {
		unwrap().setPopupNode(convertArg(node, Node.class));
	}

	@JSSetter("tooltipNode")
	public void setTooltipNode(Object node) {
		unwrap().setTooltipNode(convertArg(node, Node.class));
	}

	@JSSetter("trustedPopupEvent")
	public void setTrustedPopupEvent(Object event) {
		unwrap().setTrustedPopupEvent(convertArg(event, Event.class));
	}

	@JSFunction("trustedGetPopupNode")
	public Object trustedGetPopupNode() {
		return convertReturnValue(unwrap().trustedGetPopupNode());
	}

	@JSFunction("trustedGetTooltipNode")
	public Object trustedGetTooltipNode() {
		return convertReturnValue(unwrap().trustedGetTooltipNode());
	}


	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public Object getDefaultView() {
		if(!(unwrap() instanceof DocumentView)) {
			return null;
		}
		return convertReturnValue(((DocumentView)unwrap()).getDefaultView());
	}
}
