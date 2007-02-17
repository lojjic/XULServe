package net.lojjic.rhino.annotations;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This marker annotation can be used along with {@link JSFunction},
 * {@link JSGetter}, or {@link JSSetter} to indicate the corresponding
 * JavaScript member should be made a static property of the JavaScript
 * class.  In other words it is a property of the constructor function
 * object rather than the prototype.  It can only be applied to static
 * methods.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSStatic {
}
