package com.github.groov1kk.utils.matchers;

import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

public class IsIntArrayContainingInAnyOrder extends TypeSafeMatcher<int[]> {

  private final IsIterableContainingInAnyOrder<Integer> iterableMatcher;
  private final Collection<Matcher<? super Integer>> matchers;

  public IsIntArrayContainingInAnyOrder(Collection<Matcher<? super Integer>> matchers) {
    this.iterableMatcher = new IsIterableContainingInAnyOrder<>(matchers);
    this.matchers = matchers;
  }

  @Override
  protected boolean matchesSafely(int[] item) {
    return iterableMatcher.matches(asList(item));
  }

  @Override
  protected void describeMismatchSafely(int[] item, Description mismatchDescription) {
    iterableMatcher.describeMismatch(asList(item), mismatchDescription);
  }

  @Override
  public void describeTo(Description description) {
    description.appendList("[", ", ", "]", matchers).appendText(" in any order");
  }

  private static List<Integer> asList(int[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
  }

  /**
   * Creates an order agnostic matcher for int arrays that matches when each item in the examined
   * array is logically equal to one item anywhere in the specified items. For a positive match, the
   * examined array must be of the same length as the number of specified items. N.B. each of the
   * specified items will only be used once during a given examination, so be careful when
   * specifying items that may be equal to more than one entry in an examined array.
   *
   * @param items The array that must equal the entries of an examined array, in any order
   * @return Int array matcher
   */
  public static Matcher<int[]> intArrayContainingInAnyOrder(int... items) {
    List<Matcher<? super Integer>> matchers = new ArrayList<>();
    for (int item : items) {
      matchers.add(equalTo(item));
    }
    return new IsIntArrayContainingInAnyOrder(matchers);
  }
}
