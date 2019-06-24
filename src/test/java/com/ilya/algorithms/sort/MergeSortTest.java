package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest extends BaseSortTest {

  @Test
  public void testMergeSort() {
    Sort sort = new MergeSort();
    int[] clone = array.clone();

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
