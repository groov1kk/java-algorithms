package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ShellSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort() {
    Sort sort = new ShellSort();
    int[] oldArray = Arrays.copyOf(array, array.length);

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(oldArray));
    Assert.assertThat(array, isSortedAsc());
  }
}
