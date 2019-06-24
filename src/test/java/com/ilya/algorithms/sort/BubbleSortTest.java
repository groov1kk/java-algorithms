package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest extends BaseSortTest {

  @Test
  public void testBubbleSort() {
    Sort sort = new BubbleSort();
    Assert.assertThat(sort.sort(this.array), isSortedAsc());
  }
}
