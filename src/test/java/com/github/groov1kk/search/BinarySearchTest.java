package com.github.groov1kk.search;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class BinarySearchTest extends BaseSearchTest {

  private final Search<Integer> search = new BinarySearch<>();;

  @Test
  public void testBinarySearchValuePresent() {
    Arrays.sort(array);

    // Middle element
    int position = array.length / 2;
    int value = array[position];
    assertThat(search.rank(array, value), is(position));

    // Left from middle
    position = array.length / 2 - 1;
    value = array[position];
    assertThat(search.rank(array, value), is(position));

    // Right from middle
    position = array.length / 2 + 1;
    value = array[position];
    assertThat(search.rank(array, value), is(position));
  }

  @Test
  public void testBinarySearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    assertThat(search.rank(array, value), is(-1));
  }
}
