package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class CountingSortTest extends BaseSortTest {

  @Test
  public void testCountingSort() {
    Sort sort = new CountingSort();
    Assert.assertThat(sort.sort(array), isSortedAsc());
  }
}
