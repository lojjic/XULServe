package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to end with the value.
 * <code>&lt;where rel="endswith" /></code>
 */
public class WhereEndsWith extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	public WhereEndsWith(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject starts with the value.
	 */
	protected boolean matches(String subject, String value) {
		boolean endsWith = isIgnoreCase() ?
				subject.toLowerCase().endsWith(value.toLowerCase()) :
				subject.endsWith(value);
		return isNegate() ? !endsWith : endsWith;
	}
}
