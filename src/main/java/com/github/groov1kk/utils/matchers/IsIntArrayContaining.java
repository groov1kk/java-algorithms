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
import org.hamcrest.collection.IsIterableContainingInOrder;

public class IsIntArrayContaining extends TypeSafeMatcher<int[]> {

  private final IsIterableContainingInOrder<Integer> iterableMatcher;
  private final Collection<Matcher<? super Integer>> matchers;

  public IsIntArrayContaining(List<Matcher<? super Integer>> matchers) {
    this.iterableMatcher = new IsIterableContainingInOrder<>(matchers);
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
    description.appendList("[", ", ", "]", matchers);
  }

  private static List<Integer> asList(int[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
  }

  /**
   * Creates a matcher for arrays that matches when each item in the examined int array satisfies
   * the corresponding matcher in the specified matchers. For a positive match, the examined array
   * must be of the same length as the number of specified matchers. For example:
   *
   * <pre>assertThat(new int[] {1, 2}, intArrayContaining(1, 2))</pre>
   *
   * @param items The array that must equal the entries of an examined array
   * @return Int array matcher
   */
  public static Matcher<int[]> intArrayContaining(int... items) {
    List<Matcher<? super Integer>> matchers = new ArrayList<>();
    for (int item : items) {
      matchers.add(equalTo(item));
    }
    return new IsIntArrayContaining(matchers);
  }
}
