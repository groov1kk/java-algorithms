package com.github.groov1kk.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that the array which marked by this annotation has to be generated and injected using
 * the {@link RandomArrayExtension}. Also, this annotation provides to adjust the array before
 * injecting it.
 *
 * @see RandomArrayExtension
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomArray {

  /**
   * Array length. Must be positive.
   *
   * @return array length
   */
  int length() default 20;

  /**
   * Lower bound (inclusive) of values of the array.
   *
   * @return lower bound
   */
  int from() default -50;

  /**
   * Upper bound (exclusive) of values of the array.
   *
   * @return upper bound
   */
  int to() default 50;

  /**
   * Indicates whether the array values must be distinct.
   *
   * @return are values distinct
   */
  boolean distinct() default false;
}
