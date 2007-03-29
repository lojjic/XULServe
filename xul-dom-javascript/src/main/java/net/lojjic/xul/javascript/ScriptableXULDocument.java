package net.lojjic.xul.javascript;

import net.lojjic.rhino.annotations.JSFunction;
import net.lojjic.rhino.annotations.JSGetter;
import net.lojjic.rhino.annotations.JSSetter;
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
public class ScriptableXULDocument extends ScriptableDocument {

	private XULDocument delegateXULDocument;

	public ScriptableXULDocument() {
		super();
	}

	public ScriptableXULDocument(Scriptable scope, XULDocument delegateXULDocument) {
		super(scope, delegateXULDocument);
		this.delegateXULDocument = delegateXULDocument;
	}

	@JSFunction("addBroadcastListenerFor")
	public void addBroadcastListenerFor(Object broadcaster, Object observer, String attr) {
		delegateXULDocument.addBroadcastListenerFor(
				convertArg(broadcaster, Element.class), convertArg(observer, Element.class), attr);
	}

	@JSGetter("commandDispatcher")
	public Object getCommandDispatcher() {
		return convertReturnValue(delegateXULDocument.getCommandDispatcher());
	}

	@JSFunction("getElementsByAttribute")
	public Object getElementsByAttribute(String name, String value) {
		return convertReturnValue(delegateXULDocument.getElementsByAttribute(name, value));
	}

	@JSGetter("height")
	public long getHeight() {
		return delegateXULDocument.getHeight();
	}

	@JSGetter("popupNode")
	public Object getPopupNode() {
		return convertReturnValue(delegateXULDocument.getPopupNode());
	}

	@JSGetter("popupRangeOffset")
	public long getPopupRangeOffset() {
		return delegateXULDocument.getPopupRangeOffset();
	}

	@JSGetter("popupRangeParent")
	public Object getPopupRangeParent() {
		return convertReturnValue(delegateXULDocument.getPopupRangeParent());
	}

	@JSGetter("tooltipNode")
	public Object getTooltipNode() {
		return convertReturnValue(delegateXULDocument.getTooltipNode());
	}

	@JSGetter("trustedPopupEvent")
	public Object getTrustedPopupEvent() {
		return convertReturnValue(delegateXULDocument.getTrustedPopupEvent());
	}

	@JSGetter("width")
	public long getWidth() {
		return delegateXULDocument.getWidth();
	}

	@JSFunction("loadOverlay")
	public void loadOverlay(String url) {
		delegateXULDocument.loadOverlay(url);
	}

	@JSFunction("persist")
	public void persist(String id, String attr) {
		delegateXULDocument.persist(id, attr);
	}

	@JSFunction("removeBroadcastListenerFor")
	public void removeBroadcastListenerFor(Object broadcaster, Object observer, String attr) {
		delegateXULDocument.removeBroadcastListenerFor(
				convertArg(broadcaster, Element.class), convertArg(observer, Element.class), attr);
	}

	@JSSetter("popupNode")
	public void setPopupNode(Object node) {
		delegateXULDocument.setPopupNode(convertArg(node, Node.class));
	}

	@JSSetter("tooltipNode")
	public void setTooltipNode(Object node) {
		delegateXULDocument.setTooltipNode(convertArg(node, Node.class));
	}

	@JSSetter("trustedPopupEvent")
	public void setTrustedPopupEvent(Object event) {
		delegateXULDocument.setTrustedPopupEvent(convertArg(event, Event.class));
	}

	@JSFunction("trustedGetPopupNode")
	public Object trustedGetPopupNode() {
		return convertReturnValue(delegateXULDocument.trustedGetPopupNode());
	}

	@JSFunction("trustedGetTooltipNode")
	public Object trustedGetTooltipNode() {
		return convertReturnValue(delegateXULDocument.trustedGetTooltipNode());
	}


	///// DocumentView interface: /////

	@JSGetter("defaultView")
	public Object getDefaultView() {
		if(!(delegateXULDocument instanceof DocumentView)) {
			return null;
		}
		return convertReturnValue(((DocumentView)delegateXULDocument).getDefaultView());
	}
}
