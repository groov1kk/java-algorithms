package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class ShellSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort() {
    Sort sort = new ShellSort();
    int[] clone = array.clone();

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
