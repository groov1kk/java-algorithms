package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class ShakerSortTest extends BaseSortTest {

  @Test
  public void testShakerSort() {
    int[] clone = array.clone();

    Sort sort = new ShakerSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
