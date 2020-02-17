package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest extends BaseSortTest {

  @Test
  public void testHeapSort() {
    int[] clone = array.clone();

    Sort sort = new HeapSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
