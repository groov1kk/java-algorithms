package com.github.groov1kk.matchers;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.util.Comparator;

import javax.annotation.Nullable;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsIntArraySorted extends TypeSafeDiagnosingMatcher<int[]> {

  private final Comparator<Integer> comparator;
  private final String comparatorDescription;

  public IsIntArraySorted(Comparator<Integer> comparator, @Nullable String comparatorDescription) {
    this.comparator = requireNonNull(comparator);
    this.comparatorDescription = requireNonNullElse(comparatorDescription, "the given comparator");
  }

  @Override
  protected boolean matchesSafely(int[] array, Description mismatchDescription) {
    for (int i = 0; i < array.length - 1; i++) {
      int current = array[i];
      int next = array[i + 1];
      if (comparator.compare(current, next) > 0) {
        mismatchDescription
            .appendText("Element ")
            .appendValue(current)
            .appendText(" with index ")
            .appendValue(i)
            .appendText(" is not sorted according to ")
            .appendText(comparatorDescription);
        return false;
      }
    }
    return true;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Array must be sorted using the ").appendText(comparatorDescription);
  }

  /**
   * Creates a matcher for integer arrays that matches when each item in the examined array is
   * sorted according to the given comparator. For a positive match, each current item must not
   * violate comparison with the next element using {@code comparator.compare(current, next) > 0}
   * expression.
   *
   * @param comparator array elements comparator
   * @param comparatorDescription text description of the comparator which would be used to describe
   *     matcher and its mismatches
   * @return int array order matcher
   */
  public static Matcher<int[]> sorted(
      Comparator<Integer> comparator, @Nullable String comparatorDescription) {
    return new IsIntArraySorted(comparator, comparatorDescription);
  }

  /**
   * Creates a matcher for integer arrays that matches when each item in the examined array is
   * sorted according to the given comparator. For a positive match, each current item must not
   * violate comparison with the next element using {@code comparator.compare(current, next) > 0}
   * expression.
   *
   * @param comparator array elements comparator
   * @return int array order matcher
   */
  public static Matcher<int[]> sorted(Comparator<Integer> comparator) {
    return sorted(comparator, null);
  }

  /**
   * Creates a matcher for integer arrays that matches when all array elements are sorted in
   * ascending order. For a positive match, each current item must not be greater than the next
   * element.
   *
   * @return int array order matcher
   */
  public static Matcher<int[]> naturalOrder() {
    return sorted(Comparator.naturalOrder(), "natural order");
  }

  /**
   * Creates a matcher for integer arrays that matches when all array elements are sorted in
   * descending order. For a positive match, each current item must not be lesser that the next
   * element.
   *
   * @return int array order matcher
   */
  public static Matcher<int[]> reverseOrder() {
    return sorted(Comparator.reverseOrder(), "reverse order");
  }
}
