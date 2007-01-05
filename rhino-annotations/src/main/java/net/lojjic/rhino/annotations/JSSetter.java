package net.lojjic.rhino.annotations;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation specifies a Java method as a JavaScript property
 * setter. Its value is the name of the JavaScript property for which
 * the method will be called on assignment.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSSetter {

	/**
	 * The name of the JavaScript property
	 */
	String value();

}