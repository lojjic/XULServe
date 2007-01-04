package net.lojjic.xul.javascript.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation specifies the JavaScript class ("constructor function")
 * name for a Java class. When the Java class is wrapped into the JavaScript
 * environment, scripts will be able to create instances with this name.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSClassName {

	/**
	 * The name of the JavaScript class / constructor function
	 */
	String value();

}
