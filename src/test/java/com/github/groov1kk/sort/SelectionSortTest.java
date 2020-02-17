package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class SelectionSortTest extends BaseSortTest {

  @Test
  public void testSelectionSort() {
    int[] clone = array.clone();

    Sort sort = new SelectionSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
