package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest extends BaseSortTest {

  @Test
  public void testMergeSort() {
    int[] clone = array.clone();

    Sort sort = new MergeSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
