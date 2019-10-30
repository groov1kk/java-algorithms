package com.ilya.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class BucketSortTest extends BaseSortTest {

  @Test
  public void testBucketSort() {
    Sort sort = new BucketSort();
    Assert.assertThat(sort.sort(array), isSortedAsc());
  }

  @Test
  public void testBucketSortCustomRange() {
    int bucketRange = 20;

    Sort sort = new BucketSort(bucketRange);
    int[] sorted = sort.sort(array.clone());

    Assert.assertThat(sorted, isSortedAsc());
    Assert.assertThat(array, hasSameItemsInAnyOrder(sorted));
  }

  @Test
  public void testBucketSortCustomRangeAndSort() {
    int bucketRange = 20;
    Sort bucketSort = new BubbleSort();

    Sort sort = new BucketSort(bucketRange, bucketSort);
    int[] sorted = sort.sort(array.clone());

    Assert.assertThat(sorted, isSortedAsc());
    Assert.assertThat(array, hasSameItemsInAnyOrder(sorted));
  }
}
