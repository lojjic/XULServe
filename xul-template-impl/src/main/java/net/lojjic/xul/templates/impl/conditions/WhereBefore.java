package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to come before the value alphabetically.
 * <code>&lt;where rel="before" /></code>
 */
public class WhereBefore extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	public WhereBefore(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject comes before the value alphabetically.
	 */
	protected boolean matches(String subject, String value) {
		boolean isBefore = isIgnoreCase() ? subject.compareToIgnoreCase(value) < 0 : subject.compareTo(value) < 0;
		return isNegate() ? !isBefore : isBefore;
	}
}
