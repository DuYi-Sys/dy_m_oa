/**
 * 
 */
package com.duyi.commons.page;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.duyi.commons.page.Sort.Direction;

/**
 * Annotation to define the default {@link Sort} options to be used when injecting a {@link Sort} instance into a
 * controller handler method.
 * 
 * @since 1.6
 * @author Oliver Gierke
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SortDefault {

	/**
	 * Alias for {@link #sort()} to make a declaration configuring fields only more concise.
	 * 
	 * @return
	 */
	String[] value() default {};

	/**
	 * The properties to sort by by default. If unset, no sorting will be applied at all.
	 * 
	 * @return
	 */
	String[] sort() default {};

	/**
	 * The direction to sort by. Defaults to {@link Direction#ASC}.
	 * 
	 * @return
	 */
	Direction direction() default Direction.ASC;

	/**
	 * Wrapper annotation to allow declaring multiple {@link SortDefault} annotations on a method parameter.
	 * 
	 * @since 1.6
	 * @author Oliver Gierke
	 */
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	public @interface SortDefaults {

		/**
		 * The individual {@link SortDefault} declarations to be sorted by.
		 * 
		 * @return
		 */
		SortDefault[] value();
	}
}
