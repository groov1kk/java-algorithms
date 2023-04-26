package com.github.groov1kk.utils.matchers;

import static com.github.groov1kk.utils.Utils.intArray;
import static com.github.groov1kk.utils.matchers.IsIntArrayContainingInAnyOrder.intArrayContainingInAnyOrder;

import java.util.stream.Stream;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IsIntArrayContainingAnyOrderTest extends BaseMatchersTest {

  @Test
  void testIntArrayShouldMatchInAnyOrder() {
    assertMatches(intArrayContainingInAnyOrder(1, 2, 3), intArray(3, 1, 2));
  }

  @ParameterizedTest
  @MethodSource("data")
  void testIntArrayShouldNotMatchInAnyOrder(Matcher<int[]> matcher, int[] arg, String description) {
    assertDoesNotMatch(matcher, arg);
    assertDescription(description, matcher, arg);
  }

  static Stream<Arguments> data() {
    return Stream.of(
        Arguments.of(
            intArrayContainingInAnyOrder(1, 2, 3),
            intArray(1, 2),
            "no item matches: <3> in [<1>, <2>]"),
        Arguments.of(
            intArrayContainingInAnyOrder(1, 2, 3), intArray(1, 2, 3, 4), "no match for: <4>"));
  }
}
