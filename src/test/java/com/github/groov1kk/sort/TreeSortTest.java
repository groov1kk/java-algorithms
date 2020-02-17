package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class TreeSortTest extends BaseSortTest {

  @Test
  public void testTreeSort() {
    int[] clone = array.clone();

    Sort sort = new TreeSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
