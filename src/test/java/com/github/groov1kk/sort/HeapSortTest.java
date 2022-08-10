package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.matchers.IsIntArrayContainingInAnyOrder.intArrayContainingInAnyOrder;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.naturalOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class HeapSortTest extends BaseSortTest {

  @Test
  public void testHeapSort() {
    int[] clone = array.clone();

    Sort sort = new HeapSort();
    sort.sort(array);

    assertThat(array, intArrayContainingInAnyOrder(clone));
    assertThat(array, naturalOrder());
  }
}
