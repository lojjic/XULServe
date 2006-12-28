package net.lojjic.xul.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lojjic.xul.Constants;
import net.lojjic.xul.XULElement;

import org.apache.xerces.dom.ElementNSImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSStyleDeclaration;

/**
 * {@link net.lojjic.xul.XULElement} implementation
 */
public class XULElementImpl extends ElementNSImpl implements XULElement {
	
	private static enum Align { stretch, start, center, end, baseline }
	private static enum Orient { vertical, horizontal }
	private static enum Pack { start, center, end }
	
	private String id;
	private String className;
	private Align align = Align.stretch;
	private String dir;
	private String flex;
	private String flexGroup;
	private String ordinal;
	private Orient orient;
	private Pack pack;
	private boolean hidden;
	private boolean collapsed;
	private String observes;
	private String width;
	private String height;
	private String minWidth;
	private String minHeight;
	private String maxWidth;
	private String maxHeight;
	private String persist;
	private String left;
	private String top;
	private String datasources;
	private String ref;
	private String tooltipText;
	private String statusText;
	private boolean allowEvents;
	private CSSStyleDeclaration style;
	
	protected XULDocumentImpl ownerXULDocument;
	
	
	/**
	 * Constructor.
	 * @param ownerXULDocument
	 * @param qualifiedName
	 */
	public XULElementImpl(XULDocumentImpl ownerXULDocument, String qualifiedName) {
		super(ownerXULDocument, Constants.XUL_NAMESPACE, qualifiedName);
		this.ownerXULDocument = ownerXULDocument;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		ownerXULDocument.putIdentifier(id, this);
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAlign() {
		return align.toString();
	}

	public void setAlign(String align) {
		this.align = Align.valueOf(align);
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFlex() {
		return flex;
	}

	public void setFlex(String flex) {
		this.flex = flex;
	}

	public String getFlexGroup() {
		return flexGroup;
	}

	public void setFlexGroup(String flexGroup) {
		this.flexGroup = flexGroup;
	}

	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public String getOrient() {
		return orient.toString();
	}

	public void setOrient(String orient) {
		this.orient = Orient.valueOf(orient);
	}

	public String getPack() {
		return pack.toString();
	}

	public void setPack(String pack) {
		this.pack = Pack.valueOf(pack);
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public String getObserves() {
		return observes;
	}

	public void setObserves(String observes) {
		this.observes = observes;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getPersist() {
		return persist;
	}

	public void setPersist(String persist) {
		this.persist = persist;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getDatasources() {
		return datasources;
	}

	public void setDatasources(String datasources) {
		this.datasources = datasources;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public boolean isAllowEvents() {
		return allowEvents;
	}

	public void setAllowEvents(boolean allowEvents) {
		this.allowEvents = allowEvents;
	}

	public CSSStyleDeclaration getStyle() {
		return style;
	}
	
	public void setStyle(String style)
	{
		// TODO parse into declaration
	}

	public void focus() {
		// TODO
	}

	public void blur() {
		// TODO		
	}

	public void click() {
		// TODO
	}

	public void doCommand() {
		// TODO
	}

	public NodeList getElementsByAttribute(String name, String value) {
		// TODO find a more efficient way to perform this search
		NodeList all = getElementsByTagNameNS(Constants.XUL_NAMESPACE, "*");
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

	
	
	
	
	
	protected static Map<String, AttributeHandler<XULElementImpl>> attributeHandlers;
	
	public void setAttribute(String name, String value) {
		AttributeHandler<XULElementImpl> handler = attributeHandlers.get(name);
		if(handler != null)
		{
			handler.set(this, value);
		}
		super.setAttribute(name, value);
	}
	
	public String getAttribute(String name) {
		AttributeHandler<XULElementImpl> handler = attributeHandlers.get(name);
		if(handler != null)
		{
			return handler.get(this);
		}
		return super.getAttribute(name);
	}

	static {
		
		attributeHandlers.put("id", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getId();
			}
			public void set(XULElementImpl target, String value) {
				target.setId(value);
			}
		});
		
		attributeHandlers.put("class", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getClassName();
			}
			public void set(XULElementImpl target, String value) {
				target.setClassName(value);
			}
		});
		
		attributeHandlers.put("align", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getAlign();
			}
			public void set(XULElementImpl target, String value) {
				target.setAlign(value);
			}
		});
		
		attributeHandlers.put("dir", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getDir();
			}
			public void set(XULElementImpl target, String value) {
				target.setDir(value);
			}
		});
		
