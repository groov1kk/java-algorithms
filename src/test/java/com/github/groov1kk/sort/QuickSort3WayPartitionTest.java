package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSort3WayPartitionTest extends BaseSortTest {

  @Test
  public void testQuickSort() {
    Sort sort = new QuickSort3WayPartition();
    int[] clone = array.clone();
    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
