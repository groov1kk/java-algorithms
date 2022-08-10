package com.github.groov1kk.utils;

import static com.github.groov1kk.utils.Checker.requireNonNegative;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CheckerTest {

  @Test
  void testRequireNonNegative() {
    int value = -5;

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> requireNonNegative(value));

    assertThat(
        exception.getMessage(), is(equalTo(format("Value <%d> must not be negative", value))));
  }

  @Test
  void testRequireNonNegativeWithMessage() {
    int value = -5;
    String message = "Test message";

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> requireNonNegative(value, message));

    assertThat(exception.getMessage(), is(equalTo(message)));
  }
}
