package com.github.groov1kk.search;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class LinearSearchTest extends BaseSearchTest {

  private final Search<Integer> search = new LinearSearch<>();

  @Test
  public void testLinearSearchValuePresent() {
    int middle = ARRAY_LENGTH / 2;
    int value = array[middle];

    Assert.assertThat(search.rank(array, value), is(middle));
  }

  @Test
  public void testLinearSearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    Assert.assertThat(search.rank(array, value), is(-1));
  }
}