		attributeHandlers.put("flex", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getFlex();
			}
			public void set(XULElementImpl target, String value) {
				target.setFlex(value);
			}
		});
		
		attributeHandlers.put("flexgroup", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getFlexGroup();
			}
			public void set(XULElementImpl target, String value) {
				target.setFlexGroup(value);
			}
		});
		
		attributeHandlers.put("ordinal", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getOrdinal();
			}
			public void set(XULElementImpl target, String value) {
				target.setOrdinal(value);
			}
		});
		
		attributeHandlers.put("orient", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getOrient();
			}
			public void set(XULElementImpl target, String value) {
				target.setOrient(value);
			}
		});
		
		attributeHandlers.put("pack", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getPack();
			}
			public void set(XULElementImpl target, String value) {
				target.setPack(value);
			}
		});
		
		attributeHandlers.put("hidden", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return String.valueOf(target.isHidden());
			}
			public void set(XULElementImpl target, String value) {
				target.setHidden(Boolean.parseBoolean(value));
			}
		});
		
		attributeHandlers.put("collapsed", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return String.valueOf(target.isCollapsed());
			}
			public void set(XULElementImpl target, String value) {
				target.setCollapsed(Boolean.parseBoolean(value));
			}
		});
		
		attributeHandlers.put("observes", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getObserves();
			}
			public void set(XULElementImpl target, String value) {
				target.setObserves(value);
			}
		});
		
		attributeHandlers.put("width", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getWidth();
			}
			public void set(XULElementImpl target, String value) {
				target.setWidth(value);
			}
		});
		
		attributeHandlers.put("height", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getHeight();
			}
			public void set(XULElementImpl target, String value) {
				target.setHeight(value);
			}
		});
		
		attributeHandlers.put("minwidth", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getMinWidth();
			}
			public void set(XULElementImpl target, String value) {
				target.setMinWidth(value);
			}
		});
		
		attributeHandlers.put("minheight", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getMinHeight();
			}
			public void set(XULElementImpl target, String value) {
				target.setMinHeight(value);
			}
		});
		
		attributeHandlers.put("maxwidth", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getMaxWidth();
			}
			public void set(XULElementImpl target, String value) {
				target.setMaxWidth(value);
			}
		});
		
		attributeHandlers.put("maxheight", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getMaxHeight();
			}
			public void set(XULElementImpl target, String value) {
				target.setMaxHeight(value);
			}
		});
		
		attributeHandlers.put("persist", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getPersist();
			}
			public void set(XULElementImpl target, String value) {
				target.setPersist(value);
			}
		});
		
		attributeHandlers.put("left", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getLeft();
			}
			public void set(XULElementImpl target, String value) {
				target.setLeft(value);
			}
		});
		
		attributeHandlers.put("top", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getTop();
			}
			public void set(XULElementImpl target, String value) {
				target.setTop(value);
			}
		});
		
		attributeHandlers.put("datasources", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getDatasources();
			}
			public void set(XULElementImpl target, String value) {
				target.setDatasources(value);
			}
		});
		
		attributeHandlers.put("ref", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getRef();
			}
			public void set(XULElementImpl target, String value) {
				target.setRef(value);
			}
		});
		
		attributeHandlers.put("tooltiptext", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getTooltipText();
			}
			public void set(XULElementImpl target, String value) {
				target.setTooltipText(value);
			}
		});
		
		attributeHandlers.put("statustext", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getStatusText();
			}
			public void set(XULElementImpl target, String value) {
				target.setStatusText(value);
			}
		});
		
		attributeHandlers.put("allowevents", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return String.valueOf(target.isAllowEvents());
			}
			public void set(XULElementImpl target, String value) {
				target.setAllowEvents(Boolean.parseBoolean(value));
			}
		});
		
		attributeHandlers.put("style", new AttributeHandler<XULElementImpl>() {
			public String get(XULElementImpl target) {
				return target.getStyle().getCssText();
			}
			public void set(XULElementImpl target, String value) {
				target.setStyle(value);
			}
		});
		
	}
	
}
