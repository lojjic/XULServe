package net.lojjic.xul.templates.impl.conditions;

import org.w3c.dom.Element;

/**
 * A {@link Where} implementation that requires the subject to be less than the value numerically.
 * <code>&lt;where rel="less" /></code>
 */
public class WhereLess extends Where {

	/**
	 * Constructor
	 *
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	public WhereLess(Element element) {
		super(element);
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * This implementation tests that the subject is less than the value numerically.
	 */
	protected boolean matches(String subject, String value) {
		try {
			int subjectInt = Integer.parseInt(subject);
			int valueInt = Integer.parseInt(value);
			boolean isLess = subjectInt < valueInt;
			return isNegate() ? !isLess : isLess;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
