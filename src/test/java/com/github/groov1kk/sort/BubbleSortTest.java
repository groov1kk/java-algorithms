package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest extends BaseSortTest {

  @Test
  public void testBubbleSort() {
    int[] clone = array.clone();

    Sort sort = new BubbleSort();
    sort.sort(array);

    Assert.assertThat(array, hasTheSameItemsInAnyOrder(clone));
    Assert.assertThat(array, isSortedAsc());
  }
}
