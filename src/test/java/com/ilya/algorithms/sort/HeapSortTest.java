package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest extends BaseSortTest {

  @Test
  public void testHeapSort() {
    Sort sort = new HeapSort();
    int[] clone = array.clone();

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
