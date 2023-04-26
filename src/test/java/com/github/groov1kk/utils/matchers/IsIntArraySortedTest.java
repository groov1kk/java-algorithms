package com.github.groov1kk.utils.matchers;

import static com.github.groov1kk.utils.Utils.intArray;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.naturalOrder;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.reverseOrder;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.sorted;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class IsIntArraySortedTest extends BaseMatchersTest {

  @Test
  void testIntArrayShouldBeSorted() {
    assertMatches(sorted(Integer::compareTo), intArray(1, 2, 3));
  }

  @Test
  void testIntArrayShouldBeSortedInNaturalOrder() {
    assertMatches(naturalOrder(), intArray(1, 2, 3));
  }

  @Test
  void testIntArrayShouldBeSortedInReverseOrder() {
    assertMatches(reverseOrder(), intArray(3, 2, 1));
  }

  @Test
  void testIntArrayShouldNotBeSorted() {
    Matcher<int[]> matcher = sorted(Integer::compareTo);
    int[] array = {3, 2, 1};
    assertDoesNotMatch(matcher, array);
    assertDescription(
        "Element <3> with index <0> is not sorted according to the given comparator",
        matcher,
        array);
  }

  @Test
  void testIntArrayShouldNotBeSortedWithDescription() {
    Matcher<int[]> matcher = sorted(Integer::compareTo, "Int comparator");
    int[] array = {3, 2, 1};
    assertDoesNotMatch(matcher, array);
    assertDescription(
        "Element <3> with index <0> is not sorted according to Int comparator", matcher, array);
  }

  @Test
  void testIntArrayShouldNotBeSortedInNaturalOrder() {
    Matcher<int[]> matcher = naturalOrder();
    int[] array = {3, 2, 1};
    assertDoesNotMatch(matcher, array);
    assertDescription(
        "Element <3> with index <0> is not sorted according to natural order", matcher, array);
  }

  @Test
  void testIntArrayShouldNotBeSortedInReverseOrder() {
    Matcher<int[]> matcher = reverseOrder();
    int[] array = {1, 2, 3};
    assertDoesNotMatch(matcher, array);
    assertDescription(
        "Element <1> with index <0> is not sorted according to reverse order", matcher, array);
  }
}
