package com.github.groov1kk.utils.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public abstract class BaseMatchersTest {

  static void assertMatches(Matcher<?> matcher, Object arg) {
    assertMatches("Expected match, but it didn't", matcher, arg);
  }

  static void assertMatches(String message, Matcher<?> matcher, Object arg) {
    assertTrue(matcher.matches(arg), message + " because: " + describeMismatch(matcher, arg));
  }

  static void assertDoesNotMatch(Matcher<?> matcher, Object arg) {
    assertDoesNotMatch("Unexpected match", matcher, arg);
  }

  static void assertDoesNotMatch(String message, Matcher<?> matcher, Object arg) {
    assertFalse(matcher.matches(arg), message + " because: " + describeMismatch(matcher, arg));
  }

  static void assertDescription(String description, Matcher<?> matcher, Object arg) {
    assertEquals(description, describeMismatch(matcher, arg));
  }

  private static String describeMismatch(Matcher<?> matcher, Object arg) {
    Description description = new StringDescription();
    matcher.describeMismatch(arg, description);
    return description.toString().trim();
  }
}
