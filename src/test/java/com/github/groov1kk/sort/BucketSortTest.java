package com.github.groov1kk.sort;

import static com.github.groov1kk.utils.matchers.IsIntArrayContainingInAnyOrder.intArrayContainingInAnyOrder;
import static com.github.groov1kk.utils.matchers.IsIntArraySorted.naturalOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.groov1kk.extensions.RandomArray;
import org.junit.jupiter.api.Test;

public class BucketSortTest extends BaseSortTest {

  private final int[] array;

  public BucketSortTest(@RandomArray int[] array) {
    this.array = array;
  }

  @Test
  public void testBucketSort() {
    int[] clone = array.clone();

    Sort sort = new BucketSort();
    sort.sort(array);

    assertThat(array, intArrayContainingInAnyOrder(clone));
    assertThat(array, naturalOrder());
  }

  @Test
  public void testBucketSortCustomRange() {
    int[] clone = array.clone();

    int bucketRange = 20;
    Sort sort = new BucketSort(bucketRange);
    sort.sort(array);

    assertThat(array, naturalOrder());
    assertThat(array, intArrayContainingInAnyOrder(clone));
  }

  @Test
  public void testBucketSortCustomRangeAndSort() {
    int[] clone = array.clone();

    int bucketRange = 20;
    Sort bucketSort = new BubbleSort();
    Sort sort = new BucketSort(bucketRange, bucketSort);
    sort.sort(array);

    assertThat(array, naturalOrder());
    assertThat(array, intArrayContainingInAnyOrder(clone));
  }
}
