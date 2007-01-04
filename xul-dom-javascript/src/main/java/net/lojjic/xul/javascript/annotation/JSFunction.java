package net.lojjic.xul.javascript.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation denotes a method that should be treated as a
 * JavaScript function object within the scripting environment.
 * Its value is the name of the JavaScript function.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSFunction {

	/**
	 * The name of the JavaScript function
	 */
	String value();
}
