package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest extends BaseSortTest {

  @Test
  public void testQuickSort() {
    Sort sort = new QuickSort();
    Assert.assertThat(sort.sort(array), isSortedAsc());
  }
}
