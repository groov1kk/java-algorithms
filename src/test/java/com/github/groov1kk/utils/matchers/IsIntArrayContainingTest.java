package com.github.groov1kk.utils.matchers;

import static com.github.groov1kk.utils.Utils.intArray;
import static com.github.groov1kk.utils.matchers.IsIntArrayContaining.intArrayContaining;

import java.util.stream.Stream;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IsIntArrayContainingTest extends BaseMatchersTest {

  @Test
  void testIntArrayShouldMatch() {
    assertMatches(intArrayContaining(1, 2, 3), intArray(1, 2, 3));
  }

  @ParameterizedTest
  @MethodSource("data")
  void testIntArrayShouldNotMatch(Matcher<int[]> matcher, int[] arg, String description) {
    assertDoesNotMatch(matcher, arg);
    assertDescription(description, matcher, arg);
  }

  static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(intArrayContaining(1, 2, 3), intArray(1, 2), "no item was <3>"),
        Arguments.of(intArrayContaining(1, 2, 3), intArray(1, 2, 3, 4), "not matched: <4>"),
        Arguments.of(intArrayContaining(1, 2, 3), intArray(3, 1, 2), "item 0: was <3>"));
  }
}
