package com.github.groov1kk.search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.github.groov1kk.extensions.RandomArray;
import org.junit.jupiter.api.Test;

public class LinearSearchTest extends BaseSearchTest {

  private static final int TO_VALUE = 40;

  private final Search<Integer> search;
  private final Integer[] array;

  public LinearSearchTest(@RandomArray(to = TO_VALUE, distinct = true) Integer[] array) {
    this.search = new LinearSearch<>();
    this.array = array;
  }

  @Test
  public void testLinearSearchValuePresent() {
    int middle = array.length / 2;
    int value = array[middle];

    assertThat(search.rank(array, value), is(middle));
  }

  @Test
  public void testLinearSearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    assertThat(search.rank(array, value), is(-1));
  }
}
