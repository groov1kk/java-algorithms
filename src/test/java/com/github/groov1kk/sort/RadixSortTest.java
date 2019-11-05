package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class RadixSortTest extends BaseSortTest {

  @Test
  public void testRadixSort() {
    int[] clone = array.clone();

    Sort radixSort = new RadixSort();
    radixSort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
