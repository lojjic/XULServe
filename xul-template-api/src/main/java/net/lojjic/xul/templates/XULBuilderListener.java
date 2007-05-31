package net.lojjic.xul.templates;

/**
 * XUL template builder listener interface.
 * See http://www.xulplanet.com/references/xpcomref/ifaces/nsIXULBuilderListener.html
 */
public interface XULBuilderListener {

	/**
	 * Called after a template builder has rebuilt its content.
	 *
	 * @param builder the template builder that has rebuilt the content.
	 */
	void didRebuild(XULTemplateBuilder builder);

	/**
	 * Called before a template builder rebuilds its content.
	 *
	 * @param builder the template builder that rebuilds the content.
	 */
	void willRebuild(XULTemplateBuilder builder);

}
