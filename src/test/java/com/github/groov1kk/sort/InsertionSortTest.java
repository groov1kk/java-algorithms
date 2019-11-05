package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort() {
    Sort sort = new InsertionSort();
    int[] copy = array.clone();

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(copy));
    Assert.assertThat(array, isSortedAsc());
  }
}
