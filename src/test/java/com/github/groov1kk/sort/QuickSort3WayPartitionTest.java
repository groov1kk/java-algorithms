package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSort3WayPartitionTest extends BaseSortTest {

  @Test
  public void testQuickSort() {
    int[] clone = array.clone();

    Sort sort = new QuickSort3WayPartition();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
