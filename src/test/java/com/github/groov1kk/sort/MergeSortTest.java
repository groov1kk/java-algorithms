package com.github.groov1kk.sort;

import static com.github.groov1kk.matchers.IsIntArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static com.github.groov1kk.matchers.IsIntArraySorted.naturalOrder;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest extends BaseSortTest {

  @Test
  public void testMergeSort() {
    int[] clone = array.clone();

    Sort sort = new MergeSort();
    sort.sort(array);

    Assert.assertThat(array, arrayContainingInAnyOrder(clone));
    Assert.assertThat(array, naturalOrder());
  }
}
