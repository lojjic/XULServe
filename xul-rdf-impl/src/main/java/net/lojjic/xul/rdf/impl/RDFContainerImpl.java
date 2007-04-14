package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link RDFContainer}, a wrapper for manipulating RDF containers
 */
public class RDFContainerImpl implements RDFContainer {

	private RDFContainerUtils rdfContainerUtils;
	private RDFDataSource dataSource;
	private RDFResource resource;

	private boolean needsRefresh = true;
	private List<RDFNode> elementsList = new ArrayList<RDFNode>();

	private RDFObserver modificationObserver = new RDFObserver() {
		private boolean isInBatch = false;
		private boolean modifiedInBatch = false;

		public void onAssert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
			if(source.equalsNode(resource)) {
				modified();
			}
		}

		public void onBeginUpdateBatch(RDFDataSource dataSource) {
			isInBatch = true;
		}

		public void onChange(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode oldTarget, RDFNode newTarget) {
			if(source.equalsNode(resource)) {
				modified();
			}
		}

		public void onEndUpdateBatch(RDFDataSource dataSource) {
			isInBatch = false;
			if(modifiedInBatch) {
				modified();
				modifiedInBatch = false;
			}
		}

		public void onMove(RDFDataSource dataSource, RDFResource oldSource, RDFResource newSource, RDFResource property, RDFNode target) {
			if(oldSource.equalsNode(resource) || newSource.equalsNode(resource)) {
				modified();
			}
		}

		public void onUnassert(RDFDataSource dataSource, RDFResource source, RDFResource property, RDFNode target) {
			if(source.equalsNode(resource)) {
				modified();
			}
		}

		private void modified() {
			if(isInBatch) {
				modifiedInBatch = true;
			} else {
				needsRefresh = true;
			}
		}
	};

	/**
	 * Constructor
	 * @param rdfService
	 * @param dataSource
	 * @param resource
	 */
	public RDFContainerImpl(RDFService rdfService, RDFDataSource dataSource, RDFResource resource) {
		this.rdfContainerUtils = new RDFContainerUtilsImpl(rdfService);
		init(dataSource, resource);
	}

	/**
	 * Return the underlying {@link net.lojjic.xul.rdf.RDFDataSource}
	 */
	public RDFDataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Return the wrapped {@link net.lojjic.xul.rdf.RDFResource}
	 */
	public RDFResource getResource() {
		return resource;
	}

	/**
	 * Initialize the container wrapper to the specified resource
	 * using the specified datasource for context.
	 */
	public void init(RDFDataSource dataSource, RDFResource resource) {
		this.dataSource = dataSource;
		if(!rdfContainerUtils.isContainer(dataSource, resource)) {
			throw new IllegalArgumentException("Tried to initialize RDFContainer wrapper with an RDF resource that is not a container.");
		}
		this.resource = resource;
		dataSource.addObserver(modificationObserver);
	}

	private List<RDFNode> getElementsList() {
		if(needsRefresh) {
			elementsList.clear();
			for(int i = 1; true; i++) {
				RDFResource arc = rdfContainerUtils.indexToOrdinalResource(i);
				RDFNode target = dataSource.getTarget(resource, arc, true);
				if(target == null) {
					break;
				}
				elementsList.add(target);
			}
			needsRefresh = false;
		}
		return elementsList;
	}

	/**
	 * Return the number of elements in the container. Note that this
	 * may not always be accurate due to aggregation.
	 */
	public long getCount() {
		return getElementsList().size();
	}

	/**
	 * Return an Iterator that can be used to enumerate the contents
	 * of the container in ascending order.
	 */
	public Iterator<RDFNode> getElements() {
		return getElementsList().iterator();
	}

	/**
	 * Append an element to the container, assigning it the next
	 * available ordinal.
	 */
	public void appendElement(RDFNode element) {
		long highest = getCount();
		RDFResource arc = rdfContainerUtils.indexToOrdinalResource(++highest);
		dataSource.doAssert(resource, arc, element, true);
	}

	/**
	 * Remove the first occurence of the specified element from the
	 * container. If renumber is 'true', then the underlying RDF graph
	 * will be 're-numbered' to account for the removal.
	 */
	public void removeElement(RDFNode element, boolean renumber) {
		removeElementAt(indexOf(element), renumber);
	}

	/**
	 * Insert element at the specified index. If renumber is 'true', then
	 * the underlying RDF graph will be 're-numbered' to accomodate the new
	 * element.
	 */
	public void insertElementAt(RDFNode element, long index, boolean renumber) {
		dataSource.beginUpdateBatch();
		if(renumber) {
			List<RDFNode> elements = getElementsList();
			// Shift all ordinal properties up one from the insertion point
			for(long i = index; i <= elements.size(); i++) {
				RDFNode elt = elements.get((int)i - 1);
				dataSource.unassert(resource, rdfContainerUtils.indexToOrdinalResource(i), elt);
				dataSource.doAssert(resource, rdfContainerUtils.indexToOrdinalResource(i + 1), elt, true);
			}
		}
		dataSource.doAssert(resource, rdfContainerUtils.indexToOrdinalResource(index), element, true);
		dataSource.endUpdateBatch();
	}

	/**
	 * Remove the element at the specified index. If renumber is 'true', then
	 * the underlying RDF graph will be 're-numbered' to account for the
	 * removal.
	 *
	 * @return the element that was removed.
	 */
	public RDFNode removeElementAt(long index, boolean renumber) {
		dataSource.beginUpdateBatch();
		List<RDFNode> elements = getElementsList();
		RDFNode elementToRemove =  elements.get((int)index - 1);
		dataSource.unassert(resource, rdfContainerUtils.indexToOrdinalResource(index), elementToRemove);
		if(renumber) {
			// Shift all ordinal properties down one from the insertion point
			for(long i = index; i < elements.size(); i++) {
				RDFNode elt = elements.get((int)i);
				dataSource.unassert(resource, rdfContainerUtils.indexToOrdinalResource(i + 1), elt);
				dataSource.doAssert(resource, rdfContainerUtils.indexToOrdinalResource(i), elt, true);
			}
		}
		dataSource.endUpdateBatch();
		return elementToRemove;
	}

	/**
	 * Determine the index of an element in the container.
	 *
	 * @return The index of the specified element in the container. If
	 *         the element is not contained in the container, this function
	 *         returns '-1'.
	 */
	public long indexOf(RDFNode element) {
		int index = getElementsList().indexOf(element);
		return(index == -1 ? -1 : index + 1);
	}


}
