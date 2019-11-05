package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class ShakerSortTest extends BaseSortTest {

  @Test
  public void testShakerSort() {
    Sort sort = new ShakerSort();
    Assert.assertThat(sort.sort(array), isSortedAsc());
  }
}
