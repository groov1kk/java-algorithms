package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.matchers.IsIntArrayContainingInAnyOrder.intArrayContainingInAnyOrder;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.naturalOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

import org.junit.jupiter.api.Test;

import com.github.groov1kk.extensions.RandomArray;

public class InsertionSortTest extends BaseSortTest {

  @Test
  public void testInsertionSort(@RandomArray int[] array) {
    int[] clone = array.clone();

    Sort sort = new InsertionSort();
    sort.sort(array);

    assertThat(array, allOf(intArrayContainingInAnyOrder(clone), naturalOrder()));
  }
}
