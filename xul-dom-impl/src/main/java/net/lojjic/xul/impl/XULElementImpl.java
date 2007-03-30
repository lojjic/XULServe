package net.lojjic.xul.impl;

import java.util.ArrayList;
import java.util.List;

import net.lojjic.xul.XULConstants;
import net.lojjic.xul.XULElement;
import net.lojjic.xul.XULCommandEvent;
import net.lojjic.xul.XULTemplateBuilder;
import net.lojjic.xul.rdf.RDFCompositeDataSource;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.impl.RDFCompositeDataSourceImpl;
import net.lojjic.xul.xbl.impl.ElementXBLImpl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.events.UIEvent;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.apache.commons.lang.StringUtils;

/**
 * {@link net.lojjic.xul.XULElement} implementation
 */
public class XULElementImpl extends ElementXBLImpl implements XULElement {

	protected XULDocumentImpl ownerXULDocument;
	private XULTemplateBuilder xulTemplateBuilder;
	private RDFCompositeDataSource rdfCompositeDataSource;
	private RDFResource rdfResource;
	
	
	/**
	 * Constructor.
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, XULConstants.XUL_NAMESPACE, qualifiedName);
		this.ownerXULDocument = ownerXULDocument;
	}
	
	
	
	public String getId() {
		return getAttribute("id");
	}

	public void setId(String id) {
		setAttribute("id", id);
		ownerXULDocument.putIdentifier(id, this);
	}
	
	public String getClassName() {
		return getAttribute("class");
	}

	public void setClassName(String className) {
		setAttribute("class", className);
	}

	public String getAlign() {
		return getAttribute("align");
	}

	public void setAlign(String align) {
		setAttribute("align", align);
	}

	public String getDir() {
		return getAttribute("dir");
	}

	public void setDir(String dir) {
		setAttribute("dir", dir);
	}

	public String getFlex() {
		return getAttribute("flex");
	}

	public void setFlex(String flex) {
		setAttribute("flex", flex);
	}

	public String getFlexGroup() {
		return getAttribute("flexgroup");
	}

	public void setFlexGroup(String flexGroup) {
		setAttribute("flexgroup", flexGroup);
	}

	public String getOrdinal() {
		return getAttribute("ordinal");
	}

	public void setOrdinal(String ordinal) {
		setAttribute("ordinal", ordinal);
	}

	public String getOrient() {
		return getAttribute("orient");
	}

	public void setOrient(String orient) {
		setAttribute("orient", orient);
	}

	public String getPack() {
		return getAttribute("pack");
	}

	public void setPack(String pack) {
		setAttribute("pack", pack);
	}

	public boolean isHidden() {
		return Boolean.parseBoolean(getAttribute("hidden"));
	}

	public void setHidden(boolean hidden) {
		setAttribute("hidden", String.valueOf(hidden));
	}

	public boolean isCollapsed() {
		return Boolean.parseBoolean(getAttribute("collapsed"));
	}

	public void setCollapsed(boolean collapsed) {
		setAttribute("collapsed", String.valueOf(collapsed));
	}

	public String getObserves() {
		return getAttribute("observes");
	}

	public void setObserves(String observes) {
		setAttribute("observes", observes);
	}

	public String getWidth() {
		return getAttribute("width");
	}

	public void setWidth(String width) {
		setAttribute("width", width);
	}

	public String getHeight() {
		return getAttribute("height");
	}

	public void setHeight(String height) {
		setAttribute("height", height);
	}

	public String getMinWidth() {
		return getAttribute("minwidth");
	}

	public void setMinWidth(String minWidth) {
		setAttribute("minwidth", minWidth);
	}

	public String getMinHeight() {
		return getAttribute("minheight");
	}

	public void setMinHeight(String minHeight) {
		setAttribute("minHeight", minHeight);
	}

	public String getMaxWidth() {
		return getAttribute("maxwidth");
	}

	public void setMaxWidth(String maxWidth) {
		setAttribute("maxwidth", maxWidth);
	}

	public String getMaxHeight() {
		return getAttribute("maxheight");
	}

	public void setMaxHeight(String maxHeight) {
		setAttribute("maxheight", maxHeight);
	}

	public String getPersist() {
		return getAttribute("persist");
	}

	public void setPersist(String persist) {
		setAttribute("persist", persist);
	}

	public String getLeft() {
		return getAttribute("left");
	}

	public void setLeft(String left) {
		setAttribute("left", left);
	}

	public String getTop() {
		return getAttribute("top");
	}

	public void setTop(String top) {
		setAttribute("top", top);
	}

	public String getDatasources() {
		return getAttribute("datasources");
	}

	public void setDatasources(String datasources) {
		setAttribute("datasources", datasources);
	}

	public String getRef() {
		return getAttribute("ref");
	}

	public void setRef(String ref) {
		setAttribute("ref", ref);
	}

	public String getTooltipText() {
		return getAttribute("tooltiptext");
	}

	public void setTooltipText(String tooltipText) {
		setAttribute("tooltiptext", tooltipText);
	}

	public String getStatusText() {
		return getAttribute("statustext");
	}

	public void setStatusText(String statusText) {
		setAttribute("statustext", statusText);
	}

	public boolean isAllowEvents() {
		return Boolean.parseBoolean(getAttribute("allowevents"));
	}

	public void setAllowEvents(boolean allowEvents) {
		setAttribute("allowevents", String.valueOf(allowEvents));
	}

	public CSSStyleDeclaration getStyle() {
		return null; //TODO
		//return getAttribute("style");
	}

	public XULTemplateBuilder getBuilder() //readonly
	{
		// lazily instantiate the builder
		if(xulTemplateBuilder == null && getDatabase() != null) {
			RDFService rdfService = null; //TODO get the RDFService
			xulTemplateBuilder = new XULTemplateBuilderImpl(rdfService, this);
		}
		return xulTemplateBuilder;
	}

	public RDFCompositeDataSource getDatabase() //readonly
	{
		// lazily load the datasource
		if(rdfCompositeDataSource == null) {
			String attr = StringUtils.trimToNull(getAttribute("datasources"));
			if(attr != null) {
				RDFService rdfService = null; //TODO get the RDFService
				rdfCompositeDataSource = new RDFCompositeDataSourceImpl(rdfService); //TODO should we ask the RDFService for this?
				for(String uri : attr.split("\\s+")) {
					rdfCompositeDataSource.addDataSource(rdfService.getDataSource(uri));
				}
			}
		}
		return rdfCompositeDataSource;
	}

	public RDFResource getResource() //readonly
	{
		if(rdfResource == null) {
			String uri = StringUtils.trimToNull(getAttribute("uri"));
			if(uri != null) {
				RDFService rdfService = null; //TODO get the RDFService
				rdfResource = rdfService.getResource(uri);
			}
		}
		return rdfResource;
	}

	public void focus() {
		UIEvent evt = (UIEvent)ownerXULDocument.createEvent("UIEvents");
		evt.initUIEvent("focus", true, true, ownerXULDocument.getDefaultView(), 0);
		dispatchEvent(evt);
	}

	public void blur() {
		UIEvent evt = (UIEvent)ownerXULDocument.createEvent("UIEvents");
		evt.initUIEvent("blur", true, true, ownerXULDocument.getDefaultView(), 0);
		dispatchEvent(evt);
	}

	public void click() {
		MouseEvent evt = (MouseEvent)ownerXULDocument.createEvent("MouseEvents");
		evt.initMouseEvent("click", true, true, ownerXULDocument.getDefaultView(),
				1, 0, 0, 0, 0, false, false, false, false, (short)0, this);
		dispatchEvent(evt);
	}

	public void doCommand() {
		XULCommandEvent evt = (XULCommandEvent)ownerXULDocument.createEvent("XULCommandEvents");
		evt.initCommandEvent("command", true, true, ownerXULDocument.getDefaultView(), 0, false, false, false, false, null);
		dispatchEvent(evt);
	}

	public NodeList getElementsByAttribute(String name, String value) {
		// TODO find a more efficient way to perform this search
		NodeList all = getElementsByTagNameNS(XULConstants.XUL_NAMESPACE, "*");
		final List<Node> matching = new ArrayList<Node>();
		for(int i=0; i<all.getLength(); i++) {
			Element elt = (Element)all.item(i);
			String attrVal = elt.getAttribute(name);
			if(attrVal != null && attrVal.equals(value)) {
				matching.add(elt);
			}
		}
		return new NodeList() {
			public Node item(int index) {
				return matching.get(index);
			}
			public int getLength() {
				return matching.size();
			}
		};
	}



	/**
	 * Element factory
	 */
	public static XULElementFactory getFactory() {
		return new XULElementFactory() {
			public XULElement create(XULDocumentImpl ownerDocument, String qualifiedName) {
				return new XULElementImpl(ownerDocument, qualifiedName);
			}
		};
	}

}
