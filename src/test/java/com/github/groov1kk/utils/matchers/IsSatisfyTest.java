package com.github.groov1kk.utils.matchers;

import static com.github.groov1kk.utils.matchers.IsSatisfy.satisfy;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class IsSatisfyTest extends BaseMatchersTest {

  @Test
  void testShouldBeSatisfied() {
    assertMatches(satisfy((String it) -> !it.isEmpty()), "test");
  }

  @Test
  void testShouldNotBeSatisfied() {
    Matcher<String> matcher = satisfy(String::isEmpty);
    String argument = "test";
    assertDoesNotMatch(matcher, argument);
    assertDescription("condition has not been satisfied", matcher, argument);
  }

  @Test
  void testShouldNotBeSatisfiedWithDescription() {
    Matcher<String> matcher = satisfy(String::isEmpty, "String condition");
    String argument = "test";
    assertDoesNotMatch(matcher, argument);
    assertDescription("String condition has not been satisfied", matcher, argument);
  }
}
