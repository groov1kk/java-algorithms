package com.github.groov1kk.sort;

import static com.github.groov1kk.matchers.IsIntArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static com.github.groov1kk.matchers.IsIntArraySorted.naturalOrder;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest extends BaseSortTest {

  @Test
  public void testQuickSort() {
    int[] clone = array.clone();

    Sort sort = new QuickSort();
    sort.sort(array);

    Assert.assertThat(array, arrayContainingInAnyOrder(clone));
    Assert.assertThat(array, naturalOrder());
  }
}
