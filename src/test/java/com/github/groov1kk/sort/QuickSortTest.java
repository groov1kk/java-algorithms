package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest extends BaseSortTest {

  @Test
  public void testQuickSort() {
    int[] clone = array.clone();

    Sort sort = new QuickSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
