package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that compares subject and value for equality.
 * <code>&lt;where rel="equals" /></code>
 */
public class WhereEquals extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	protected WhereEquals(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation does equality comparison.
	 */
	protected boolean matches(String subject, String value) {
		boolean isEqual = isIgnoreCase() ? subject.equalsIgnoreCase(value) : subject.equals(value);
		return isNegate() ? !isEqual : isEqual;
	}
}
