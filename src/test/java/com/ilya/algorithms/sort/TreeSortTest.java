package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class TreeSortTest extends BaseSortTest {

  @Test
  public void testTreeSort() {
    Sort sort = new TreeSort();
    Assert.assertThat(sort.sort(array), isSortedAsc());
  }
}
