package com.ilya.algorithms.search;

import com.ilya.algorithms.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class LinearSearchTest extends BaseTest {

  private static final int ARRAY_LENGTH = 30;
  private static final int FROM_VALUE = -50;
  private static final int TO_VALUE = 50;

  private int[] array;
  private Search search;

  @Before
  public void init() {
    this.search = new LinearSearch();
    this.array = distinctArray(ARRAY_LENGTH, FROM_VALUE, TO_VALUE);
  }

  @Test
  public void testLinearSearchValuePresent() {
    int middle = ARRAY_LENGTH / 2;
    int value = this.array[middle];

    Assert.assertThat(this.search.rank(this.array, value), is(middle));
  }

  @Test
  public void testLinearSearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    Assert.assertThat(this.search.rank(this.array, value), is(-1));
  }
}
