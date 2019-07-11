package com.ilya.algorithms.search;

import com.ilya.algorithms.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;

public class BinarySearchTest extends BaseTest {

  private static final int ARRAY_LENGTH = 30;
  private static final int FROM_VALUE = -50;
  private static final int TO_VALUE = 50;

  private Integer[] array;
  private Search<Integer> search;

  @Before
  public void init() {
    this.search = new BinarySearch<>();
    this.array = distinctArray(ARRAY_LENGTH, FROM_VALUE, TO_VALUE);
  }

  @Test
  public void testBinarySearchValuePresent() {
    Arrays.sort(this.array);

    // Middle element
    int position = this.array.length / 2;
    int value = this.array[position];
    Assert.assertThat(this.search.rank(this.array, value), is(position));

    // Left from middle
    position = this.array.length / 2 - 1;
    value = this.array[position];
    Assert.assertThat(this.search.rank(this.array, value), is(position));

    // Right from middle
    position = this.array.length / 2 + 1;
    value = this.array[position];
    Assert.assertThat(this.search.rank(this.array, value), is(position));
  }

  @Test
  public void testBinarySearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    Assert.assertThat(this.search.rank(this.array, value), is(-1));
  }
}
