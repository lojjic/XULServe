package net.lojjic.xul.templates.impl.conditions;

import org.apache.xml.utils.UnImplNode;

/**
 * Utility class for building a template rule condition &lt;where/> element,
 * which exposes just those {@link org.w3c.dom.Element} methods needed for our tests.
 * This gives us an easy way to create a test &lt;where/> Element that can be used
 * as if it were parsed from XML, without actually having to deal with XML parsing.
 */
public class MockWhereElement extends UnImplNode {

	private String subject;
	private String rel;
	private String value;
	private boolean negate;
	private boolean multiple;
	private boolean ignoreCase;

	/**
	 * Constructor
	 */
	public MockWhereElement(String rel, String subject, String value, boolean negate, boolean multiple, boolean ignoreCase) {
		this.rel = rel;
		this.subject = subject;
		this.value = value;
		this.negate = negate;
		this.multiple = multiple;
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Expose the private members as attributes
	 */
	@Override
	public String getAttribute(String name) {
		if(Where.REL_ATTR.equals(name)) return rel;
		if(Where.SUBJECT_ATTR.equals(name)) return subject;
		if(Where.VALUE_ATTR.equals(name)) return value;
		if(Where.NEGATE_ATTR.equals(name)) return String.valueOf(negate);
		if(Where.MULTIPLE_ATTR.equals(name)) return String.valueOf(multiple);
		if(Where.IGNORECASE_ATTR.equals(name)) return String.valueOf(ignoreCase);
		return null;
	}
}
