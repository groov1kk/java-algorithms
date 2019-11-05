package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class TreeSortTest extends BaseSortTest {

  @Test
  public void testTreeSort() {
    Sort sort = new TreeSort();
    int[] clone = array.clone();

    sort.sort(array);

    Assert.assertThat(array, hasSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
