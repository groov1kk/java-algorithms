package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort() {
    int[] copy = array.clone();

    Sort sort = new InsertionSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(copy));
    Assert.assertThat(array, isSortedAsc());
  }
}
