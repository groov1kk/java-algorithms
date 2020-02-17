package com.github.groov1kk;

public final class Checker {

  private Checker() {}

  /**
   * Checks that the specified integer {@code value} is non-negative. Otherwise this method throws
   * {@link IllegalArgumentException}.
   *
   * <p>This method is designed primarily for doing parameter validation in methods and constructors
   * with multiple parameters.
   *
   * @param value Integer value to check
   * @return {@code value} if it is non-negative
   * @throws IllegalArgumentException if {@code value} is negative
   */
  public static int requireNonNegative(int value) {
    failIfNegative(value, String.format("Value <%d> must not be negative", value));
    return value;
  }

  /**
   * Checks that the specified integer {@code value} is non-negative. Otherwise this method throws
   * {@link IllegalArgumentException} with custom exception {@code message}.
   *
   * <p>This method is designed primarily for doing parameter validation in methods and constructors
   * with multiple parameters.
   *
   * @param value Integer value to check
   * @param message Error message that will be thrown if {@code value} is negative
   * @return {@code value} if it is non-negative
   * @throws IllegalArgumentException if {@code value} is negative
   */
  public static int requireNonNegative(int value, String message) {
    failIfNegative(value, message);
    return value;
  }

  /**
   * Checks whether the given {@code value} is negative.
   *
   * @param value Value to check
   * @param <T> Value type
   * @return Is value negative
   */
  public static <T extends Number> boolean isNegative(T value) {
    boolean isNegative;
    if (value instanceof Long) {
      isNegative = value.longValue() < 0;
    } else if (value instanceof Float) {
      isNegative = value.floatValue() < 0;
    } else if (value instanceof Double) {
      isNegative = value.doubleValue() < 0;
    } else {
      isNegative = value.intValue() < 0;
    }
    return isNegative;
  }

  private static <T extends Number> void failIfNegative(T value, String message) {
    if (isNegative(value)) {
      throw new IllegalArgumentException(message);
    }
  }
}
