package com.github.groov1kk.search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.github.groov1kk.extensions.RandomArray;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BinarySearchTest extends BaseSearchTest {

  private static final int TO_VALUE = 40;

  private final Search<Integer> search;
  private final Integer[] array;

  public BinarySearchTest(@RandomArray(to = TO_VALUE, distinct = true) Integer[] array) {
    this.search = new BinarySearch<>();
    this.array = array;
  }

  @Test
  public void testBinarySearchMiddleValue() {
    Arrays.sort(array);

    int position = array.length / 2;
    int value = array[position];
    assertThat(search.rank(array, value), is(position));
  }

  @Test
  public void testBinarySearchLeftValue() {
    Arrays.sort(array);

    int position = array.length / 2 - 1;
    int value = array[position];
    assertThat(search.rank(array, value), is(position));
  }

  @Test
  public void testBinarySearchRightValue() {
    Arrays.sort(array);

    int position = array.length / 2 + 1;
    int value = array[position];
    assertThat(search.rank(array, value), is(position));
  }

  @Test
  public void testBinarySearchValueNotPresent() {
    int value = TO_VALUE + 2; // More than upper bound;

    assertThat(search.rank(array, value), is(-1));
  }
}
