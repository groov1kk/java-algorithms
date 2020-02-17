package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class ShellSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort() {
    int[] clone = array.clone();

    Sort sort = new ShellSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
