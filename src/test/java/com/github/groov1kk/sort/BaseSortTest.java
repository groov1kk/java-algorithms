package com.github.groov1kk.sort;

import com.github.groov1kk.BaseTest;
import com.github.groov1kk.Utils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;

import java.util.Arrays;
import java.util.Objects;

abstract class BaseSortTest extends BaseTest {

  /** Array of integer values to test. */
  int[] array;

  @Before
  public void init() {
    this.array = array(20, -50, 50);
  }

  /**
   * Returns an instance of {@link Matcher} that verifies whether a given array has ascending sort
   * order of its elements.
   *
   * @return matcher that verifies ascending order of elements of an array
   */
  Matcher<int[]> isSortedAsc() {
    return new BaseMatcher<>() {

      static final String DESCRIPTION = "Array has to be sorted in ascending order";
      static final String MISMATCH = "Array %s is not sorted in ascending order";

      @Override
      public boolean matches(Object item) {
        return Utils.isSortedAsc((int[]) item);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(DESCRIPTION);
      }

      @Override
      public void describeMismatch(Object item, Description description) {
        description.appendText(String.format(MISMATCH, Arrays.toString((int[]) item)));
      }
    };
  }

  /**
   * Verifies whether {@code expected} array has the same set of elements as given array has. The
   * order of elements does not matter.
   *
   * @param expected Expected array
   * @return Matcher that verifies whether two arrays have the same elements in any order
   */
  Matcher<int[]> hasSameItemsInAnyOrder(int[] expected) {
    return new BaseMatcher<>() {

      static final String DESCRIPTION = "Arrays have to have the same elements in any order";
      static final String MISMATCH = "Arrays do not have the same elements\n";

      @Override
      public boolean matches(Object item) {
        Objects.requireNonNull(item, "Array must not be null");

        int[] clone = ((int[]) item).clone();
        Arrays.sort(clone);
        Arrays.sort(expected);
        return Arrays.equals(clone, expected);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(DESCRIPTION);
      }

      @Override
      public void describeMismatch(Object item, Description description) {
        StringBuilder builder = new StringBuilder(MISMATCH);

        int[] dif = Utils.difference((int[]) item, expected);
        if (dif.length != 0) {
          String errorMessage = "Items %s have to be present, but they haven't\n";
          builder.append(String.format(errorMessage, Arrays.toString(dif)));
        }

        dif = Utils.difference(expected, (int[]) item);
        if (dif.length != 0) {
          String errorMessage = "Items %s have not been expected\n";
          builder.append(String.format(errorMessage, Arrays.toString(dif)));
        }
        description.appendText(builder.toString());
      }
    };
  }
}
