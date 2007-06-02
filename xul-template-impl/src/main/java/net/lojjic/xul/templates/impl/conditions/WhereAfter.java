package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to come after the value alphabetically.
 * <code>&lt;where rel="after" /></code>
 */
public class WhereAfter extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	protected WhereAfter(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject comes after the value alphabetically.
	 */
	protected boolean matches(String subject, String value) {
		boolean isAfter = isIgnoreCase() ? subject.compareToIgnoreCase(value) > 0 : subject.compareTo(value) > 0;
		return isNegate() ? !isAfter : isAfter;
	}
}
