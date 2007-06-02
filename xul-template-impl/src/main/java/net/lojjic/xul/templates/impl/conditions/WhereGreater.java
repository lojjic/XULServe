package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to be greater than the value numerically.
 * <code>&lt;where rel="greater" /></code>
 */
public class WhereGreater extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	protected WhereGreater(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject is greater than the value numerically.
	 */
	protected boolean matches(String subject, String value) {
		try {
			int subjectInt = Integer.parseInt(subject);
			int valueInt = Integer.parseInt(value);
			boolean isGreater = subjectInt > valueInt;
			return isNegate() ? !isGreater : isGreater;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
