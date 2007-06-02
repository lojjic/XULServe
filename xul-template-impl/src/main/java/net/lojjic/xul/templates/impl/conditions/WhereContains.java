package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to contain the value.
 * <code>&lt;where rel="contains" /></code>
 */
public class WhereContains extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	protected WhereContains(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject contains the value.
	 */
	protected boolean matches(String subject, String value) {
		boolean contains = isIgnoreCase() ?
				subject.toLowerCase().contains(value.toLowerCase()) :
				subject.contains(value);
		return isNegate() ? !contains : contains;
	}
}
