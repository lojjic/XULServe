package net.lojjic.xul;

import org.w3c.dom.Node;

import java.util.Iterator;

/**
 * A query processor takes a template query and generates results for it given
 * a datasource and a reference point. There is a one-to-one relationship
 * between a template builder and a query processor. The template builder
 * creates the query processor, and there is no other means to retrieve it.
 * <p/>
 * A template query is the contents inside a &lt;query> element within the
 * template. The actual syntax is opaque to the template builder and defined
 * by a query processor. The query is expected to consist of either text or
 * DOM nodes that, when executed by a call to the generateResults method, will
 * allow the generation of a list of results.
 * <p/>
 * The template builder will supply two variables, the reference variable and
 * the member variable to further indicate what part of the datasource is to
 * be examined in addition to the query itself. The reference is always
 * a placeholder for the starting point and the member is always a placeholder
 * for the end points (the results).
 * <p/>
 * The reference point is important when generating output recursively, as
 * the query will be the same for each iteration, however, the reference point
 * will differ.
 * <p/>
 * For instance, when examining an XML source, an XML query processor might
 * begin at the node referred by the reference variable and end at a list of
 * that node's children.
 * <p/>
 * Some queries may not need the reference variable if the syntax or the form
 * of the data implies the value. For instance, a datasource that holds a
 * table that can only produce one set of results.
 * <p/>
 * The reference variable may be specified in a template by setting the
 * "container" attribute on the &lt;template> element to the variable to use. The
 * member variable may be specified in a similar way using the "member"
 * attribute, or it may be specified in the first &lt;action> body in the
 * template as the value of a uri attribute on an element. A breadth-first
 * search of the first action is performed to find this element.
 * <p/>
 * If unspecified, the default value of the reference variable is ?uri.
 * <p/>
 * For example, a query might have the following syntax:
 * <p/>
 * (?id, ?name, ?url) from Bookmarks where parentfolder = ?start
 * <p/>
 * This query might generate a result for each bookmark within a given folder.
 * The variable ?start would be the reference variable, while the variable ?id
 * would be the member variable, since it is the unique value that identifies
 * a result. Each result will have the four variables referred to defined for
 * it and the values may be retrieved using the result's getBindingFor and
 * getBindingObjectFor methods.
 * <p/>
 * The template builder must call initializeForBuilding before the other
 * methods, except for translateRef. The builder will then call compileQuery
 * for each query in the template to compile the queries. When results need
 * to be generated, the builder will call generateResults. The
 * initializeForBuilding, compileQuery and addBinding methods may not be
 * called after generateResults has been called until the builder indicates
 * that the generated output is being removed by calling the done method.
 * <p/>
 * Currently, the datasource supplied to the methods will always be an
 * nsIRDFDataSource or a DOM node, and will always be the same one in between
 * calls to initializeForBuilding and done.
 */
public interface XULTemplateQueryProcessor {

	/**
	 * Initialize for query generation. This will be called before the rules are
	 * processed and whenever the template is rebuilt. This method must be
	 * called once before any of the other query processor methods except for
	 * translateRef.
	 *
	 * @param aDatasource datasource for the data
	 * @param aBuilder    the template builder
	 * @param aRootNode   the root node the builder is attached to
	 * @throws IllegalArgumentException if the datasource is not supported
	 * @throws IllegalStateException    if generateResults has already been called.
	 */
	void initializeForBuilding(Object aDatasource,
	                           XULTemplateBuilder aBuilder,
	                           Node aRootNode)
			throws IllegalArgumentException, IllegalStateException;

	/**
	 * Called when the template builder is being destroyed so that the query
	 * processor can clean up any state. The query processor should remove as
	 * much state as possible, such as results or references to the builder.
	 * This method will also be called when the template is going to be rebuilt.
	 */
	void done();

	/**
	 * Compile a query from a node. The result of this function will later be
	 * passed to generateResults for result generation. If null is returned,
	 * the query will be ignored.
	 * <p/>
	 * The template builder will call this method once for each query within
	 * the template, before any results can be generated using generateResults,
	 * but after initializeForBuilding has been called. This method should not
	 * be called again for the same query unless the template is rebuilt.
	 * <p/>
	 * The reference variable may be used by the query processor as a
	 * placeholder for the reference point, or starting point in the query.
	 * <p/>
	 * The member variable is determined from the member attribute on the
	 * template, or from the uri in the first action's rule if that attribute is
	 * not present. A rule processor may use the member variable as a hint to
	 * indicate what variable is expected to contain the results.
	 *
	 * @param aBuilder        the template builder
	 * @param aQuery          <query> node to compile
	 * @param aRefVariable    the reference variable
	 * @param aMemberVariable the member variable
	 * @returns a compiled query object
	 */
	Object compileQuery(XULTemplateBuilder aBuilder,
	                    Node aQuery,
	                    String aRefVariable,
	                    String aMemberVariable);

