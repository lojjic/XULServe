package net.lojjic.xul.templates.impl.conditions;

import net.lojjic.xul.templates.XULTemplateResult;
import org.w3c.dom.Element;

import java.util.HashMap;

/**
 * Base class for &lt;where/> template rule conditions
 */
public abstract class Where {

	public static final String REL_ATTR = "rel";
	public static final String SUBJECT_ATTR = "subject";
	public static final String VALUE_ATTR = "value";
	public static final String NEGATE_ATTR = "negate";
	public static final String IGNORECASE_ATTR = "ignorecase";
	public static final String MULTIPLE_ATTR = "multiple";

	private static HashMap<String, Class<? extends Where>> relMapping = new HashMap<String, Class<? extends Where>>();
	static {
		relMapping.put("after", WhereAfter.class);
		relMapping.put("before", WhereBefore.class);
		relMapping.put("contains", WhereContains.class);
		relMapping.put("endswith", WhereEndsWith.class);
		relMapping.put("equals", WhereEquals.class);
		relMapping.put("greater", WhereGreater.class);
		relMapping.put("less", WhereLess.class);
		relMapping.put("startswith", WhereStartsWith.class);
	}

	private String subject;
	private String value;
	private boolean negate;
	private boolean ignoreCase;
	private boolean multiple;

	/**
	 * Constructor - this should never be called directly by external classes; they should
	 * use the {@link #newInstance(org.w3c.dom.Element)} factory method.
	 * @param element - the &lt;where/> element from which this condition is being built
	 */
	public Where(Element element) {
		this.subject = element.getAttribute(SUBJECT_ATTR);
		this.value = element.getAttribute(VALUE_ATTR);
		this.negate = Boolean.parseBoolean(element.getAttribute(NEGATE_ATTR));
		this.ignoreCase = Boolean.parseBoolean(element.getAttribute(IGNORECASE_ATTR));
		this.multiple = Boolean.parseBoolean(element.getAttribute(MULTIPLE_ATTR));
	}

	/**
	 * Factory method to build a Where instance of the appropriate type for the
	 * given Element based on its rel attribute.
	 */
	public static Where newInstance(Element element) {
		String rel = element.getAttribute(REL_ATTR);
		Class<? extends Where> cls = relMapping.get(rel);
		if(cls == null) {
			throw new IllegalArgumentException("The rel attribute was either null or not recognized.");
		}
		try {
			return cls.getConstructor(Element.class).newInstance(element);
		}
		catch (Exception e) {
			throw new RuntimeException("Could not instantiate class " + cls.getName(), e);
		}
	}

	/**
	 * Return the value of the subject attribute. This may either be a variable name
	 * (including the question mark, e.g. "?name") or a literal string value.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Return the value of the value attribute. This may either be a variable name
	 * (including the question mark, e.g. "?name") or a literal string value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Whether the multiple="true" attribute is set
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * Whether the ignorecase="true" attribute is set
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * Whether the negate="true" attribute is set
	 */
	public boolean isNegate() {
		return negate;
	}

	/**
	 * Apply the where-condition to a given {@link XULTemplateResult} to determine if it
	 * satisfies the condition.
	 *
	 * @param result - the {@link XULTemplateResult} to which the where-condition is being tested
	 */
	public boolean matches(XULTemplateResult result) {

		// Get the subject(s) and value(s):
		String[] subjects;
		if(isMultiple()) {
			subjects = getSubject().split(",");
		} else {
			subjects = new String[] {getSubject()};
		}
		String[] values;
		if(isMultiple()) {
			values = getValue().split(",");
		} else {
			values = new String[] {getValue()};
		}

		// replace variable references with their values from the XULTemplateResult:
		for(int i=0; i<subjects.length; i++) {
			if(subjects[i].startsWith("?")) {
				subjects[i] = result.getBindingFor(subjects[i]);
			}
		}
		for(int i=0; i<values.length; i++) {
			if(values[i].startsWith("?")) {
				values[i] = result.getBindingFor(values[i]);
			}
		}

		// delegate comparison of each subject-value pair to the subclass:
		for(String subject : subjects) {
			for(String value : values) {
				if(matches(subject, value)) return true;
			}
		}
		return false;
	}

	/**
	 * Apply the where-condition to a single subject-value pair.  This may be called one
	 * or more times by {@link #matches(net.lojjic.xul.templates.XULTemplateResult)}.
	 * Subclasses will implement the exact comparison logic.
	 */
	protected abstract boolean matches(String subject, String value);

}
