package net.lojjic.xul.javascript.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation specifies a Java method as a JavaScript property
 * getter. Its value is the name of the JavaScript property for which
 * the method will be called on access.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSGetter {

	/**
	 * The name of the JavaScript property.
	 */
	String value();

}