	/**
	 * Generate the results of a query and return them in an enumerator. The
	 * enumerator must contain nsIXULTemplateResult objects. If there are no
	 * results, an empty enumerator must be returned.
	 * <p/>
	 * The datasource will be the same as the one passed to the earlier
	 * initializeForBuilding method. The context reference (aRef) is a reference
	 * point used when calculating results.
	 * <p/>
	 * The value of aQuery must be the result of a previous call to compileQuery
	 * from this query processor. This method may be called multiple times,
	 * typically with different values for aRef.
	 *
	 * @param aDatasource datasource for the data
	 * @param aRef        context reference value used as a starting point
	 * @param aQuery      the compiled query returned from query compilation
	 * @throws IllegalArgumentException if aQuery is invalid
	 * @returns an enumerator of nsIXULTemplateResult objects as the results
	 */
	Iterator generateResults(Object aDatasource,
	                         XULTemplateResult aRef,
	                         Object aQuery)
			throws IllegalArgumentException;

	/**
	 * Add a variable binding for a particular rule. A binding allows an
	 * additional variable to be set for a result, outside of those defined
	 * within the query. These bindings are always optional, in that they will
	 * never affect the results generated.
	 * <p/>
	 * This function will never be called after generateResults. Any bindings
	 * that were added should be applied to each result when the result's
	 * ruleMatched method is called, since the bindings are different for each
	 * rule.
	 * <p/>
	 * The reference aRef may be used to determine the reference when
	 * calculating the value for the binding, for example when a value should
	 * depend on the value of another variable.
	 * <p/>
	 * The syntax of the expression aExpr is defined by the query processor. If
	 * the syntax is invalid, the binding should be ignored. Only fatal errors
	 * should be thrown, or NS_ERROR_UNEXPECTED if generateResults has already
	 * been called.
	 * <p/>
	 * As an example, if the reference aRef is the variable '?count' which
	 * holds the value 5, and the expression aExpr is the string '+2', the value
	 * of the variable aVar would be 7, assuming the query processor considers
	 * the syntax '+2' to mean add two to the reference.
	 *
	 * @param aRuleNode rule to add the binding to
	 * @param aVar      variable that will be bound
	 * @param aRef      variable that holds reference value
	 * @param aExpr     expression used to compute the value to assign
	 */
	void addBinding(Node aRuleNode,
	                String aVar,
	                String aRef,
	                String aExpr);

	/**
	 * Translate a ref attribute string into a result. This is used as the
	 * reference point by the template builder when generating the first level
	 * of content. For recursive generation, the result from the parent
	 * generation phase will be used directly as the reference so a translation
	 * is not needed. This allows all levels to be generated using objects that
	 * all implement the nsIXULTemplateResult interface.
	 * <p/>
	 * This method may be called before initializeForBuilding, so the
	 * implementation may use the supplied datasource if it is needed to
	 * translate the reference.
	 *
	 * @param aDatasource datasource for the data
	 * @param aRefString  the ref attribute string
	 * @return the translated ref
	 */
	XULTemplateResult translateRef(Object aDatasource,
	                               String aRefString);

	/**
	 * Compare two results to determine their order, used when sorting results.
	 * This method should return -1 when the left result is less than the right,
	 * 0 if both are equivalent, and 1 if the left is greater than the right.
	 * The comparison should only consider the values for the specified
	 * variable.
	 * <p/>
	 * If the comparison variable is null, the results may be
	 * sorted in a natural order, for instance, based on the order the data in
	 * stored in the datasource.
	 * <p/>
	 * This method must only be called with results that were created by this
	 * query processor.
	 *
	 * @param aLeft  the left result to compare
	 * @param aRight the right result to compare
	 * @param aVar   variable to compare
	 * @return -1 if less, 0 if equal, or 1 if greater
	 */
	int compareResults(XULTemplateResult aLeft,
	                   XULTemplateResult aRight,
	                   String aVar);

}
