package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class CountingSortTest extends BaseSortTest {

  @Test
  public void testCountingSort() {
    int[] clone = array.clone();

    Sort sort = new CountingSort();
    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
