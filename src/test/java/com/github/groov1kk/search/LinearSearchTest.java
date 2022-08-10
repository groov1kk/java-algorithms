package com.github.groov1kk.search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class LinearSearchTest extends BaseSearchTest {

  private final Search<Integer> search = new LinearSearch<>();

  @Test
  public void testLinearSearchValuePresent() {
    int middle = ARRAY_LENGTH / 2;
    int value = array[middle];

    assertThat(search.rank(array, value), is(middle));
  }

  @Test
  public void testLinearSearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    assertThat(search.rank(array, value), is(-1));
  }
}
